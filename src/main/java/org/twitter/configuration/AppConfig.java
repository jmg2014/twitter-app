package org.twitter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.twitter.Main;
import org.twitter.TwitterConsole;
import org.twitter.factory.CommandFactory;
import org.twitter.repository.TwitterRepository;

@Configuration
@ComponentScan("org.twitter.*")
public class AppConfig {



  @Bean
  public TwitterRepository repository() {
    return new TwitterRepository();
  }

  @Bean
  public CommandFactory factory() {
    return new CommandFactory(repository());
  }

  @Bean
  public TwitterConsole twitterConsole() {
    return new TwitterConsole(factory());
  }

  @Bean
  public Main main() {
    return new Main();
  }



}
