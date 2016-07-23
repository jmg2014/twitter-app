package org.twitter

import org.twitter.command.ExitCommand
import org.twitter.command.WallCommand
import org.twitter.factory.CommandFactory
import org.twitter.repository.TwitterRepository

import spock.lang.Specification

class TwitterConsoleSpec extends Specification {

  TwitterConsole console
  private CommandFactory factory=Mock()
  private TwitterRepository repository=Mock()

  def setup() {
    console = new TwitterConsole(factory)
  }


  def "when an exit command is received the application should finish"() {

    setup:
    def BufferedReader reader = new BufferedReader(new StringReader("user exits"))

    when: "an exit command is executed"
    factory.createCommand("user exits") >> new ExitCommand()

    then: "the application should finish"
    console.console(reader) equals true
  }

  def "when the command is not an exit command the application should not finish"() {

    setup:
    def BufferedReader reader = new BufferedReader(new StringReader("user walls"))

    when: "an exit command is executed"
    factory.createCommand("user walls") >> new WallCommand("user", repository)
    repository.findUser("user") >> Optional.empty()

    then: "the application should finish"
    console.console(reader) equals false
  }
}
