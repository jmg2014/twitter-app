package org.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.twitter.configuration.AppConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class Main {

  @Autowired
  private TwitterConsole twitterConsole;

  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    Main main = ctx.getBean(Main.class);
    main.start();

  }


  private void start() {

    boolean exit = false;

    while (!exit) {

      System.out.print("> ");

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

      exit = twitterConsole.console(bufferedReader);
    }
  }
}


