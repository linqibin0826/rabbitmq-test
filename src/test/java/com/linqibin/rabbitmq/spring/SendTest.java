package com.linqibin.rabbitmq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SendTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() {
        String msg = "hello spring-amqp";
        // 如果不指定交换机, 则会向默认的交换机发送消息
        rabbitTemplate.convertAndSend("spring.test.exchange", "hello.world", msg);
    }
}
