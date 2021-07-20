package com.zpl.practice;

// import com.zpl.practice.rabbitmq.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张沛霖
 * @date 2020/10/20
 */
// @Import(RabbitMQConfig.class)
@RestController
public class TestRabbitMQController {

    static final Logger logger = LoggerFactory.getLogger(TestRabbitMQController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RequestMapping(value = "/send2Q", method = RequestMethod.GET)
    public String testPublisherConfirms() {
        String correlationId = "correlationId-" + System.currentTimeMillis();
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setCorrelationId(correlationId);
        Message message = messageConverter.toMessage("这是一条消息", messageProperties);
        rabbitTemplate.send("testExchange", "", message, new CorrelationData(correlationId));
        return null;
    }

    @RequestMapping(value = "/send2WrongQ", method = RequestMethod.GET)
    public String testPublisherReturns() {
        String correlationId = "correlationId-" + System.currentTimeMillis();
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setCorrelationId(correlationId);
        Message message = messageConverter.toMessage("这是一条消息", messageProperties);
        rabbitTemplate.send("testExchange", "1111", message, new CorrelationData(correlationId));
        return null;
    }

    public static void main(String[] args) {
        // PlatformUtil platformUtil = new PlatformUtil().include(PlatformUtil.PlatformEnum.JI_TUAN_APP).include(PlatformUtil.PlatformEnum.BAO_HU_TONG, PlatformUtil.PlatformEnum.BAO_XIAN_DA_SHI, PlatformUtil.PlatformEnum.SHOU_XIAN_APP);
        //连续追加
        // platformUtil.include(Platform.SHOU_XIAN_ZAI_XIAN).include(Platform.SHOU_XIAN_APP);



        //增加全部然后排除几个
        // platformUtil.includeAll().exclude(PlatformUtil.Platform.E_FU_WU_ZHI_FU_BAO).exclude(PlatformUtil.Platform.E_FU_WU_ZHI_FU_BAO);
        // System.out.println(platformUtil.validate("a"));


        // System.out.println(platformUtil.validate("1"));
        // System.out.println(platformUtil.platforms);
        // platformUtil.printPlatforms();
    }


}
