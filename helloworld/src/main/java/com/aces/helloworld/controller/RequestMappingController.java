package com.aces.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    /**
     * 1、@RequestMapping 的属性，
     * value：定义映射的URL
     * method：定义请求的方式（GET、POST、PUT、DELETE）
     *
     * params：请求参数
     * headers：请求头
     * 其中params和heads支持简单的表达式
     * param1：表示请求必须包含参数名为param1的参数
     * !param1：表示请求参数不能包含名为param1的参数
     * param1 != value1：表示请求包含参数名为param1的参数，但是其值不能是value1
     * {"param1=value1", "param2"}：表示请求参数包含名为param1和param2的两个参数，且param1的参数值为value1
     *
     * @return 成功页面
     */
    @RequestMapping(value = "/method", method = RequestMethod.POST)
    public String requestMappingMethod() {
        logger.info("RequestMapping属性使用说明");
        return "success";
    }

    /**
     * params和headers可以更加精确的映射请，不过使用到的不多，最常用的还是method
     * @return
     */
    @RequestMapping(value = "/paramsAndHeads", params = {"usernmae", "age=11"}, headers = {"Accept-Language=en-US"})
    public String requestMappingParamsAndHeads() {
        logger.info("RequestMapping属性使用说明");
        return "success";
    }
}
