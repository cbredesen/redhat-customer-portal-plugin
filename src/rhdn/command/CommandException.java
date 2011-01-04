package rhdn.command;

/**
 * An exception thrown during command processing.  The message is intended to be presentable to 
 * the user.
 * 
 * @author Chris Bredesen
 */
public class CommandException extends Exception {

	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandException(String message) {
		super(message);
	}

}
