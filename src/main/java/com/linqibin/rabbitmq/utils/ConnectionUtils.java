package com.linqibin.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {

    public static Connection getConnection() throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 配置工厂参数
        connectionFactory.setHost("192.168.142.128");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("youshop");
        connectionFactory.setPassword("youshop");
        connectionFactory.setVirtualHost("youshop");

        // 获取连接
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}
