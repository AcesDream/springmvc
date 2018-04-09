package com.aces.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

@Controller
public class ServletController {


    private Logger logger = LoggerFactory.getLogger(ServletController.class);

    /**
     * 可以使用Servlet原生的API，具体支持以下类型：
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * java.security.Principal
     * Locale
     * InputStream
     * OutputStream
     * Reader
     * Writer

     * @return 成功页面
     */
    @RequestMapping("/servlet")
    public String pojo(HttpServletRequest request, HttpServletResponse response) {
        logger.info("request:{}", request);
        logger.info("response:{}", response);

        logger.info("username:{}", request.getParameter("username"));
        return "success";
    }

    @RequestMapping("/servletWriter")
    public void pojo(HttpServletRequest request, HttpServletResponse response, Writer writer) {
        try {
            writer.write("hello Servlet API");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
