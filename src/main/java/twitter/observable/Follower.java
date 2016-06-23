package twitter.observable;

import twitter.repository.Post;
import twitter.repository.User;

/**
 * Interface to notify new posts to followers
 * 
 * @author Jorge Manrique
 * @version 1.0
 */
public interface Follower {

	void addFollower(User user);

	void notifyFollower(Post post);

}
