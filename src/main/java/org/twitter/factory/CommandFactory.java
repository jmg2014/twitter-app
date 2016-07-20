package org.twitter.factory;

import org.twitter.command.Command;

public interface CommandFactory {

  Command createCommand(String line);
}
