package org.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.twitter.command.Command;
import org.twitter.command.ExitCommand;
import org.twitter.factory.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Twitter app
 *
 * @author Jorge Manrique
 * @version 1.0
 */
public class TwitterConsole {


  private CommandFactory factory;

  @Autowired
  public TwitterConsole(CommandFactory factory) {
    this.factory = factory;
  }

  /**
   * Process the console.
   *
   * 
   */
  public boolean console(BufferedReader bufferedReader) {


    boolean exit = false;

    Command command = null;

    Command exitCommand = new ExitCommand();


    try {


      String line = bufferedReader.readLine();

      command = factory.createCommand(line);

      command.execute();


      if (exitCommand.equals(command)) {
        exit = true;
      }


    } catch (RuntimeException ex) {

      throw ex;

    } catch (IOException ex) {
      System.out.println("ERROR, you should speak with the administrator.");

    }


    return exit;
  }

}
