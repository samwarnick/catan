package server;

import java.util.ArrayList;
import java.util.List;

import client.data.GameInfo;
import shared.model.GameModel;
import shared.model.user.User;

public class GameHub {

	private List<GameModel> models;
	private List<GameInfo> infos;
	private List<User> users;
	private static GameHub instance;
	
	private GameHub	(){
		models = new ArrayList<GameModel>();
		infos = new ArrayList<GameInfo>();
		users = new ArrayList<User>();
	}
	
	public static GameHub getInstance(){
		if (instance == null){
			instance = new GameHub();
		}
		return instance;
	}
	
	public GameModel getModel(int num){
		return models.get(num);
	}
	
	public GameInfo getInfo(int num){
		return infos.get(num);
	}
	
	public User getUser(int num){
		return users.get(num);
	}
	
	public void addModel(GameModel model){
		models.add(model);
	}
	
	public void addInfo(GameInfo info){
		infos.add(info);
	}
	
	public void addUser(User user){
		users.add(user);
	}
	
}
