package org.twitter.factory

import org.twitter.command.Command
import org.twitter.command.ExitCommand
import org.twitter.command.FollowCommand
import org.twitter.command.PostCommand
import org.twitter.command.ReadCommand
import org.twitter.command.UnknownCommand
import org.twitter.command.WallCommand
import org.twitter.repository.Post
import org.twitter.repository.TwitterRepository

import spock.lang.Specification

import java.time.LocalDateTime

class CommnadFactorySpec extends Specification {


  private CommandFactory factory
  private TwitterRepository repository=Mock()

  def setup() {
    factory = new CommandFactory(repository)
  }


  def "when the factory recieves an empty line,  it should return this type of command"() {


    when: "the factory is called"
    Command command=factory.createCommand("")

    then: "the factory should return an Unknown command"
    assert command.equals( new UnknownCommand())
  }

  def "when the factory recieves a Wall command,  it should return this type of command"() {

    when: "the factory is called"
    Command command=factory.createCommand("user wall")

    then: "the factory should return a Wall command"
    assert command.equals( new WallCommand("user", repository))
  }

  def "when the factory recieves a Follow command, it should return this type of command"() {

    when: "the factory is called"
    Command command=factory.createCommand("user1 follows user2")

    then: "the factory should return a Wall command"
    assert command.equals( new FollowCommand("user1", "user2", repository))
  }

  def "when the factory recieves a Exit command, it should return this type of command"() {

    when: "the factory is called"
    Command command=factory.createCommand("user exit")

    then: "the factory should return a Wall command"
    assert command.equals( new ExitCommand())
  }

  def "when the factory recieves a Read command, it should return this type of command"() {

    when: "the factory is called"
    Command command=factory.createCommand("user")

    then: "the factory should return a Wall command"
    assert command.equals( new ReadCommand("user", repository))
  }

  def "when the factory recieves a Post command, it should return this type of command"() {

    when: "the factory is called"
    Command command=factory.createCommand("user -> message")

    then: "the factory should return a Wall command"
    assert command.equals( new PostCommand("user", new Post("user","comment", LocalDateTime.now()), repository))
  }

  def "when the factory recieves an Unknown command, it should return this type of command"() {

    when: "the factory is called"
    Command command=factory.createCommand("user mycommand")

    then: "the factory should return a Wall command"
    assert command.equals( new UnknownCommand())
  }
}
