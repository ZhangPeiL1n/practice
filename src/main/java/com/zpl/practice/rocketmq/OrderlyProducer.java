package com.zpl.practice.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * 保证生产有序的生产者
 *
 * @author zhangpeilin
 */
public class OrderlyProducer {

    public static void main(String[] args) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("TestProducerGroup");
        // 开启消息追踪
        // DefaultMQProducer producer = new DefaultMQProducer("TestProducerGroup",true);
        producer.setNamesrvAddr("");
        int orderId = 0;
        // 给消息设置 tag
        Message message = new Message("TestTopic", "tag", "".getBytes());
        // 给消息设置自定义属性
        message.putUserProperty("a", "1");
        message.putUserProperty("b", "6");
        // 设置延迟消息
        message.setDelayTimeLevel(3);
        producer.send(message, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                Long orderId = (Long) o;
                long index = orderId % list.size();
                return list.get((int) index);
            }
        }, orderId);
    }
}
