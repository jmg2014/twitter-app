package twitter.command;

/**
 * Default implementation when an user types a wrong command
 * 
 * @author Jorge Manrique
 * @version 1.0
 */
public class UnknownCommand implements Command {

	@Override
	public void execute() {
		System.out.println("UNKNOWN COMMAND");

	}

}
