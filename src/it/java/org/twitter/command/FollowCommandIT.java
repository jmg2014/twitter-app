package org.twitter.command;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.twitter.command.Command;
import org.twitter.configuration.AppConfigTest;
import org.twitter.factory.CommandFactory;
import org.twitter.repository.TwitterRepository;
import org.twitter.repository.User;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfigTest.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class FollowCommandIT {

  @Autowired
  private CommandFactory factory;

  @Autowired
  private TwitterRepository repository;

  @Test
  public void add_follower_when_user_uses_follow_action() {

    // Given: Alice and Bob have post a message
    Command postAlice = factory.createCommand("Alice -> Today is a good day");
    postAlice.execute();
    Command postBob = factory.createCommand("Bob -> Today is raining");
    postBob.execute();

    // When: Alice follows Bob
    Command aliceFfollowBob = factory.createCommand("Alice follows Bob");
    aliceFfollowBob.execute();

    // then: Bob should have Alice as a follower
    Optional<User> bob = repository.findUser("Bob");
    assertEquals(1, bob.get().getFollowers().size());
    assertEquals(true, bob.get().getFollowers().contains(repository.findUser("Alice").get()));

  }

  @Test
  public void add_follower_only_one() {

    // Given: Alice and Bob have posted a message and Alice follows Bob
    Command postAlice = factory.createCommand("Alice -> Today is a good day");
    postAlice.execute();
    Command postBob = factory.createCommand("Bob -> Today is raining");
    postBob.execute();

    // When: Alice follows Bob again
    Command aliceFfollowBob = factory.createCommand("Alice follows Bob");
    aliceFfollowBob.execute();
    aliceFfollowBob.execute();

    // then: Bob shouldn't have Alice twice as a follower
    Optional<User> bob = repository.findUser("Bob");
    assertEquals(1, bob.get().getFollowers().size());

  }

  @Test
  public void add_post_when_user_follows_another_user() {

    // Given: Alice and Bob have posted a message
    Command postAlice = factory.createCommand("Alice -> Today is a good day");
    postAlice.execute();


    // Sleep the test to simulate time between different post messages
    // If the time is exactly the same, Alice will not copy the post from Bob
    try {
      Thread.sleep(10);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }

    Command postBob = factory.createCommand("Bob -> Today is raining");
    postBob.execute();

    // When: Alice follows Bob
    Command aliceFfollowBob = factory.createCommand("Alice follows Bob");
    aliceFfollowBob.execute();


    // then: Alice should have the Bob's post
    Optional<User> alice = repository.findUser("Alice");
    assertEquals(2, alice.get().getPosts().get().size());

  }


}
