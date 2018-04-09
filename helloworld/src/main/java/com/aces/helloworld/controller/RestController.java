package com.aces.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Restful API介绍
 * REST：即 Representational State Transfer。（资源）表现层状态转化。是目前最流行的一种互联网软件架构。
 * 它结构清晰、符合标准、易于理解、扩展方便，所以正得到越来越多网站的采用
 *
 * 资源（Resources）：网络上的一个实体，或者说是网络上的一个具体信息。
 * 它可以是一段文本、一张图片、一首歌曲、一种服务，总之就是一个具体的存在。
 * 可以用一个URI（统一资源定位符）指向它，每种资源对应一个特定的 URI 。
 * 要获取这个资源，访问它的URI就可以，因此 URI 即为每一个资源的独一无二的识别符。
 *
 *
 * 表现层（Representation）：把资源具体呈现出来的形式，叫做它的表现层（Representation）。
 * 比如，文本可以用 txt 格式表现，也可以用 HTML 格式、XML 格式、JSON 格式表现，甚至可以采用二进制格式。
 *
 * 状态转化（State Transfer）：每发出一个请求，就代表了客户端和服务器的一次交互过程。
 * HTTP协议，是一个无状态协议，即所有的状态都保存在服务器端。
 * 因此，如果客户端想要操作服务器，必须通过某种手段，让服务器端发生“状态转化”（State Transfer）。
 * 而这种转化是建立在表现层之上的，所以就是 “表现层状态转化”。
 * 具体说，就是 HTTP 协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE。
 * 它们分别对应四种基本操作：
 *      GET 用来获取资源
 *      POST 用来新建资源
 *      PUT 用来更新资源
 *      DELETE 用来删除资源
 *
 * 示例：
 * /order/1 HTTP GET ：得到 id = 1 的 order
 * /order/1 HTTP DELETE：删除 id = 1的 order
 * /order/1 HTTP PUT：更新id = 1的 order
 * /order HTTP POST：新增 order
 *
 * spring mvc支持rest的底层支持
 * HiddenHttpMethodFilter：浏览器 form 表单只支持 GET与 POST 请求，而DELETE、PUT 等 method 并不支持，
 * Spring3.0 添加了一个过滤器，可以将这些请求转换为标准的 http 方法，使得支持 GET、POST、PUT 与DELETE 请求。
 *
 * 通过在form表的中添加隐藏域：_method，设置值为PUT或者DELETE
 */

@Controller
@RequestMapping("/rest")
public class RestController {


    private Logger logger = LoggerFactory.getLogger(RestController.class);

    /**
     * 查询
     * @param userId
     * @return 成功页面
     */
    @RequestMapping("/user/{id}")
    public String getUser(@PathVariable("id") Integer userId) {
        logger.info("userId：{}", userId);
        logger.info("查询用户ID：{}的数据", userId);
        return "success";
    }

    /**
     * 保存
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public String saveUser(@PathVariable("id") Integer userId) {
        logger.info("userId：{}", userId);
        logger.info("保存用户ID：{}的数据", userId);
        return "success";
    }

    /**
     * 更新
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable("id") Integer userId) {
        logger.info("userId：{}", userId);
        logger.info("更新用户ID：{}的数据", userId);
        return "success";
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") Integer userId) {
        logger.info("userId：{}", userId);
        logger.info("删除用户ID：{}的数据", userId);
        return "success";
    }

}
