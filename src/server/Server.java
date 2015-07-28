package server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import server.handlers.GameHandler;
import server.handlers.GamesHandler;
import server.handlers.MoveHandler;
import server.handlers.UserHandler;

public class Server {

	private HttpServer server;
	private HttpHandler userHandler = new UserHandler();
	private HttpHandler moveHandler = new MoveHandler();
	private HttpHandler gameHandler = new GameHandler();
	private HttpHandler gamesHandler = new GamesHandler();
	
	public void run(int port) {
		System.out.println("Server starting on port " + port);
		try {
			server = HttpServer.create(new InetSocketAddress(port), 100);
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.setExecutor(null);
		
		server.createContext("/user/", userHandler);
		server.createContext("/game/", gameHandler);
		server.createContext("/games/", gamesHandler);
		server.createContext("/move/", moveHandler);
		
		server.start();
	}
	
	public static void main(String[] args) {
		int port = 8081;
		if (args.length == 1) {
			port = Integer.parseInt(args[0]);
		}
		new Server().run(port);
	}
}
