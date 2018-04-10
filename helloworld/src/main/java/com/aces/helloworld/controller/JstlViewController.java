package com.aces.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class JstlViewController {


    private Logger logger = LoggerFactory.getLogger(JstlViewController.class);

    /**
     * 若项目中使用了 JSTL，则 SpringMVC 会自动把视图由InternalResourceView 转为 JstlView
     * 实际上是只要把jstl和standard依赖添加到项目中，视图就会InternalResourceView 转为 JstlView
     * 可以结合视图解析流程源码分析，看到view是JstlView
     *
     * 若使用 JSTL 的 fmt 标签则需要在 SpringMVC 的配置文件中配置国际化资源文件
     *
     * @return 成功页面
     */
    @RequestMapping("/testJstlView")
    public String testJstlView () {
        logger.info("testJstlView");
        return "success";
    }
}
