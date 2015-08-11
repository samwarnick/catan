package server.dao.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import client.data.GameInfo;
import server.GameHub;
import server.ServerException;
import server.commands.move.MoveCommand;
import server.dao.IGameDAO;
import shared.model.GameModel;

import org.apache.commons.io.FileUtils;




import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class JsonGameDAO implements IGameDAO {
	
	private int commandLimit;
	final String path = "persistence" + File.separator + "Json" + File.separator;
	
	public JsonGameDAO(int commandLimit) {
		this.commandLimit = commandLimit;
	}

	@Override
	public void addGameModel(GameModel model) {
		File dir = new File(path + model.getGameID());
		if (!dir.exists()) {
			if (!dir.mkdir()) {
				System.out.println("directory failed to be created");
			}
		}
		else {
			System.out.println("directory already exists");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
//			mapper.writeValue(new File(path + model.getGameID() + File.separator + "model.json"), model);
			XStream xml = new XStream(new DomDriver());
			FileUtils.writeStringToFile(new File(path + model.getGameID() + File.separator + "model.xml"), xml.toXML(model));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addGameInfo(GameInfo info) {
		File dir = new File(path + info.getId());
		if (!dir.exists()) {
			if (!dir.mkdir()) {
				System.out.println("directory failed to be created");
			}
		}
		else {
			System.out.println("directory already exists");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
//			mapper.writeValue(new File(path + info.getId() + File.separator + "info.json"), info);
			XStream xml = new XStream(new DomDriver());
			FileUtils.writeStringToFile(new File(path + info.getId() + File.separator + "info.xml"), xml.toXML(info));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addCommand(int gameID, MoveCommand command) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(path + gameID + File.separator + "commands.xml");
		if (command == null) {
			try {
//				mapper.writeValue(file, new ArrayList<MoveCommand>());
				XStream xml = new XStream(new DomDriver());
				FileUtils.writeStringToFile(file, xml.toXML(new ArrayList<MoveCommand>()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			List<MoveCommand> commands = null;
			try {
				if (file.exists()) {
					XStream xml = new XStream(new DomDriver());
//					commands = mapper.readValue(file, new TypeReference<List<MoveCommand>>(){});
					commands = (List<MoveCommand>) xml.fromXML(file);
					if (commands != null && commands.size() < commandLimit - 1) {
						commands.add(command);
//						mapper.writeValue(file, commands);
						FileUtils.writeStringToFile(file, xml.toXML(commands));
					}
					else {
//						mapper.writeValue(new File(path + gameID + File.separator + "model.json"), GameHub.getInstance().getModel(gameID));
						FileUtils.writeStringToFile(new File(path + gameID + File.separator + "model.xml"), xml.toXML(GameHub.getInstance().getModel(gameID)));
//						mapper.writeValue(file, new ArrayList<MoveCommand>());
						FileUtils.writeStringToFile(file, xml.toXML(new ArrayList<MoveCommand>()));
					}
				}
				else {
//					mapper.writeValue(file, new ArrayList<MoveCommand>());
					XStream xml = new XStream(new DomDriver());
					FileUtils.writeStringToFile(file, xml.toXML(new ArrayList<MoveCommand>()));
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void updateGameModel(GameModel model) {
		File file = new File(path + model.getGameID() + File.separator + "model.xml");
		if (file.exists()) {
			ObjectMapper mapper = new ObjectMapper();
			try {
//				mapper.writeValue(file, model);
				XStream xml = new XStream(new DomDriver());
				FileUtils.writeStringToFile(file, xml.toXML(model));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("game did not exist in persistance storage");
		}
	}

	@Override
	public void updateGameInfo(GameInfo info) {
		File file = new File(path + info.getId() + File.separator + "info.xml");
		if (file.exists()) {
			ObjectMapper mapper = new ObjectMapper();
			try {
//				mapper.writeValue(file, info);
				XStream xml = new XStream(new DomDriver());
				FileUtils.writeStringToFile(file, xml.toXML(info));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("info did not exist in persistance storage");
		}
	}

	@Override
	public List<GameModel> getAllGameModels() {
		List<GameModel> models = new ArrayList<GameModel>();
		//for each file
	    File directory = new File("persistence" + File.separator + "Json");
	    File [] listOfDirectories = directory.listFiles();
		for(int i = 0; i < listOfDirectories.length; i++)
		{
			ObjectMapper mapper = new ObjectMapper();
			File file = new File(path + i + File.separator + "model.json");
			if (file.exists()) {
				XStream xml = new XStream(new DomDriver());
				//get the game model
//					GameModel model = mapper.readValue(file, GameModel.class);
				GameModel model = (GameModel)xml.fromXML(file);
				//get that games commands
				List<MoveCommand> commands = getCommands(i);
				//and execute those commands
				for(MoveCommand command: commands)
				{
					command.setGameModel(model);
					try {
						model = (GameModel)command.execute(command.getInput());
					} catch (ServerException e) {
						e.printStackTrace();
					}
				}
				//add to List
				models.add(model);
				addCommand(i, null);
			}
		}
		return models;
	}

	@Override
	public List<GameInfo> getAllGameInfos() {
		List<GameInfo> infos = new ArrayList<GameInfo>();
		//for each file
	    File directory = new File("persistence" + File.separator + "Json");
	    System.out.println(directory.getPath());
	    System.out.println(directory.isDirectory());
	    File [] listOfDirectories = directory.listFiles();
	    
		for(int i = 0; i < listOfDirectories.length; i++)
		{
//			ObjectMapper mapper = new ObjectMapper();
			File file = new File(path + i + File.separator + "info.xml");
			if (file.exists()) {
				XStream xml = new XStream(new DomDriver());
				//					GameInfo info = mapper.readValue(file, GameInfo.class);
				GameInfo info = (GameInfo)xml.fromXML(file);
				infos.add(info);
			}
	
		}
		return infos;
	}

	@Override
	public List<MoveCommand> getCommands(int gameID) {
		List<MoveCommand> commands = new ArrayList<MoveCommand>();
		ObjectMapper mapper = new ObjectMapper();
		XStream xml = new XStream(new DomDriver());
//			commands = mapper.readValue(new File(path + gameID + File.separator + "commands.xml"), new TypeReference<List<MoveCommand>>(){});
		commands = (List<MoveCommand>)xml.fromXML(new File(path + gameID + File.separator + "commands.xml"));
		return commands;
	}



}
