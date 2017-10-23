package com.liang.helloworld;

import com.liang.constants.Constants;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Title : Recv
 * @Author : zhaoliang
 * @Date : Created in 17/10/23 上午10:29
 */
public class Recv {
    public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Constants.QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message=new String(body,"UTF-8");
                System.out.println("[x] Received '"+message+"'");
            }
        };
        channel.basicConsume(Constants.QUEUE_NAME,true,consumer);


    }
}
