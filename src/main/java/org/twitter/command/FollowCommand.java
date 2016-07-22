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
      if (maybePost.isPresent()) {

        Set<Post> posts = maybePost.get();
        for (Post post : posts) {

          if (post.getOwner().equals(follower.getName())) {
            user.addPost(post);
          }
        }
      }


    }

  }

}