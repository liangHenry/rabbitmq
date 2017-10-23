package com.liang.workQueues;

import com.liang.constants.Constants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Title : NewTask.java
 * @Author : zhaoliang
 * @Date : Created in 17/10/23 上午10:44
 */
public class NewTask {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        System.out.println(factory.getHost());
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();


        channel.queueDeclare(Constants.TASK_QUEUE_NAME,true,false,false,null);
        String message="Hello.world.info.mark.rabbit.";
        channel.basicPublish("",Constants.TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        System.out.println("[x] sent '" +message+"'");

        channel.close();
        connection.close();
    }
}
