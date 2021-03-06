package server;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarFile;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import server.factories.AbstractFactory;
import server.factories.JsonFactory;
import server.factories.SQLFactory;
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
		System.setProperty("sun.zip.disableMemoryMapping", "true");
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
		server.createContext("/moves/", moveHandler);
		
		server.start();
	}
	
	public static void main(String[] args) {
		int port = 8081;
		int n = 7;
		String persistType = "SQL";
		
		Server server = new Server();

		if (args.length == 3) {
			port = Integer.parseInt(args[0]);
			n = Integer.parseInt(args[1]);
			System.out.println(args[2]);
			persistType = args[2];
		}
		server.setUpPersistence(n, persistType);
		server.run(port);
	}
	
	@SuppressWarnings({ "rawtypes", "resource" })
	private void setUpPersistence(int n, String persistType) {
		try {
//			String path = new File(Server.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().toString();
//			JarFile jarFile = new JarFile("lib/plugins/" + persistType + ".jar");
//			Enumeration e = jarFile.entries();
			URL[] urls = { new URL("jar:file:lib/plugins/" + persistType +".jar!/") };
//			URLClassLoader cl = URLClassLoader.newInstance(urls);
//			AbstractFactory factory = null;
//			while (e.hasMoreElements()) {
//		        JarEntry je = (JarEntry) e.nextElement();
//		        if(!je.isDirectory() && je.getName().endsWith(".class")){
//		        	String className = je.getName().substring(0,je.getName().length()-6);
//			        className = className.replace('/', '.');
//			        if (className.equals("server.factories." + persistType +"Factory")) {
//			        	Class c = cl.loadClass(className);
//			        	factory = (AbstractFactory) c.newInstance();
//			        }
//		        }
//		    }
//			
			AbstractFactory factory = null;
			if (persistType.equals("Json")) {
				factory = new JsonFactory();
			} else if (persistType.equals("SQL")) {
				factory = new SQLFactory();
			}
			
//			URLClassLoader loader = new URLClassLoader(urls, this.getClass().getClassLoader());
//			Class classToLoad = Class.forName("server.factories." + persistType + "Factory", true, loader);
//			AbstractFactory factory = (AbstractFactory) classToLoad.newInstance();
//			loader.close();
					
			// make DAOs from factory
			GameHub.getInstance().setUserDAO(factory.makeUserDAO());
			GameHub.getInstance().setGameDAO(factory.makeGameDAO(n));
			// load data from DAOs
			GameHub.getInstance().loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
