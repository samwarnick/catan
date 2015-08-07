package server.dao.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import client.data.GameInfo;
import server.GameHub;
import server.commands.move.MoveCommand;
import server.dao.IGameDAO;
import shared.model.GameModel;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonGameDAO implements IGameDAO {
	
	private int commandLimit;
	final String path = "Persistance" + File.separator + "Json" + File.separator;
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GameInfo> getAllGameInfos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MoveCommand> getCommands(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}



}
