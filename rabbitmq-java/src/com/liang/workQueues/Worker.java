package com.liang.workQueues;

import com.liang.constants.Constants;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Title : ${FILE_NAME}
 * @Author : zhaoliang
 * @Date : Created in 17/10/23 上午10:47
 */
public class Worker {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();

        channel.queueDeclare(Constants.TASK_QUEUE_NAME,true,false,false,null);

        System.out.println("[*] Waiting for messages . To exit press CTRL+C");

        channel.basicQos(1);

        final Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String message=new String(body,"UTF-8");
                System.out.println("[x] Received '"+message+"'");

                try {
                    doWork(message);
                } finally {
                    System.out.println(" [x] Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        boolean autoAck=false;
        channel.basicConsume(Constants.TASK_QUEUE_NAME,autoAck,consumer);

    }
    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
