package client.login;

import client.base.*;
import client.misc.*;
import client.proxy.ProxyServer;

import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

import server.ServerException;
import shared.communication.input.UserLoginInput;
import shared.communication.input.UserRegisterInput;
import shared.communication.input.move.AcceptTradeInput;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	private ProxyServer ps;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView) {

		super(view);
		
		this.messageView = messageView;
		ps = ProxyServer.getInstance();
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		System.out.println("here");
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		System.out.println("you got here");
		
		boolean success = true;
		// TODO: log in user
		if(getLoginView().getLoginUsername().trim().equals("")||
				getLoginView().getLoginPassword().trim().equals("")){
			getMessageView().setTitle("Login Failed");
			getMessageView().setMessage("there are empty fields");
			getMessageView().showModal();
			return;
		}
		try {
			ps.loginUser(new UserLoginInput(getLoginView().getLoginUsername(),
											getLoginView().getLoginPassword()));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			success = false;
		}
		// If log in succeeded
		if(success){
		getLoginView().closeModal();
		loginAction.execute();
		}
		else{
			getMessageView().setTitle("Login Failed");
			getMessageView().setMessage("The submitted username-password does not exist");
			getMessageView().showModal();
		}
	}

	@Override
	public void register() {
		
		// TODO: register new user (which, if successful, also logs them in)
		boolean success = true;
		// TODO: log in user
		if(getLoginView().getRegisterUsername().trim().equals("")||getLoginView().getRegisterPassword().trim().equals("")||
				getLoginView().getRegisterPasswordRepeat().trim().equals("")){
			getMessageView().setTitle("Registration Failed");
			getMessageView().setMessage("there are empty fields");
			getMessageView().showModal();
			return;
		}
		if(!getLoginView().getRegisterPassword().equals(getLoginView().getRegisterPasswordRepeat())){
			getMessageView().setTitle("Registration Failed");
			getMessageView().setMessage("passwords do not match");
			getMessageView().showModal();
			return;
		}
		
		try {
			ps.registerUser(new UserRegisterInput(getLoginView().getRegisterUsername(),
											getLoginView().getRegisterPassword()));
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			success = false;
		} catch(Exception e){
			getMessageView().setTitle("Registration Failed");
			getMessageView().setMessage(e.getMessage());
			getMessageView().showModal();
		}
		// If register succeeded
		if(success){
			getLoginView().closeModal();
			loginAction.execute();
			}
		else{
			getMessageView().setTitle("Registration Failed");
			getMessageView().setMessage("The submitted username-password already exists");
			getMessageView().showModal();
		}
	}

}

