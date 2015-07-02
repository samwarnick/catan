package shared.communication.input;

import java.util.logging.Level;

/**
 * This class contains the ChangeLogLevel method name and the desired level to be set.
 * @author Matt
 * 
 */
public class UtilChangeLogLevelInput extends Input {
	
	private Level level;
	
	public UtilChangeLogLevelInput(Level level) {
		super("/util/changeLogLevel");
		this.level = level;
	}

	public Level getLevel() {
		return level;
	}
	
}
