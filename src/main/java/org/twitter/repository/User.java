package org.twitter.repository;

import org.twitter.observable.Follower;
import org.twitter.observable.UserObserver;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class User implements UserObserver, Follower {

  private String name;

  private Set<User> followers = new HashSet<User>();

  private Set<Post> posts = new TreeSet<Post>(Comparator.comparing(Post::getDateTime).reversed()
      .thenComparing(Post::getOwner).thenComparing(Post::getComment));


  public String getName() {
    return name;
  }

  public Set<User> getFollowers() {
    return followers;
  }



  public Optional<Set<Post>> getPosts() {
    return Optional.ofNullable(posts);
  }

  public void addPost(Post post) {
    posts.add(post);
  }

  public User(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    if (getClass() != obj.getClass()) {
      return false;
    }

    User other = (User) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }


  @Override
  public void addFollower(User follower) {
    followers.add(follower);
  }

  @Override
  public void notifyFollower(Post post) {
    for (User user : followers) {
      user.updatePost(post);
    }
  }

  @Override
  public void updatePost(Post post) {
    posts.add(post);

  }
}
