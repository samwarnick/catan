package client.proxy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import server.ServerException;
import shared.communication.input.Input;

/**
 * @author isaachartung
 *
 *The ClientCommunicator receives java objects, serializes them, and sends them to the server.
 *It is also responsible for receiving serialized objects from the server, deserializing them,
 *and returning the new java object to its caller.
 *
 */
public class ClientCommunicator {
	
	private final String DEFAULT_HOST = "localhost";
	private final int DEFAULT_PORT = 8081;
	private String serverHost = "localhost";
	private int serverPort = 8081;
	private String URLPrefix;
	private String cookie = null;
	private int playerId = -1;
	private int gameId = -1;
	
	/**
	 * 
	 * @param host is the name of the machine running the server
	 * @param port is the port of that the server is listening on
	 * @pre port < 65,535 and host is not null 
	 * @post a ClientCommunicator is created with the given host name and port number.
	 */
	
	public ClientCommunicator() {
		URLPrefix = "http://" + DEFAULT_HOST + ":" + DEFAULT_PORT;
	}

	
	public void modcook(){
		
	}
	
	public ClientCommunicator(String host, int port){
		serverHost = host;
		serverPort = port;
		URLPrefix = "http://" + serverHost + ":" + serverPort;
	}

	/**
	 * 
	 * @param toPost is some Object to be sent to the server
	 * @pre the Object is not be null
	 * @post returns the object included in the HTML response given by the server.
	 */
	
	public Object post(Input toPost, String requestMethod) throws ServerException {
		try {
			String method = toPost.getMethod();
	        URL url;
			url = new URL(URLPrefix + method);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod(requestMethod);
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Content-Type", "text/html");
	        if(cookie!=null){
	        	conn.setRequestProperty("Cookie", cookie);
	        }
	        conn.connect();
	        ObjectMapper mapper = new ObjectMapper();
//	        mapper.setVisibilityChecker(mapper.getVisibilityChecker().withFieldVisibility(Visibility.ANY));
	        if (requestMethod != "GET") {
	        }
	        conn.getOutputStream().close();
	        
	        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	        	
	        	if (conn.getContentLength() == 7) { // i.e. "success" in response body
	        		System.out.println("Success");
	        		if(toPost.getMethod().equals("/user/login") || toPost.getMethod().equals("/user/register")){
//	        			String precookie = (String) conn.getHeaderField("Set-Cookie");
//	        			cookie = precookie.substring(0, precookie.length()-8);
//	        			StringBuilder temp = new StringBuilder(URLDecoder.decode(cookie, "UTF-8"));
//	        			int index = temp.lastIndexOf("\"playerID\":") + 11;
//	        			playerId = Integer.parseInt(temp.substring(index, temp.length()-1));
	        			cookie = (String) conn.getHeaderField("Set-Cookie");
	        			StringBuilder temp = new StringBuilder(cookie);
	        			int index = temp.lastIndexOf("catan.user=") + 11;
	        			playerId = Integer.parseInt(temp.substring(index, temp.length()));
	        			System.out.println(playerId);
	        		}
	        		if(toPost.getMethod().equals("/games/join")){
	        			cookie += ";" + (String) conn.getHeaderField("Set-Cookie");
	        			StringBuilder temp = new StringBuilder(cookie);
	        			int index = temp.lastIndexOf("catan.game=") + 11;
	        			gameId = Integer.parseInt(temp.substring(index, temp.length()));
	        		}
	        		return null;
	        	}
	        	else{
	        		return mapper.readValue(conn.getInputStream(), Object.class);
	        	}
	        }
	        else{
	        	throw new ServerException(String.format("%s, %s, %s", url.toString(),
						toPost.getMethod(), conn.getResponseCode()));
	        }
		} catch (IOException e) {
			throw new ServerException(e.getMessage());
		}
	}


	public int getPlayerId() {
		return playerId;
	}


	public int getGameId() {
		return gameId;
	}


	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}


	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
}
