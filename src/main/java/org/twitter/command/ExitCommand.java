package org.twitter.command;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This command is used to close the Twitter app
 *
 * @author Jorge Manrique
 * @version 1.0
 *
 */
public class ExitCommand implements Command {

  private static final Logger LOGGER = Logger.getLogger(ExitCommand.class.getSimpleName());

  @Override
  public void execute() {
    LOGGER.log(Level.INFO, "The application will be closed.");
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ExitCommand.class.getSimpleName().hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    return getClass() != obj.getClass();
  }
}
