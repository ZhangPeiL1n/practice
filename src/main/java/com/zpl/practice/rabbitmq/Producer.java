package com.zpl.practice.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 张沛霖
 * @date 2020/11/12
 */
// @Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private void send() {

        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setMessageId("msg_id_1");
        Message message = messageConverter.toMessage(new Object(), messageProperties);
        rabbitTemplate.send("exchange", "routingKey", message);


        rabbitTemplate.convertAndSend("exchange", "routingKey", new Object());
    }


}
