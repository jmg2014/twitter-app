package org.twitter.factory;

import org.springframework.stereotype.Component;
import org.twitter.command.Command;
import org.twitter.command.ExitCommand;
import org.twitter.command.FollowCommand;
import org.twitter.command.PostCommand;
import org.twitter.command.ReadCommand;
import org.twitter.command.UnknownCommand;
import org.twitter.command.WallCommand;
import org.twitter.repository.Post;
import org.twitter.repository.TwitterRepository;

import java.time.LocalDateTime;

@Component
public class CommandFactory {

  private static int USER_TOKEN = 0;
  private static int MESSAGE_TOKEN = 1;
  private static int FOLLOWER_TOKEN = 2;
  private static String COMMNAD_WALL = "wall";
  private static String COMMAND_FOLLOW = "follows";
  private static String COMMAND_EXIT = "exit";
  private static String TOKEN_ACTION = "->";
  private TwitterRepository repository;

  public CommandFactory() {};

  public CommandFactory(TwitterRepository repository) {
    this.repository = repository;
  }

  /**
   * Create a new command.
   *
   * @param line with the command
   * @return the new command
   */
  public Command createCommand(String line) {


    if ("".equals(line)) {
      return new UnknownCommand();
    }

    String[] tokens = line.split(" ");
    if (tokens.length == 1) {
      return new ReadCommand(tokens[USER_TOKEN].trim(), repository);
    }

    if (tokens[1].equals(TOKEN_ACTION)) {

      String[] postCommandtokens = line.split(TOKEN_ACTION);
      String owner = postCommandtokens[USER_TOKEN].trim();
      String message = postCommandtokens[MESSAGE_TOKEN];

      return new PostCommand(postCommandtokens[USER_TOKEN].trim(),
          new Post(owner, message, LocalDateTime.now()), repository);
    }

    String user = tokens[USER_TOKEN].trim();

    if (tokens[1].equals(COMMAND_FOLLOW)) {
      String follower = tokens[FOLLOWER_TOKEN];
      return new FollowCommand(user, follower, repository);
    }
    if (tokens[1].equals(COMMNAD_WALL)) {
      return new WallCommand(user, repository);
    }
    if (tokens[1].equals(COMMAND_EXIT)) {
      return new ExitCommand();
    }
    return new UnknownCommand();
  }

}
