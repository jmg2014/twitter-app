package org.twitter.observable;

import org.twitter.repository.Post;

/**
 * Interface to update the post of a follower
 *
 * @author Jorge Manrique
 * @version 1.0
 */
public interface UserObserver {

  void updatePost(Post post);
}
