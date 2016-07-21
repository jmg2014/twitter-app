package twitter.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.twitter.factory.CommandFactory;
import org.twitter.repository.TwitterRepository;


@Configuration
// @ComponentScan("twitter.*")
public class AppConfigTest {


  @Bean
  public TwitterRepository repository() {
    return new TwitterRepository();
  }

  @Bean
  public CommandFactory factory() {
    return new CommandFactory(repository());
  }



}
