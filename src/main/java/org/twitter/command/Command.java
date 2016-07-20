package org.twitter.command;

/**
 * Define a common interface to define all the possible commands for our twitter app.
 *
 * @author Jorge Manrique
 * @version 1.0
 */
public interface Command {

  void execute();
}
