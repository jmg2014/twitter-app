package twitter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import twitter.Main;
import twitter.TwitterConsole;
import twitter.command.PostCommand;
import twitter.factory.CommandFactory;
import twitter.factory.CommandFactoryImpl;
import twitter.repository.TwitterRepository;
import twitter.repository.TwitterRepositoryImpl;

@Configuration
@ComponentScan("twitter.*")
public class AppConfig {

      
    @Bean
    public TwitterConsole twitterConsole() {
		return new TwitterConsole();
    }
   
   @Bean
    public Main main() {
		return new Main();
    }

	
   
}