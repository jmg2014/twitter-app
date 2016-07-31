package org.twitter.command;

import org.twitter.repository.Post;
import org.twitter.repository.TwitterRepository;
import org.twitter.repository.User;
import org.twitter.util.TimeHelper;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

/**
 * Implements the command: reading: "user_name" If the user is not in the system yet, the command
 * does not show anything.
 *
 * @author Jorge Manrique
 * @version 1.0
 */
public class ReadCommand implements Command, DisplayMessage {

  private String user;

  private TwitterRepository repository;

  public ReadCommand(String user, TwitterRepository repository) {
    this.user = user;
    this.repository = repository;
  }

  @Override
  public void message(Set<Post> message) {

    message.stream().forEach(post -> System.out.println(
        post.getComment() + " " + TimeHelper.difference(post.getDateTime(), LocalDateTime.now())));

  }


  @Override
  public void execute() {

    Optional<User> result = repository.findUser(user);
    result.ifPresent(user -> user.getPosts().ifPresent(post -> message(post)));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ReadCommand.class.getSimpleName().hashCode();
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
