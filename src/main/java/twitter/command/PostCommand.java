package twitter.command;

import twitter.repository.Post;
import twitter.repository.TwitterRepository;

/**
 * Implements the command: posting: <user name> -> <message>
 * 
 * @author Jorge Manrique
 * @version 1.0
 */

public class PostCommand implements Command {

	private String user;
	private Post post;

	private TwitterRepository twitterRepository;

	public PostCommand(String user, Post post, TwitterRepository repository) {
		this.user = user;
		this.post = post;
		this.twitterRepository = repository;
	}

	@Override
	public void execute() {

		twitterRepository.savePost(user, post);

	}

}
