package com.zpl.practice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PracticeApplicationTests {

    Logger logger = LoggerFactory.getLogger(PracticeApplicationTests.class);

    @MockBean
    private List<String> list;

    @Autowired
    private ConfigurableWebApplicationContext context;

    @Autowired
    private Environment environment;


    @Value("${no.sense.string:false}")
    private boolean isTestString;

    @Test
    public void TestLogger() {
        logger.debug(print());
    }

    public String print() {
        System.out.println("aaaaaaaaaaa");
        return "null";
    }

    @Test
    public void contextLoads() {
        //List<String> list = Mockito.mock(List.class);
        Mockito.when(list.get(0)).thenReturn("HelloWorld");

        String result = list.get(0);

        //这个times中的参数是后面方法被调用的次数
        //Mockito.verify(list,Mockito.times(2)).get(0);
        Mockito.verify(list, Mockito.times(1)).get(0);

        Assert.assertEquals("HelloWorld", result);
    }

    @Test
    public void testProperties() {
        RabbitTemplate template = (RabbitTemplate) context.getBean("zhangpeilinTemplate");
        SimpleRabbitListenerContainerFactory listenerContainerFactory = (SimpleRabbitListenerContainerFactory) context.getBean("zhangpeilinListenerContainerFactory");

        System.out.println(template);

        System.out.println(listenerContainerFactory);
    }

    @Test
    public void test() {
        System.out.println(isTestString);


    }

}
