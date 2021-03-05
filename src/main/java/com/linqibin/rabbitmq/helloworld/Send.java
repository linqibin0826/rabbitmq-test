package com.linqibin.rabbitmq.helloworld;

import com.linqibin.rabbitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Send {

    /**
     * 消息队列名称
     */
    public static final String QUEUE_NAME = "hello_world";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 获取连接
        Connection connection = ConnectionUtils.getConnection();
        // 2. 获取通道
        Channel channel = connection.createChannel();

        // 3. 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4. 发送消息
        String msg = "hello world!";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
        System.out.println(" [x] Sent '" + msg + "'");

        // 5. 关闭通道和连接
        channel.close();
        connection.close();

    }
}
