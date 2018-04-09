package com.aces.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

@Controller
public class ModelAndViewController {


    private Logger logger = LoggerFactory.getLogger(ModelAndViewController.class);

    /**
     * 目标方法的返回值可以是ModelAndView类型，可以包含视图和模型信息
     * Spring mvc 会把ModelAndView中的model中的数据放入到request域对象中
     *
     * 目标方法也可以通过添加Map、Model、ModelMap类型的入参，返回数据，详见下面的三个方法
     * @return 成功页面
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        logger.info("testModelAndView:{}");
        ModelAndView modelAndView = new ModelAndView("success");

        modelAndView.addObject("time", new Date());

        return modelAndView;
    }

    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap) {
        logger.info("testModelAndView:{}");
        modelMap.addAttribute("name", "modelMap");

        return "success";
    }

    @RequestMapping("/testModel")
    public String testModel(Model model) {
        logger.info("testModelAndView:{}");
        model.addAttribute("name", "model");

        return "success";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map) {
        logger.info("map.getClass():{}", map.getClass());
        map.put("name", "map");

        return "success";
    }

}
