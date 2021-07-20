package com.zpl.practice;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.ToString;

/**
 * @author 张沛霖
 * @date 2020/12/21
 */
@ToString
public class TestBo {

    public static final String MESSAGE = "message";

    @JSONField(name = "1111")
    public String name;

    public String password;

    @JSONField(name = MESSAGE)
    public String msg;
}
