package twitter.command;

import java.util.Set;

import twitter.repository.Post;

/**
 * Interface that should be implemented for a command in case that needs to display a message in the console
 * @author Jorge Manrique
 * @version 1.0
 */
public interface DisplayMessage {
	
	public void message(Set<Post> message);

}
