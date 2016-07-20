package org.twitter.repository;

import java.util.Optional;

/**
 * Repository of the application
 *
 * @author Jorge Manrique
 * @version 1.0
 */
public interface TwitterRepository {

  void saveUser(String user);

  void savePost(String user, Post post);

  Optional<User> findUser(String username);

  int countUsers();

}
