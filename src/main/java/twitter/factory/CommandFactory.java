package twitter.factory;

import twitter.command.Command;

public interface CommandFactory {
	
	Command createCommand(String line);
}
