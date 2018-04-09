package com.aces.helloworld.controller;

import com.aces.helloworld.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * ModelAttribute的应用场景：
 * 更新数据，前端页面只传入部分参数
 */
@Controller
public class ModelAttributeController {


    private Logger logger = LoggerFactory.getLogger(ModelAttributeController.class);

    /**
     * 运行流程：
     * 1、执行@ModelAttribute注解修饰的方法：从数据库中取出对象，并把对象放到Map中，键为：user
     * 2、spring mvc从map中取出user对象，并把表单参数赋给该user对象的对应属性
     * 3、spring mvc把上述对象传入到目标方法的参数
     *
     *
     * 源码分析流程：
     * 1、首先会调用@ModelAttribute注解修饰的方法，实际上把@ModelAttribute方法中map的数据放入到implicitModel
     * 2、解析请求处理器的目标参数，实际上该目标参数来自于WebDataBinder对象的target属性
     *      a、创建WebDataBinder对象：
     *          I、确定objectName属性：
     *              若传入的attrName属性值为空串，则objectName为类名的首字母小写；
     *              注意attrName，若目标方法的POJO属性使用了@ModelAttribute来修饰，则attrName则为@ModelAttribute的value值；
     *          II、确定target属性：
     *              1、在implicitModel中查找attrName对应的属性值，如果有，则返回；
     *              2、若不存在：则验证当前handler是否使用了@SessionAttributes注解进行修饰，若使用了，而且value指定值
     *              有和attrName相匹配的键，则尝试从session中获取attrName所对应的属性值，若session中没有对应的属性值，则抛出了异常
     *              3、若handler没有使用@SessionAttributes进行修饰，
     *              或@SessionAttributes中没有使用value值指定的key和attrName相互匹配，则通过反射创建POJO
     *      b、spring mvc把表单请求参数赋给WebDataBinder的target对应的属性
     *      c、spring mvc会把WebDataBinder的attrName和target给到implicitModel，进而传递到request域对象中
     *      d、把WebDataBinder的target作为参数传递给目标方法的入参
     * 3、
     *
     * spring mvc确定目标方法POJO类型入参的过程，即上面的attrName（objectName）
     * 1、确定一个key；
     *      a）若目标方法的POJO类型的参数没有使用@ModelAttribute修饰，则key为POJO类名第一个字母的小写；
     *      b）若使用了@ModelAttribute修饰，则key为@ModelAttribute注解的value
     * 2、在implicitModel中查找key对应的对象，若存在，则作为入参传入；
     *      a）若在@ModelAttribute标记的方法中，在map中保存过，且key和1确定的可以一致，则可以获取到；
     * 3、在implicitModel中不存在key对应的对象，则检查当前的handler是否使用@SessionAttributes注解修饰，若使用了该注解，
     * 且@SessionAttributes 注解的value属性值中包含了key，则会从HTTPSession中获取key所对应的对象，若不存在，将抛出异常；
     * 4、若handler没有使用@SessionAttributes注解，或这个注解value值中不包含key，则通过反射创建POJO类型的参数，然后传入；
     * 5、spring mvc会把key和POJO类型的对象保存到implicitModel，进而会保存到request中
     *
     * 注意：在@ModelAttribute注解修饰的方法中，放入到map时的键需要和目标方法入参类型的第一个字母小写的字符串一致
     * @param user
     * @return
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(User user) {
        logger.info("testModelAndView:{}");
        logger.info("user:{}", user);
        return "success";
    }

    /**
     * 1、有 @ModelAttribute 注解的方法，会在每个目标方法执行之前被spring mvc调用
     * 2、@ModelAttribute注解也可以用来修饰POJO类型的入参，其value属性值有如下作用（实际上就是执行attrName）
     *      a）spring mvc会使用value属性在implicitModel中查找对应的对象，如果存在，则会直接传入到目标方法的入参中
     *      b）spring mvc会以value为key，POJO类型的对象为value保存到request域中
     *
     *
     * @param user
     * @return
     */
    @RequestMapping("/testModelAttribute2")
    public String testModelAttribute2(@ModelAttribute("abc") User user) {
        logger.info("testModelAndView2:{}");
        logger.info("user:{}", user);
        return "success";
    }

    @RequestMapping("/testModelAttribute3")
    public String testModelAttribute3(@ModelAttribute("xyz") User user) {
        logger.info("testModelAndView3:{}");
        logger.info("此时request域中会有一个key为xyz的user");
        logger.info("user:{}", user);
        return "success";
    }


    /**
     * 有 @ModelAttribute 注解的方法，会在每个目标方法执行之前被spring mvc调用
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
            map.put("abc", user);
        }
    }
}
