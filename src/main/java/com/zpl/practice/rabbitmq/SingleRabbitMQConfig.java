package com.zpl.practice.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.MessageFormat;

/**
 * 多 RabbitMQ 与多 ActiveMQ 的思路类似
 * 1. 根据不同的连接配置创建不同的连接工厂
 * 2. 使用需要的连接工厂创建 RabbitTemplate（生产者、消费者拉模式需要） 和 RabbitListenerContainerFactory（消费者推模式需要）
 * 并交给 spring 管理，通过不同的 beanName 区分
 * 3. 使用时根据 beanName 区分进行注入
 *
 * @author 张沛霖
 * @date 2020/11/12
 */
@Configuration
public class SingleRabbitMQConfig {

    private final Logger logger = LoggerFactory.getLogger(SingleRabbitMQConfig.class);
    @Autowired
    CachingConnectionFactory factory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);

        rabbitTemplate.setMandatory(true);
        //publisher confirms
        rabbitTemplate.setConfirmCallback((correlationData, ack, s) -> {
            String id = correlationData.getId();
            // System.out.println(correlationData);
            if (ack) {
                logger.info("{} 消息成功到达 broker", id);
            } else {
                logger.info("{} 消息未到达 broker,原因：{}", id, s);
            }
        });


        //publisher returns
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            logger.info(MessageFormat.format("消息发送失败，ReturnCallback:{0},{1},{2},{3},{4}", message, replyCode, replyText, exchange, routingKey));
        });

        return rabbitTemplate;
    }

}
