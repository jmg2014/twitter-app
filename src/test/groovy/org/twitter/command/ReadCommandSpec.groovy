package org.twitter.command

import static org.junit.Assert.assertEquals

import org.twitter.repository.Post
import org.twitter.repository.TwitterRepository
import org.twitter.repository.User

import spock.lang.Specification

import java.time.LocalDateTime

class ReadCommandSpec extends Specification {


  def TwitterRepository repository
  def Post post,post2;

  def setup() {

    repository=new TwitterRepository()
    repository.saveUser("alice")
    repository.saveUser("bob")

    Optional<User> alice=repository.findUser("alice")
    post=new Post("alice","my message 01",LocalDateTime.now());
    alice.get().addPost(post)

    Optional<User> bob=repository.findUser("bob")
    post=new Post("bob","Bob is the best!",LocalDateTime.now());
    bob.get().addPost(post)
  }


  def "when Alice calls the Read command, she should get all her posts"(){

    ByteArrayOutputStream myConsole = new ByteArrayOutputStream();
    System.setOut(new PrintStream(myConsole));

    given: "Alice who is in the repository"

    String alice="alice"

    ReadCommand command= new ReadCommand(alice,repository);


    when: "Alice calls Read Command"
    command.execute();
    Optional<User> maybeUser= repository.findUser(alice);

    then: "Alice should have her posts"
    String textConsole = myConsole.toString();

    String[] lines = textConsole.split("\r\n");
    assertEquals(1, lines.length);
    textConsole.contains("my message 01")
  }
}
