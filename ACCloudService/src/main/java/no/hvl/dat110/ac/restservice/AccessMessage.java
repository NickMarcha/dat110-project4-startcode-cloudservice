package no.hvl.dat110.ac.restservice;

public class AccessMessage {
	
	// utility class that can be used for representing messages
	// sent using JSON
	
	private String message;
	
	public AccessMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
