package server.dao.json;

import java.io.File;
import java.io.IOException;
import java.util.List;

import client.data.GameInfo;
import server.commands.move.MoveCommand;
import server.dao.IGameDAO;
import shared.model.GameModel;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonGameDAO implements IGameDAO {
	
	private int commandLimit;
	
	public JsonGameDAO(int commandLimit) {
		this.commandLimit = commandLimit;
	}

	@Override
	public void addGameModel(GameModel model) {
		// TODO Auto-generated method stub
		File dir = new File("Persistance" + File.separator + "Json" + File.separator + model.getGameID());
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
			mapper.writeValue(new File("Persistance/Json/model.json"), model);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addGameInfo(GameInfo info) {
		// TODO Auto-generated method stub
		File dir = new File("Persistance" + File.separator + "Json" + File.separator + info.getId());
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
			mapper.writeValue(new File("Persistance/Json/info.json"), info);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addCommand(int gameID, MoveCommand command) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("Persistance/Json/commands.json"), model);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateGameModel(GameModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateGameInfo(GameInfo info) {
		// TODO Auto-generated method stub

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
