package org.twitter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.twitter.Main;
import org.twitter.TwitterConsole;

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
