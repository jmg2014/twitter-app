package org.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.twitter.command.Command;
import org.twitter.command.ExitCommand;
import org.twitter.factory.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Twitter app
 *
 * @author Jorge Manrique
 * @version 1.0
 */
public class TwitterConsole {

  @Autowired
  private CommandFactory factory;

  /**
   * Starting point.
   *
   * 
   */
  public void start() {

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


      } catch (RuntimeException ex) {
        throw ex;
      } catch (IOException ex) {
        System.out.println("ERROR, you should speak with the administrator.");
      }

    } while (!exitCommand.equals(command));

  }
}
