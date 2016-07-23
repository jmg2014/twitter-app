package org.twitter.command;

import org.twitter.repository.Post;

import java.util.Set;

/**
 * Interface that should be implemented for a command in case that needs to display a message in the
 * console
 *
 * @author Jorge Manrique
 * @version 1.0
 */
public interface DisplayMessage {

  void message(Set<Post> message);

}
