package client.proxy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	        conn.connect();
	        ObjectMapper mapper = new ObjectMapper();
	        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(toPost));
	        mapper.writeValue(conn.getOutputStream(), toPost);
	        conn.getOutputStream().close();
	        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	        	return mapper.readTree(conn.getInputStream());
	        }
	        else{
	        	throw new ServerException(String.format(url.toString(),
						toPost.getMethod(), conn.getResponseCode()));
	        }
		} catch (IOException e) {
			throw new ServerException(e.getMessage());
		}
	}
	public Object postNotGameModel(Input toPost) throws ServerException {
		try {
			String method = toPost.getMethod();
	        URL url;
			url = new URL(URLPrefix+method);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        conn.connect();
	        ObjectMapper mapper = new ObjectMapper();
	        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(toPost));
	        mapper.writeValue(conn.getOutputStream(), toPost);
	        conn.getOutputStream().close();
	        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	        	
	        	
	        	
	        	Object object = mapper.readValue(conn.getInputStream(), Object.class);
	        	return object;
	        }
	        else{
	        	throw new ServerException(String.format("%s, %s, %s", url.toString(),
						toPost.getMethod(), conn.getResponseCode()));
	        }
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServerException(e.getMessage());
		}
	}
	
	private void printResponse(HttpURLConnection conn) {
		StringBuilder builder = new StringBuilder();
    	try {
			builder.append(conn.getResponseCode())
			       .append(" ")
			       .append(conn.getResponseMessage())
			       .append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

    	Map<String, List<String>> map = conn.getHeaderFields();
    	for (Map.Entry<String, List<String>> entry : map.entrySet())
    	{
    	    if (entry.getKey() == null) 
    	        continue;
    	    builder.append( entry.getKey())
    	           .append(": ");

    	    List<String> headerValues = entry.getValue();
    	    Iterator<String> it = headerValues.iterator();
    	    if (it.hasNext()) {
    	        builder.append(it.next());

    	        while (it.hasNext()) {
    	            builder.append(", ")
    	                   .append(it.next());
    	        }
    	    }

    	    builder.append("\n");
    	}

    	System.out.println(builder);
	}
}
