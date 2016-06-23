package twitter.command;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import twitter.repository.Post;
import twitter.repository.TwitterRepository;
import twitter.repository.User;
import twitter.util.TimeHelper;

/**
 * Implements the command: wall: <user name> wall
 * 
 * @author Jorge Manrique
 * @version 1.0
 */
public class WallCommand implements Command, DisplayMessage {

	private String user;

	private TwitterRepository repository;
	
	public WallCommand(String user,TwitterRepository repository) {
		this.user=user;
		this.repository=repository;
	}

	@Override
	public void message(Set<Post> message) {
		message.stream().forEach(post->System.out.println(post.getOwner()+" - "+post.getComment()+" "+TimeHelper.difference(post.getDateTime(),LocalDateTime.now())));

	}

	@Override
	public void execute() {
				
		Optional<User> oUser=repository.findUser(user);
		
		if (oUser.isPresent()){
			
			User user=oUser.get();
			Optional<Set<Post>> oPost=user.getPosts();
			if (oPost.isPresent()){
				message(oPost.get());
			}
			
			
		}
	

	}

}
