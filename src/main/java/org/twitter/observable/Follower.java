package org.twitter.observable;

import org.twitter.repository.Post;
import org.twitter.repository.User;

/**
 * Interface to notify new posts to followers
 *
 * @author Jorge Manrique
 * @version 1.0
 */
public interface Follower {

  void addfollower(User user);

  void notifyFollower(Post post);

}
