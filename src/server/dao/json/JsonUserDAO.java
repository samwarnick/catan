package server.dao.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import server.dao.*;
import shared.model.user.User;

public class JsonUserDAO implements IUserDAO{
	
	final String path = "persistence" + File.separator + "Json" + File.separator + "User";

	@Override
	public void addUser(User user) {
		
//		File dir = new File(path + File.separator + user.getId());
//		if (!dir.exists()) {
//			if (!dir.mkdir()) {
//				System.out.println("directory failed to be created");
//			}
//		}
//		else {
//			System.out.println("directory already exists");
//		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new File(path + File.separator + user.getId() + ".json");
			mapper.writeValue(file, user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> getUsers() {
		
		List<User> users = new ArrayList<User>();
		//for each file
	    File dir = new File(path);
	    File [] listOfFiles = dir.listFiles();
		for(File file: listOfFiles)
		{
			if (file.isFile()){
				ObjectMapper mapper = new ObjectMapper();
				try {
					User user = mapper.readValue(file, User.class);
					users.add(user);
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return users;
		
	}

}
