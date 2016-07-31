package org.twitter.command;

import org.twitter.repository.Post;
import org.twitter.repository.TwitterRepository;
import org.twitter.repository.User;
import org.twitter.util.TimeHelper;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implements the command: wall: "user_name" wall
 *
 * @author Jorge Manrique
 * @version 1.0
 */
public class WallCommand implements Command, DisplayMessage {

  private String user;

  private TwitterRepository repository;

  private Comparator<Post> comparator = Comparator.comparing(Post::getDateTime).reversed()
      .thenComparing(Post::getOwner).thenComparing(Post::getComment);

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
      Optional<Set<Post>> maybeFolloweePost = user.getFolloweePosts();


      TreeSet<Post> posts = new TreeSet<Post>(comparator);


      if (maybeFolloweePost.isPresent()) {
        posts.addAll(maybeFolloweePost.get());
      }
      if (maybePost.isPresent()) {
        posts.addAll(maybePost.get());
      }

      message(posts);


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
    return getClass() == obj.getClass();
  }

}
