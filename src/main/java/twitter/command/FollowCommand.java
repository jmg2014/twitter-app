package twitter.command;

import java.util.Optional;
import java.util.Set;

import twitter.repository.Post;
import twitter.repository.TwitterRepository;
import twitter.repository.User;

/**
 * Implements the command: following: <user name> follows <another user>
 * 
 * @author Jorge Manrique
 * @version 1.0
 */
public class FollowCommand implements Command {

	
	private String user;	
	private String follower;
	private TwitterRepository repository;
	
	public FollowCommand(String user,String follower,TwitterRepository repository) {
		this.user=user;
		this.follower=follower;
		this.repository=repository;
	}
	
	@Override
	public void execute() {
		
		Optional<User> oUser=repository.findUser(user);
		Optional<User> oFollower=repository.findUser(follower);
		if (oUser.isPresent() && oFollower.isPresent()){
			User user=oUser.get();
			User follower=oFollower.get();
			
			//Updating the follower
			follower.addFollower(user);
			
			//Updating all the posts from the follower to user			
			Optional<Set<Post>> oPost=follower.getPosts();
			if (oPost.isPresent()){
			
				Set<Post> posts=oPost.get();
				for (Post post:posts){
										
					if (post.getOwner().equals(follower.getName())){
						user.addPost(post);
					}
				}
			}
			
			
		}

	}

}
