package com.liang.helloworld;

import com.liang.constants.Constants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Title : Send
 * @Author : zhaoliang
 * @Date : Created in 17/10/23 上午10:20
 */
public class Send {



    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        System.out.println(factory.getHost());
        System.out.println(factory.getPassword());
        System.out.println(factory.getUsername());
        System.out.println(factory.getShutdownTimeout());
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();


        channel.queueDeclare(Constants.QUEUE_NAME,false,false,false,null);
        String message="Hello world";
        channel.basicPublish("",Constants.QUEUE_NAME,null,message.getBytes());
        System.out.println("[x] sent '" +message+"'");

        channel.close();
        connection.close();
    }
}
