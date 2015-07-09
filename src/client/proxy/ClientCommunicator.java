package client.proxy;

import java.io.IOException;
import java.io.Writer;
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
	private String PATH_PREFIX = "/docs/api/data";
	private String URLPrefix;
	
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
	
	public Object post(Input toPost) throws ServerException {
		Object result = null;
		try {
			String method = toPost.getMethod();
	        URL url;
			//url = new URL(URLPrefix+ PATH_PREFIX +method);
			url = new URL(URLPrefix + method);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        conn.connect();
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.writeValue(conn.getOutputStream(), toPost);
	        String x = mapper.writeValueAsString(toPost);
	        System.out.println(x);
	        conn.getOutputStream().close();
	        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	        	result = new Object();
	        	result = mapper.readValue(conn.getInputStream(), result.getClass());
	        }
	        else{
	        	throw new ServerException(String.format(url.toString(),
						toPost.getMethod(), conn.getResponseCode()));
	        }
		} catch (IOException e) {
			throw new ServerException(e.getMessage());
		}
		return result;
	}

}
