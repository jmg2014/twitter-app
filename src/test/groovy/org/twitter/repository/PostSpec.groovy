package org.twitter.repository

import spock.lang.Specification

import java.time.LocalDateTime

class PostSpec extends Specification {


  def "when two posts are the same object,  it should return true"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);

    when: "check equals"
    boolean result=post.equals(post)

    then: "should return true"
    result
  }


  def "when two posts are the same,  it should return true"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);
    Post post2= new Post("alice","alice's post",localDate);

    when: "check equals"
    boolean result=post.equals(post2)

    then: "should return true"
    result
  }

  def "when the second post is null,  it should return false"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);


    when: "check equals"
    boolean result=post.equals(null)

    then: "should return false"
    !result
  }

  def "when the owner is null,  it should return false"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);
    Post post2= new Post(null,"alice's post",localDate);

    when: "check equals"
    boolean result=post.equals(post2)

    then: "should return false"
    !result
  }

  def "when the message is null,  it should return false"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);
    Post post2= new Post("alice",null,localDate);

    when: "check equals"
    boolean result=post.equals(post2)

    then: "should return false"
    !result
  }


  def "when the time is null,  it should return false"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);
    Post post2= new Post("alice","alice's post",null);

    when: "check equals"
    boolean result=post.equals(post2)

    then: "should return false"
    !result
  }

  def "when there are different types,  it should return false"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);

    when: "check equals"
    boolean result=post.equals(new User("alice"))

    then: "should return false"
    !result
  }
  def "when two posts have different user,  it should return false"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);
    Post post2= new Post("bob","alice's post",localDate);

    when: "check equals"
    boolean result=post.equals(post2)

    then: "should return false"
    !result
  }

  def "when two posts have different message,  it should return false"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);
    Post post2= new Post("alice","sunny day",localDate);

    when: "check equals"
    boolean result=post.equals(post2)

    then: "should return false"
    !result
  }

  def "when two posts have different time,  it should return false"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);
    Post post2= new Post("alice","alice's post",localDate.plusSeconds(5));

    when: "check equals"
    boolean result=post.equals(post2)

    then: "should return false"
    !result
  }


  def "when two posts are the same ,  they should have the same hascode"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);
    Post post2= new Post("alice","alice's post",localDate);

    when: "check hashcode"


    then: "should return true"
    post.hashCode()==post2.hashCode()
  }

  def "when two posts are different ,  they should have a different hascode"() {

    LocalDateTime localDate;

    given: "two post with the same infomration"
    localDate=LocalDateTime.now();
    Post post= new Post("alice","alice's post",localDate);
    Post post2= new Post("alice","alice's post2",localDate);

    when: "check hashcode"


    then: "should return true"
    post.hashCode()!=post2.hashCode()
  }
}
