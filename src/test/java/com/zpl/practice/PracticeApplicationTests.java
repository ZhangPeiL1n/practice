package com.zpl.practice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PracticeApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(PracticeApplicationTests.class);

    @MockBean
    private List<String> list;

    @Autowired
    private ConfigurableWebApplicationContext context;

    @Autowired
    private Environment environment;


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
}
