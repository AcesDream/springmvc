package com.aces.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestParamController {


    private Logger logger = LoggerFactory.getLogger(RequestParamController.class);

    /**
     * 可以使用 @RequestParam 来映射参数
     * value：参数名称
     * required：该参数是否是必需的，默认为true
     * defaultValue：设置请求参数的默认值
     *
     * /WEB-INF/views/success.jso
     * @return 成功页面
     */
    @RequestMapping("/requestParam")
    public String requestParam(@RequestParam(value = "username") String name, @RequestParam(value = "age", required = false, defaultValue = "0") int age) {
        logger.info("username:{}；age：{}", name, age);
        return "success";
    }

    /**
     * @RequestHeader 和 @RequestParam类似，映射请求头信息
     * @param al
     * @return
     */
    @RequestMapping("/requestHeader")
    public String requestHeader(@RequestHeader(value = "Accept-Language") String al) {

        logger.info("RequestHeader");
        logger.info("Accept-Language:{}", al);
        return "success";
    }
}
