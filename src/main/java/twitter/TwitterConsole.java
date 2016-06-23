package twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;

import twitter.command.Command;
import twitter.command.ExitCommand;
import twitter.factory.CommandFactory;

/**
 * Twitter app
 * 
 * @author Jorge Manrique
 * @version 1.0
 */
public class TwitterConsole {

	@Autowired
	private CommandFactory factory;

	public void start(String[] args) {

		Command command = null;
		Command exitCommand = new ExitCommand();

		do {
			try {
				System.out.print("> ");
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				String line = null;

				line = bufferedReader.readLine();
				
				command = factory.createCommand(line);
				
				command.execute();
				
				
			} catch (RuntimeException e) {
				throw e;
			} catch (IOException e) {
				System.out.println("ERROR, you should speak with the administrator.");
			}

		} while (!exitCommand.equals(command));

	}
}