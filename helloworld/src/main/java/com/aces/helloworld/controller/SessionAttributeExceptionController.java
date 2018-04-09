package com.aces.helloworld.controller;

import com.aces.helloworld.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

/**
 * @SessionAttributes 引起的异常
 *
 * 使用@SessionAttributes注解，指定了value为user
 * 然后访问/testModelAttributeExcep方法， 会抛出异常：
 * [Session attribute 'user' required - not found in session] with root cause
 * 可以参考ModelAttribute原理说明那一节
 * 解决方法：
 * 1、在方法入参POJO上使用@ModelAttribute("abc")
 * public String testModelAttributeNoExcep(@ModelAttribute("abc") User user)
 *
 * 2、增加@ModelAttribute注解标注的方法
 * @ModelAttribute
 * public void getUser(@RequestParam(value = "username", required = false) String username, Map<String, Object> map)
 *
 *
 */
@Controller
@SessionAttributes(value = {"user"}, types = {String.class})
public class SessionAttributeExceptionController {


    private Logger logger = LoggerFactory.getLogger(SessionAttributeExceptionController.class);

    @RequestMapping("/testModelAttributeExcep")
    public String testModelAttributeExcep(User user) {
        logger.info("testModelAttributeExcep:{}");
        logger.info("user:{}", user);
        return "success";
    }


    @RequestMapping("/testModelAttributeNoExcep")
    public String testModelAttributeNoExcep(@ModelAttribute("abc") User user) {
        logger.info("testModelAttributeExcep:{}");
        logger.info("user:{}", user);
        return "success";
    }

    /**
     * 可以注释掉本方法，然后分别访问
     * /testModelAttributeExcep：会抛出异常
     * /testModelAttributeNoExcep：不会抛出异常，因为入参使用了@ModelAttribute("abc") User user
     *
     * 取消本方法的注释，然后分别访问：
     * /testModelAttributeExcep：不会抛出异常
     * /testModelAttributeNoExcep：不会抛出异常
     * @param username
     * @param map
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "username", required = false) String username, Map<String, Object> map) {
        logger.info("更新之前，先查询数据库");
        if(username != null) {
            User user = new User();
            user.setUsername("tom");
            user.setPassword("123");
            user.setEmail("tom@126.com");
            user.setAge(12);

            map.put("user", user);
        }
    }

}
