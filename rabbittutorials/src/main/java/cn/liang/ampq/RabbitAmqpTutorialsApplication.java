package cn.liang.ampq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitAmqpTutorialsApplication {

    @Profile("usage_message")
    @Bean
    public CommandLineRunner usage() {
        System.out.println("CommandLineRunner usage");
        return new CommandLineRunner() {
            @Override
            public void run(String... arg0) throws Exception {
                System.out.println("This app uses Spring Profiles to  control its behavior.\n");
                System.out.println("Sample usage: java -jar  rabbit-tutorials.jar  --spring.profiles.active=hello-world,sender");
            }
        };
    }

    @Profile("!usage_message")
    @Bean
    public CommandLineRunner tutorial() {
        System.out.println("CommandLineRunner tutorial");
        return new RabbitAmqpTutorialsRunner();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RabbitAmqpTutorialsApplication.class, args);
        //java -jar  --spring.profiles.active=hello-world,sender
        //java -jar  --spring.profiles.active=hello-world,receiver

        //java -jar  --spring.profiles.active=work-queues,receiver
        //java -jar  --spring.profiles.active=work-queues,sender

        //java -jar  --spring.profiles.active=pub-sub,receiver --tutorial.client.duration=60000
        //java -jar  --spring.profiles.active=pub-sub,sender --tutorial.client.duration=60000

        //java -jar  --spring.profiles.active=routing,receiver --tutorial.client.duration=60000
        //java -jar  --spring.profiles.active=routing,sender --tutorial.client.duration=60000


        //java -jar  --spring.profiles.active=topics,receiver --tutorial.client.duration=60000
        //java -jar  --spring.profiles.active=topics,sender --tutorial.client.duration=60000

        //java -jar  --spring.profiles.active=rpc,server  --tutorial.client.duration=6000


        //java -jar --spring.profiles.active=rpc,server
        //java -jar --spring.profiles.active=rpc,client
    }
}