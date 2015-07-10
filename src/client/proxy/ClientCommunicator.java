package client.proxy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
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
	
	public JsonNode post(Input toPost) throws ServerException {
		try {
			String method = toPost.getMethod();
	        URL url;
			//url = new URL(URLPrefix+ PATH_PREFIX +method);
			url = new URL(URLPrefix + method);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        if(cookie!=null){
	        	// System.out.println(cookie);
	        	conn.addRequestProperty("Cookie", cookie);
	        }
	        conn.connect();
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.writeValue(conn.getOutputStream(), toPost);
	        conn.getOutputStream().close();
	        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	        	if (conn.getInputStream().available() == 7) { // i.e. "success" in response body
	        		if(toPost.getMethod().equals("/user/login")){
	        			String precookie = (String) conn.getHeaderField("Set-Cookie");
	        			cookie = precookie.substring(0, precookie.length()-8);
	        		}
	        		if(toPost.getMethod().equals("/games/join")){
	        			String precookie = (String) conn.getHeaderField("Set-Cookie");
	        			cookie += ";  " + precookie.substring(0, precookie.length()-8);
	        		}
	        		return null;
	        	}
	        	else {
	        		return mapper.readTree(conn.getInputStream());
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
}
