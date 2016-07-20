package twitter.command;

import static org.junit.Assert.assertEquals;
import static twitter.util.TestHelper.delayBetweenCommand;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.twitter.command.Command;
import org.twitter.factory.CommandFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import twitter.configuration.AppConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfigTest.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class WallCommandIT {

  @Autowired
  private CommandFactory factory;

  @Test
  public void show_only_its_own_posts_when_is_not_following_others() {

    // Given: Alice has posted a message and is not following anyone
    Command postAlice = factory.createCommand("Alice -> Today is not a good day");
    postAlice.execute();

    delayBetweenCommand();

    Command postAlice2 = factory.createCommand("Alice -> England lost yesteday :(");
    postAlice2.execute();

    delayBetweenCommand();

    Command postBob = factory.createCommand("Bob -> Today is raining");
    postBob.execute();

    delayBetweenCommand();


    // When: Alice use wall command
    Command wallAlice = factory.createCommand("Alice");

    // Message method in WallCommand print in the console
    // in order to check what prints we can capture the console
    final ByteArrayOutputStream myConsole = new ByteArrayOutputStream();
    System.setOut(new PrintStream(myConsole));

    wallAlice.execute();

    // then: Alice has two posts
    String textConsole = myConsole.toString();

    String[] lines = textConsole.split("\r\n|\r|\n");
    assertEquals(2, lines.length);

  }


  @Test
  public void show_all_posts_when_a_user_is_following_others() {

    // Given: Alice, Bob and Charlie have posted several messages
    Command postAlice = factory.createCommand("Alice -> Today is not a good day");
    postAlice.execute();

    delayBetweenCommand();

    Command postAlice2 = factory.createCommand("Alice -> England lost yesteday :(");
    postAlice2.execute();

    delayBetweenCommand();

    Command postBob = factory.createCommand("Bob -> Waiting for the train.");
    postBob.execute();

    delayBetweenCommand();

    Command postCharlie = factory.createCommand("Charlie -> Today is raining");
    postCharlie.execute();

    delayBetweenCommand();

    // when: Charlie follows Alice and Bob
    Command followAlice = factory.createCommand("Charlie follows Alice");
    followAlice.execute();

    delayBetweenCommand();

    Command followBob = factory.createCommand("Charlie follows Bob");
    followBob.execute();


    // then: Charlie's wall should show all the posts
    Command wallAlice = factory.createCommand("Charlie wall");


    // Message method in WallCommand print in the console
    // in order to check what prints we can capture the console
    final ByteArrayOutputStream myConsole = new ByteArrayOutputStream();
    System.setOut(new PrintStream(myConsole));

    wallAlice.execute();

    String textConsole = myConsole.toString();

    String[] lines = textConsole.split("\r\n|\r|\n");
    assertEquals(4, lines.length);

  }



}
