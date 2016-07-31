package org.twitter.command

import org.twitter.repository.Post
import org.twitter.repository.TwitterRepository
import org.twitter.repository.User

import spock.lang.Specification

import java.time.LocalDateTime

class FollowCommandSpec extends Specification {


  private TwitterRepository repository
  private Post post;

  def setup() {

    repository=new TwitterRepository()
    repository.saveUser("alice")
    repository.saveUser("bob")

    Optional<User> bob=repository.findUser("bob")
    post=new Post("bob","my message",LocalDateTime.now());
    bob.get().addPost(post)
  }


  def "when Alice follows Bob , she should get all the Bob's posts"(){


    given: "Alice who is in the repository"

    String alice="alice"

    and: "Bob who is also in the repository"

    String bob="bob"

    and: "Alice follows Bob"

    Command command= new FollowCommand(alice, bob,repository);


    when: "Alice follows Bob"
    command.execute();
    Optional<User> maybeUser= repository.findUser(alice);

    then: "Alice sholud have all Bob's posts"


    maybeUser.get().getFolloweePosts().get().size()== 1
    maybeUser.get().getFolloweePosts().get().contains(post);
  }
}
