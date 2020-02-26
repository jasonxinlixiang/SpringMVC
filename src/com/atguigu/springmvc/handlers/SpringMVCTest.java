package com.atguigu.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {

    private static final String SUCCESS = "success";

    /**
     * 了解： 可以使用params和headers来更加精确的映射请求，params和headers 支持简单的表达式
     * @return
     */
    @RequestMapping(value = "testParamsAndHeaders", params = {"username", "age!=10"})
    public String testParamsAndHeaders(){
        System.out.println("testParamsAndHeaders");
        return SUCCESS;
    }

    /**
     *  使用method属性来指定请求方式
     * @return
     */
    @RequestMapping(value = "testMethod", method = RequestMethod.POST)
    public String testMethod(){
        System.out.println("test method");
        return SUCCESS;
    }

    /**
     * 1. @RequestMapping除了修饰方法也可以修饰类
     * 2.
     * @return
     */
    @RequestMapping("/testRequestMapping")
    public String testRequestMapping(){
        System.out.println("testRequestMapping");
        return SUCCESS;
    }
}
