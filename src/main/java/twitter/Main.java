package twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import twitter.configuration.AppConfig;

@Component
public class Main {

    public static void main(String[] args) {
    	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);    			   

        Main p = ctx.getBean(Main.class);
        p.start(args);
        
    }

    @Autowired
    private TwitterConsole twitterConsole;
    
    private void start(String[] args) {
        twitterConsole.start(args);
    }
}



