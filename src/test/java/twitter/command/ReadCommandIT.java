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
public class ReadCommandIT {

  @Autowired
  private CommandFactory factory;


  @Test
  public void show_only_its_own_posts() {

    // Given: Alice and Bob have posted a message and Alice follows Bob
    Command postAlice = factory.createCommand("Alice -> Today is a good day");
    postAlice.execute();

    delayBetweenCommand();
    Command postAlice2 = factory.createCommand("Alice -> England lost yesteday :(");
    postAlice2.execute();

    // Sleep the test to simulate time between different post messages
    // If the time is exactly the same, Alice will not copy the post from Bob
    delayBetweenCommand();

    Command postBob = factory.createCommand("Bob -> Today is raining");
    postBob.execute();

    Command aliceFfollowBob = factory.createCommand("Alice follows Bob");
    aliceFfollowBob.execute();


    // When: Alice use read command
    Command readAlicePosts = factory.createCommand("Alice");

    // Message method in ReadCommand print in the console
    // in order to check what prints we can capture the console
    final ByteArrayOutputStream myConsole = new ByteArrayOutputStream();
    System.setOut(new PrintStream(myConsole));

    readAlicePosts.execute();


    // then: Alice should have two posts
    String textConsole = myConsole.toString();

    String[] lines = textConsole.split("\r\n|\r|\n");
    assertEquals(2, lines.length);



  }



}
