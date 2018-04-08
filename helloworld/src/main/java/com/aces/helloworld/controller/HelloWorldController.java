package com.aces.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    /**
     * 1. 使用 @RequestMapping 注解来映射请求的URL
     * 2. 返回值会通过视图解析器解析为实际的物理视图，对于 InternalResourceViewResolver 视图解析器，会做如下解析
     * 通过“prefix + returnval + suffix”这样的方法得到实际的物理视图，然后做转发操作
     *
     * /WEB-INF/views/success.jso
     * @return 成功页面
     */
    @RequestMapping("/hello")
    public String helloWorld() {
        logger.info("i'm spring mvc hello world");
        return "success";
    }
}
