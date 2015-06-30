package shared.communication.input;

public class GamesLoadInput extends Input {

	private String fileName;

	public GamesLoadInput(String fileName) {
		super("/games/load");
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
	
}
