package server.dao.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import client.data.GameInfo;
import server.GameHub;
import server.ServerException;
import server.commands.move.MoveCommand;
import server.dao.IGameDAO;
import shared.model.GameModel;

//import org.apache.commons.io.FileUtils;




import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
			mapper.writeValue(new File(path + model.getGameID() + File.separator + "model.json"), model);
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
			mapper.writeValue(new File(path + info.getId() + File.separator + "info.json"), info);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addCommand(int gameID, MoveCommand command) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(path + gameID + File.separator + "commands.json");
		List<MoveCommand> commands = null;
		try {
			if (file.exists()) {
				commands = mapper.readValue(file, new TypeReference<List<MoveCommand>>(){});
				if (commands != null && commands.size() < commandLimit - 1) {
					commands.add(command);
					mapper.writeValue(file, commands);
				}
				else {
					mapper.writeValue(new File(path + gameID + File.separator + "model.json"), GameHub.getInstance().getModel(gameID));
					mapper.writeValue(file, new ArrayList<MoveCommand>());
				}
			}
			else {
				mapper.writeValue(file, new ArrayList<MoveCommand>());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateGameModel(GameModel model) {
		File file = new File(path + model.getGameID() + File.separator + "model.json");
		if (file.exists()) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				mapper.writeValue(file, model);
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
		File file = new File(path + info.getId() + File.separator + "info.json");
		if (file.exists()) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				mapper.writeValue(file, info);
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
			try {
				File file = new File(path + i + File.separator + "model.json");
				if (file.exists()) {
					//get the game model
					GameModel model = mapper.readValue(file, GameModel.class);
					//get that games commands
					List<MoveCommand> commands = getCommands(i);
					//and execute those commands
					for(MoveCommand command: commands)
					{
						command.setGameModel(model);
						try {
							command.execute(command.getInput());
						} catch (ServerException e) {
							e.printStackTrace();
						}
					}
					//add to List
					models.add(model);
				}
				
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
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
			ObjectMapper mapper = new ObjectMapper();
			try {
				File file = new File(path + i + File.separator + "info.json");
				if (file.exists()) {
					GameInfo info = mapper.readValue(file, GameInfo.class);
					infos.add(info);
				}
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		}
		return infos;
	}

	@Override
	public List<MoveCommand> getCommands(int gameID) {
		List<MoveCommand> commands = new ArrayList<MoveCommand>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			commands = mapper.readValue(new File(path + gameID + File.separator + "commands.json"), new TypeReference<List<MoveCommand>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return commands;
	}



}
