package cn.liang.ampq.workQueue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut2", "work-queues"})
@Configuration
public class Tut2Config {

    @Bean
    public Queue hello() {
        return new Queue("work-queue");
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Tut2Receiver receiver1() {
            System.out.println("======receiver1-------");

            return new Tut2Receiver(1);
        }

        @Bean
        public Tut2Receiver receiver2() {
            System.out.println("====receiver2-----");

            return new Tut2Receiver(2);
        }
    }

    @Profile("sender")
    @Bean
    public Tut2Sender sender() {
        System.out.println("====sender----");
        return new Tut2Sender();
    }
}
