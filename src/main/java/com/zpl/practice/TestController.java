package com.zpl.practice;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangpeilin
 * @date 2021/6/16
 */
@RestController
public class TestController {



    @RequestMapping("/test")
    public String testParamBinding() throws InterruptedException {
        // TimeUnit.SECONDS.sleep(10);
        return "this is return";
    }
    @RequestMapping(value = "/test/json",method = RequestMethod.POST)
    public String testParamJsonBinding(@RequestBody TestBo testBo){
        System.out.println(testBo.msg);
        return testBo.toString();
    }
}
