package com.zpl.practice.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 保证消费有序的消费者
 *
 * @author zhangpeilin
 */
public class OrderlyConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TestConsumerGroup");
        consumer.setNamesrvAddr("");
        consumer.subscribe("topic", "*");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                try {
                    for (MessageExt msg : list) {
                        // 有序处理
                    }
                    // 全部消费成功
                    return ConsumeOrderlyStatus.SUCCESS;
                } catch (Exception e) {
                    // 消息处理有问题
                    // 返回标识，暂停一会再处理这批
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
            }
        });


    }

}
