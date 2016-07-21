package org.twitter.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class TwitterRepository {

  private Map<String, User> users = new HashMap<String, User>();

  public TwitterRepository() {}


  public void saveUser(String user) {
    users.put(user, new User(user));
  }


  public void savePost(String username, Post post) {

    User user = null;

    if (!users.containsKey(username)) {

      user = new User(username);

      users.put(username, user);

    } else {

      user = users.get(username);

    }

    user.addPost(post);

    // Notify to followers the new post
    user.notifyFollower(post);

  }


  public Optional<User> findUser(String username) {

    User user = users.get(username);
    return Optional.ofNullable(user);

  }


  public int countUsers() {
    return users.size();
  }

}
