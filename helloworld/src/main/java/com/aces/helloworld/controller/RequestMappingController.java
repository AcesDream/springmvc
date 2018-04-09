package com.aces.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/springmvc")
public class RequestMappingController {


    private Logger logger = LoggerFactory.getLogger(RequestMappingController.class);

    /**
     * 1. @RequestMapping 除了修饰方法，还可以修饰类
     * 2. 类定义处：提供初步的请求映射信息。相对于WEB应用的根目录
     *    方法定义处：提供近一步的细分映射信息
     *    相对于类定义处的URL。若类定义处未标注 @RequestMapping ，则方法标记的URL，相对于WEB应用的根目录
     *
     * @return 成功页面
     */
    @RequestMapping("/hello")
    public String helloWorld() {
        logger.info("RequestMapping可以修饰方法，也可以修饰类");
        return "success";
    }
}
