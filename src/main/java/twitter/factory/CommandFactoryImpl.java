package twitter.factory;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import twitter.command.Command;
import twitter.command.ExitCommand;
import twitter.command.FollowCommand;
import twitter.command.PostCommand;
import twitter.command.ReadCommand;
import twitter.command.UnknownCommand;
import twitter.command.WallCommand;
import twitter.repository.Post;
import twitter.repository.TwitterRepository;

@Component
public class CommandFactoryImpl implements CommandFactory{
	
	private static int USER_TOKEN=0;
	private static int MESSAGE_TOKEN=1;
	private static int FOLLOWER_TOKEN=2;
	

	@Autowired
	private TwitterRepository repository;
	
	@Override
	public Command createCommand(String line) {
		
		
		if ("".equals(line)){ return new UnknownCommand();}
		
		String[] tokens=line.split(" ");
		if (tokens.length ==1){
			return new ReadCommand(tokens[USER_TOKEN].trim(),repository);
		}
		if (tokens[1].equals("->")){
			
			String[] postCommandtokens=line.split("->");
			return new PostCommand(postCommandtokens[USER_TOKEN].trim(),new Post(postCommandtokens[USER_TOKEN].trim(),postCommandtokens[MESSAGE_TOKEN],LocalDateTime.now()),repository);
		}
		if (tokens[1].equals("follows")){
			return new FollowCommand(tokens[USER_TOKEN].trim(),tokens[FOLLOWER_TOKEN],repository);
		}
		if (tokens[1].equals("wall")){
			return new WallCommand(tokens[USER_TOKEN].trim(),repository);
		}
		if (tokens[1].equals("exit")){
			return new ExitCommand();
		}
		return new UnknownCommand();
	}

}
