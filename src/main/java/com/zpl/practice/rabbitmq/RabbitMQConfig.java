// package com.zpl.practice.rabbitmq;
//
// import com.rabbitmq.client.*;
// import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
// import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
// import org.springframework.amqp.rabbit.connection.Connection;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
// import org.springframework.context.annotation.Bean;
//
// import java.io.IOException;
//
// /**
//  * 多 RabbitMQ 与多 ActiveMQ 的思路类似
//  * 1. 根据不同的连接配置创建不同的连接工厂
//  * 2. 使用需要的连接工厂创建 RabbitTemplate（生产者、消费者拉模式需要） 和 RabbitListenerContainerFactory（消费者推模式需要）
//  * 并交给 spring 管理，通过不同的 beanName 区分
//  * 3. 使用时根据 beanName 区分进行注入
//  *
//  * @author 张沛霖
//  * @date 2020/11/12
//  */
// // @Configuration
// public class RabbitMQConfig {
//
//     /**
//      * 第一个连接工厂
//      *
//      * @param address
//      * @param virtualHost
//      * @param userName
//      * @param password
//      * @return
//      * @throws Exception
//      */
//     @Bean(name = "firstRabbitMQConnectionFactory")
//     public CachingConnectionFactory firstConnectionFactory(
//             @Value("${spring.rabbitmq.first.address}") String address,
//             @Value("${spring.rabbitmq.first.virtual-host}") String virtualHost,
//             @Value("${spring.rabbitmq.first.username}") String userName,
//             @Value("${spring.rabbitmq.first.password}") String password) throws Exception {
//         CachingConnectionFactory factory = new CachingConnectionFactory();
//         //密码解密
//         // password = DESUtils.decrypt(password);
//         factory.setAddresses(address);
//         factory.setVirtualHost(virtualHost);
//         factory.setUsername(userName);
//         factory.setPassword(password);
//
//
//         Connection connection = factory.createConnection();
//         Channel channel = connection.createChannel(false);
//
//         //订阅方式接收消息，推送接口
//         //第四个参数是回调方法
//         //autoAck false 需要手动对消费进行确认
//         channel.basicConsume("queueName", false, "consumeTag", new DefaultConsumer(channel) {
//             @Override
//             public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                 String routingKey = envelope.getRoutingKey();
//                 String contentType = properties.getContentType();
//                 long deliveryTag = envelope.getDeliveryTag();
//
//
//                 //确认消息
//                 channel.basicAck(deliveryTag, false);
//             }
//         });
//
//         //拉取接口
//         GetResponse response = channel.basicGet("queueName", false);
//         if (response == null) {
//
//         } else {
//             AMQP.BasicProperties props = response.getProps();
//             byte[] body = response.getBody();
//             //envelope 信封
//             long deliveryTag = response.getEnvelope().getDeliveryTag();
//
//             channel.basicAck(deliveryTag, false);
//         }
//
//
//         //退回监听
//         channel.addReturnListener(new ReturnListener() {
//             @Override
//             public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
//
//             }
//         });
//
//
//         return factory;
//     }
//
//     /**
//      * 第二个连接工厂
//      *
//      * @param address
//      * @param virtualHost
//      * @param userName
//      * @param password
//      * @return
//      * @throws Exception
//      */
//     @Bean(name = "secondRabbitMQConnectionFactory")
//     public CachingConnectionFactory secondConnectionFactory(
//             @Value("${spring.rabbitmq.second.address}") String address,
//             @Value("${spring.rabbitmq.second.virtual-host}") String virtualHost,
//             @Value("${spring.rabbitmq.second.username}") String userName,
//             @Value("${spring.rabbitmq.second.password}") String password) throws Exception {
//         CachingConnectionFactory factory = new CachingConnectionFactory();
//         // password = DESUtils.decrypt(password);
//         factory.setAddresses(address);
//         factory.setVirtualHost(virtualHost);
//         factory.setUsername(userName);
//         factory.setPassword(password);
//
//         return factory;
//     }
//
//     /**
//      * 第一个 RabbitTemplate
//      *
//      * @param connectionFactory
//      * @return
//      */
//     @Bean(name = "firstRabbitTemplate")
//     public RabbitTemplate firstRabbitTemplate(
//             @Qualifier("firstRabbitMQConnectionFactory") CachingConnectionFactory connectionFactory) {
//         RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//
//
//         return rabbitTemplate;
//     }
//
//     /**
//      * 第二个 RabbitTemplate
//      *
//      * @param cachingConnectionFactory
//      * @return
//      */
//     @Bean(name = "secondRabbitTemplate")
//     public RabbitTemplate secondRabbitTemplate(
//             @Qualifier("secondRabbitMQConnectionFactory") CachingConnectionFactory cachingConnectionFactory) {
//         RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
//         return rabbitTemplate;
//     }
//
//
//     /**
//      * 第一个 RabbitListenerContainerFactory
//      *
//      * @param configurer
//      * @param cachingConnectionFactory
//      * @return
//      */
//     @Bean(name = "firstRabbitListenerContainerFactory")
//     public SimpleRabbitListenerContainerFactory firstFactory(
//             SimpleRabbitListenerContainerFactoryConfigurer configurer,
//             @Qualifier("firstRabbitMQConnectionFactory") CachingConnectionFactory cachingConnectionFactory) {
//         SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//         configurer.configure(factory, cachingConnectionFactory);
//         return factory;
//     }
//
//
//     /**
//      * 第二个 RabbitListenerContainerFactory
//      *
//      * @param configurer
//      * @param cachingConnectionFactory
//      * @return
//      */
//     @Bean(name = "secondRabbitListenerContainerFactory")
//     public SimpleRabbitListenerContainerFactory secondFactory(
//             SimpleRabbitListenerContainerFactoryConfigurer configurer,
//             @Qualifier("secondRabbitMQConnectionFactory") CachingConnectionFactory cachingConnectionFactory) {
//         SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//         configurer.configure(factory, cachingConnectionFactory);
//         return factory;
//     }
//
// }
