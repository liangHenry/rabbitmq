package com.liang.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Title : ${FILE_NAME}
 * @Author : zhaoliang
 * @Date : Created in 17/10/23 上午11:23
 */
public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String severity = "error";//"info", "warning", "error"
        String message="Hello World";
        channel.basicPublish(EXCHANGE_NAME,severity,null,message.getBytes());
        System.out.println(" [x] Sent "+severity+" : "+message);

        channel.close();
        connection.close();

    }
}
