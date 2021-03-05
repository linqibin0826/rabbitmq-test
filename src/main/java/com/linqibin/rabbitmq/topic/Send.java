package com.linqibin.rabbitmq.topic;

import com.linqibin.rabbitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
    private final static String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 声明exchange，指定类型为topic
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 消息内容
        String insert = "新增商品 : id = 1001";
        String update = "更新商品 : id = 1002";
        String delete = "删除商品 : id = 1003";

        // 发送消息，并且指定routing key 为：insert ,代表新增商品
        channel.basicPublish(EXCHANGE_NAME, "item.insert", null, insert.getBytes());
        System.out.println(" [商品服务：] Sent '" + insert + "'");
        channel.basicPublish(EXCHANGE_NAME, "item.update", null, update.getBytes());
        System.out.println(" [商品服务：] Sent '" + update + "'");
        channel.basicPublish(EXCHANGE_NAME, "item.delete", null, delete.getBytes());
        System.out.println(" [商品服务：] Sent '" + delete + "'");

        channel.close();
        connection.close();
    }
}
