package shared.model.bank;

@SuppressWarnings("serial")
public class BankException extends Exception {
	
	public BankException(){
		return;
	}
	
	public BankException(String message){
		super(message);
	}
	
	public BankException(Throwable t){
		super(t);
	}
	
	public BankException(String message, Throwable t){
		super(message, t);
	}

}
