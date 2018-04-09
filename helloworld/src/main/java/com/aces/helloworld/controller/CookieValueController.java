package com.aces.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CookieValueController {


    private Logger logger = LoggerFactory.getLogger(CookieValueController.class);

    /**
     * @CookieValue和 @RequestParam 类似，映射cookie中值
     * value：参数名称
     * required：该参数是否是必需的，默认为true
     * defaultValue：设置请求参数的默认值
     *
     * /WEB-INF/views/success.jso
     * @return 成功页面
     */
    @RequestMapping("/cookieValue")
    public String cookieValue(@CookieValue(value = "JSESSIONID") String sid) {
        logger.info("sessionId:{}", sid);
        return "success";
    }

}
