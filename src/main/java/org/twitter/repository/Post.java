package org.twitter.repository;

import java.time.LocalDateTime;

public class Post implements Comparable<Post> {

  private String owner;
  private String comment;
  private LocalDateTime dateTime;

  public String getComment() {
    return comment;
  }


  public LocalDateTime getDateTime() {
    return dateTime;
  }



  public Post(String owner, String comment, LocalDateTime dateTime) {
    this.owner = owner;
    this.comment = comment;
    this.dateTime = dateTime;
  }


  public String getOwner() {
    return owner;
  }


  @Override
  public int compareTo(Post post) {
    return post.getDateTime().compareTo(this.getDateTime());
  }

}
