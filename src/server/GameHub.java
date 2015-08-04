package server;

import java.util.ArrayList;
import java.util.List;

import client.data.GameInfo;
import client.data.PlayerInfo;
import server.commands.games.CreateCommand;
import shared.communication.input.GamesCreateInput;
import shared.definitions.CatanColor;
import shared.model.GameModel;
import shared.model.board.Board;
import shared.model.player.Player;
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
		
		addUser(new User("Spencer","spencer"));
		addUser(new User("Sam","sam"));
		addUser(new User("Isaac","isaac"));
		addUser(new User("Jordan","jordan"));
		addUser(new User("Matt","matt"));
		
		
//		ArrayList<PlayerInfo> infos = new ArrayList<PlayerInfo>();
//		infos.add(new PlayerInfo("Spencer",CatanColor.WHITE,0));
//		infos.add(new PlayerInfo("Sam",CatanColor.PUCE,1));
//		infos.add(new PlayerInfo("Isaac",CatanColor.GREEN,2));
//		infos.add(new PlayerInfo("Matt",CatanColor.ORANGE,4));
//		GameInfo ginfo = new GameInfo(0,"default",infos);
//		addInfo(ginfo);
//		GameModel model = GameModel.getDefaultModel();
//		ArrayList<Player> players = new ArrayList<Player>();
//		players.add(new Player(CatanColor.WHITE,"Spencer",0));
//		players.add(new Player(CatanColor.PUCE,"Sam",1));
//		players.add(new Player(CatanColor.GREEN,"Isaac",2));
//		players.add(new Player(CatanColor.ORANGE,"Matt",3));
//		model.setPlayers(players);
//		model.setBoard(new Board(true,true,true));
//		addModel(model);
		
		
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
	
	public int getModelsSize(){
		return models.size();
	}
	
	public void updateModel(GameModel updatedModel) {
		int id = updatedModel.getGameID();
		models.remove(id);
		models.add(id, updatedModel);
	}
	
	public GameInfo getInfo(int num){
		return infos.get(num);
	}
	

	public List<GameInfo> getGameInfos() {
		return infos;
	}

	public User getUser(int num){
		return users.get(num);
	}
	
	public User getUserByNameAndPassword(String username, String password) {
		for (User u: users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		
		return null;
	}
	
	public User registerUser(String username, String password) {
		if (!userAlreadyThere(username)) {
			User newUser = new User(username, password);
			addUser(newUser);
			return getUserByNameAndPassword(username, password);
		}
		return null;
	}
	
	private boolean userAlreadyThere(String username) {
		for (User u: users) {
			if (u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public void addModel(GameModel model) {
		models.add(model);
	}
	
	public void addInfo(GameInfo info) {
		infos.add(info);
	}
	
	public void addUser(User user) {
		user.setId(users.size());
		users.add(user);
	}
	
}
