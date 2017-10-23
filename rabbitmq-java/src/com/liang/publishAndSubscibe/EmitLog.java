package com.liang.publishAndSubscibe;

import com.liang.constants.Constants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Title : ${FILE_NAME}
 * @Author : zhaoliang
 * @Date : Created in 17/10/23 上午11:08
 */
public class EmitLog {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        System.out.println(factory.getHost());
        System.out.println(factory.getPassword());
        System.out.println(factory.getUsername());
        System.out.println(factory.getShutdownTimeout());
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();


        channel.exchangeDeclare(Constants.EXCHANGE_NAME,"fanout");

        String message="Hello.world.info.next";
        channel.basicPublish(Constants.EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println("[x] sent '" +message+"'");

        channel.close();
        connection.close();
    }
}
