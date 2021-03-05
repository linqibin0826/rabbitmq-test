package com.linqibin.rabbitmq.work;

import com.linqibin.rabbitmq.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive2 {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(com.linqibin.rabbitmq.helloworld.Send.QUEUE_NAME, false, false, false, null);

        // 设置每个消费者同时只能预先请求一条消息,只有确认后才能处理下一条
        channel.basicQos(1);

        // 定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 获取消息,并且处理,这个方法类似监听事件, 如果有消息的时候, 会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println(" [2] received : " + msg + "!");
                // 手动确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // 监听队列
        channel.basicConsume(Send.QUEUE_NAME, false, consumer);
    }
}
