package org.twitter.command;

import org.twitter.repository.Post;
import org.twitter.repository.TwitterRepository;
import org.twitter.repository.User;
import org.twitter.util.TimeHelper;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

/**
 * Implements the command: wall: "user_name" wall
 *
 * @author Jorge Manrique
 * @version 1.0
 */
public class WallCommand implements Command, DisplayMessage {

  private String user;

  private TwitterRepository repository;

  public WallCommand(String user, TwitterRepository repository) {
    this.user = user;
    this.repository = repository;
  }

  @Override
  public void message(Set<Post> message) {
    message.stream().forEach(post -> System.out.println(post.getOwner() + " - " + post.getComment()
        + " " + TimeHelper.difference(post.getDateTime(), LocalDateTime.now())));

  }

  @Override
  public void execute() {

    Optional<User> maybeUser = repository.findUser(user);

    if (maybeUser.isPresent()) {

      User user = maybeUser.get();
      Optional<Set<Post>> maybePost = user.getPosts();
      if (maybePost.isPresent()) {
        message(maybePost.get());
      }

    }

  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + WallCommand.class.getSimpleName().hashCode();
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
    if (getClass() != obj.getClass()) {
      return false;
    }
    return true;
  }

}
