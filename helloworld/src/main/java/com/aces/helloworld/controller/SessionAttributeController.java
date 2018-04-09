package com.aces.helloworld.controller;

import com.aces.helloworld.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * 如何把模型数据放到session域中
 * 1、通过添加@SessionAttributes，可以把数据即放到session域中也放到请求域中
 * value：存放的是键的值
 * types：可以是类型
 *
 * 因此session中有user和home，没有score
 *
 * 注意：该注解只能放在类上
 */
@Controller
@SessionAttributes(value = {"user"}, types = {String.class})
public class SessionAttributeController {


    private Logger logger = LoggerFactory.getLogger(SessionAttributeController.class);

    @RequestMapping("/sessionAttribute")
    public String testSessionAttribute(Map<String, Object> map) {
        logger.info("map.getClass():{}", map.getClass());
        User user = new User();
        user.setUsername("tom");
        user.setPassword("123");
        user.setEmail("tom@126.com");
        user.setAge(12);

        //会放在session中，因为@SessionAttributes中的value指定的
        map.put("user", user);
        //会放在session中，因为@SessionAttributes中的types指定的
        map.put("home", "home");
        //不会放在session中
        map.put("score", 127.5);
        return "success";
    }

}
