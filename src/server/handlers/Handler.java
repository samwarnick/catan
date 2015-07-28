package server.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.commands.ICommand;

public abstract class Handler implements HttpHandler {
	protected ICommand command = null;

	@Override
	public abstract void handle(HttpExchange arg0) throws IOException;
	
	protected String jsonStringFromExchange(InputStream stream) {
		InputStreamReader isr;
		try {
			isr = new InputStreamReader(stream,"utf-8");
			BufferedReader br = new BufferedReader(isr);
			Scanner scanner = new Scanner(br);
			StringBuilder sb = new StringBuilder();
			while (scanner.hasNext()) {
				sb.append(scanner.next());
			}
			
			br.close();
			isr.close();
			scanner.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
