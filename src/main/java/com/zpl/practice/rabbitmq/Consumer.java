package com.zpl.practice.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 张沛霖
 * @date 2020/11/12
 */
// @Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // @RabbitListener(queues = "testQueue")
    // private void rabbitListenerReceive(Message message, Channel channel) throws IOException {
    //
    //     logger.info("收到消息,correlationId:{},消息参数:{}", message.getMessageProperties().getCorrelationId(), message.toString());
    //
    //     long deliveryTag = message.getMessageProperties().getDeliveryTag();
    //
    //
    //
    //     try {
    //         channel.basicAck(deliveryTag, false);
    //     } catch (Exception e) {
    //         channel.basicNack(deliveryTag, false, true);
    //     }
    // }
}
