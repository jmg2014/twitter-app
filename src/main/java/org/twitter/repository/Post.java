package org.twitter.repository;

import java.time.LocalDateTime;

public class Post {

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
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((comment == null) ? 0 : comment.hashCode());
    result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
    result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
    Post other = (Post) obj;
    if (comment == null) {
      if (other.comment != null) {
        return false;
      }
    } else if (!comment.equals(other.comment)) {
      return false;
    }
    if (dateTime == null) {
      if (other.dateTime != null) {
        return false;
      }
    } else if (!dateTime.equals(other.dateTime)) {
      return false;
    }
    if (owner == null) {
      if (other.owner != null) {
        return false;
      }
    } else if (!owner.equals(other.owner)) {
      return false;
    }
    return true;
  }

}
