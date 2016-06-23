package twitter.command;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import twitter.repository.Post;
import twitter.repository.TwitterRepository;
import twitter.repository.User;
import twitter.util.TimeHelper;

/**
 * Implements the command: 
 * reading: <user name> 
 * If the user is not in the system yet, the command does not show anything.
 * 
 * @author Jorge Manrique
 * @version 1.0
 */
public class ReadCommand implements Command, DisplayMessage {

	private String user;

	private TwitterRepository repository;

	public ReadCommand(String user, TwitterRepository repository) {
		this.user = user;
		this.repository = repository;
	}

	@Override
	public void message(Set<Post> message) {

		message.stream().filter(p -> p.getOwner().equals(user)).forEach(post -> System.out
				.println(post.getComment() + " " + TimeHelper.difference(post.getDateTime(), LocalDateTime.now())));

	}

	@Override
	public void execute() {

		Optional<User> result = repository.findUser(user);
		if (result.isPresent()) {
			User user = result.get();
			Optional<Set<Post>> oPosts = user.getPosts();
			if (oPosts.isPresent()) {
				message(oPosts.get());
			}
		}
	}

}
