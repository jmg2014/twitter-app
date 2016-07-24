package org.twitter.command;

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + UnknownCommand.class.getSimpleName().hashCode();
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
    return getClass() == obj.getClass();
  }

}
