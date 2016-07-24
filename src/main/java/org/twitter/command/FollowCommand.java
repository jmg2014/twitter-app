package org.twitter.command;

import org.twitter.repository.Post;
import org.twitter.repository.TwitterRepository;
import org.twitter.repository.User;

import java.util.Optional;
import java.util.Set;

/**
 * Implements the command: following: "user_name" follows "another_user"
 *
 * @author Jorge Manrique
 * @version 1.0
 */
public class FollowCommand implements Command {


  private String user;
  private String follower;
  private TwitterRepository repository;

  public FollowCommand(String user, String follower, TwitterRepository repository) {
    this.user = user;
    this.follower = follower;
    this.repository = repository;
  }

  @Override
  public void execute() {

    Optional<User> maybeUser = repository.findUser(user);
    Optional<User> maybeFollower = repository.findUser(follower);
    if (maybeUser.isPresent() && maybeFollower.isPresent()) {
      User user = maybeUser.get();
      User follower = maybeFollower.get();

      // Updating the follower
      follower.addFollower(user);

      // Updating all the posts from the follower to user
      Optional<Set<Post>> maybePost = follower.getPosts();

      maybePost.ifPresent(
          allPosts -> allPosts.stream().filter(post -> post.getOwner().equals(follower.getName()))
              .forEach(post -> user.addPost(post)));


    }

  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + FollowCommand.class.getSimpleName().hashCode();
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
