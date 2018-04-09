package com.aces.helloworld.controller;

import com.aces.helloworld.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PojoController {


    private Logger logger = LoggerFactory.getLogger(PojoController.class);

    /**
     * Spring MVC 会按请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值。支持级联属性。
     *
     * @return 成功页面
     */
    @RequestMapping("/pojo")
    public String pojo(User user) {
        logger.info("user:{}", user);
        return "success";
    }

}
