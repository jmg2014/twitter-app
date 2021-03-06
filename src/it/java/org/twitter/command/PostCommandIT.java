package org.twitter.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.twitter.configuration.AppConfigTest;
import org.twitter.factory.CommandFactory;
import org.twitter.repository.Post;
import org.twitter.repository.TwitterRepository;
import org.twitter.repository.User;

import java.util.Optional;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfigTest.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PostCommandIT {

  @Autowired
  private CommandFactory factory;

  @Autowired
  private TwitterRepository repository;

  @Test
  public void create_user_when_first_posting_action() {

    // Given: the Post command
    Command command = factory.createCommand("user -> Today is a good day");

    // when: the command is executed
    command.execute();

    // then: it should be an user in the repository
    assertEquals(1, repository.countUsers());

  }

  @Test
  public void reuse_user_after_first_post() {

    // Given: two Post commands
    Command command = factory.createCommand("user -> Today is a good day");
    Command command2 = factory.createCommand("user -> Today is still a good day");

    // when: both Posts are executed
    command.execute();
    command2.execute();

    // then: it should be an user in the repository
    assertEquals(1, repository.countUsers());

  }

  @Test
  public void create_new_post_when_user_uses_post_command() {

    // Given: one command
    Command command = factory.createCommand("user -> Today is a good day");

    // when: both Post are executed
    command.execute();

    // then: the user should have one post
    Optional<User> result = repository.findUser("user");
    if (!result.isPresent()) {
      fail("The user is not in the repository");
    } else {
      Optional<Set<Post>> maybePost = result.get().getPosts();
      if (maybePost.isPresent()) {
        assertEquals(1, maybePost.get().size());
      } else {
        fail("The user does not have any post");
      }

    }
  }

  @Test
  public void add_post_to_its_followers_when_user_has_a_new_post() {

    // Given: Ana follows Bob
    Command alicePostCommand = factory.createCommand("Alice -> Today is a good day");
    alicePostCommand.execute();


    Command bobPostCommand = factory.createCommand("Bob -> Wales won yesterday! :)");
    bobPostCommand.execute();



    Command aliceFollowCommand = factory.createCommand("Alice follows Bob");
    aliceFollowCommand.execute();



    // when: Bob post a new message
    Command bobNewPostCommand = factory.createCommand("Bob -> Waiting for the next match");
    bobNewPostCommand.execute();

    // then: Alice should have the latest Bob's post
    Optional<User> result = repository.findUser("Alice");
    if (!result.isPresent()) {
      fail("The user is not in the repository");
    } else {
      Optional<Set<Post>> maybePost = result.get().getPosts();
      if (maybePost.isPresent()) {
        assertEquals(1, maybePost.get().size());
      } else {
        fail("The user does not have any post");
      }
      Optional<Set<Post>> maybeFolloweePost = result.get().getFolloweePosts();
      if (maybeFolloweePost.isPresent()) {
        assertEquals(2, maybeFolloweePost.get().size());
      } else {
        fail("The user does not have any post");
      }

    }
  }


}
