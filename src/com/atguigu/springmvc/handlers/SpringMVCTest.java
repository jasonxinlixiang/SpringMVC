package com.atguigu.springmvc.handlers;

import com.atguigu.springmvc.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {

    private static final String SUCCESS = "success";

    /**
     * 目标方法可以添加 Map 类型(实际上也可以是Model 类型或是ModelMap 类型)的参数
     * @param map
     * @return
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map){
        System.out.println(map.getClass().getName());
        map.put("names", Arrays.asList("Tom", "Jerry", "Jason"));
        return SUCCESS;
    }

    /**
     * 目标方法的返回值可以是ModelAndView 类型
     * 其中可以包含视图和模型信息
     * Spring MVC 会把 ModelAndView 的model中数据放入到request域对象中
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        String viewName = SUCCESS;
        ModelAndView modelAndView = new ModelAndView(viewName);

        //添加模型数据到ModelAndView中
        modelAndView.addObject("time", new Date());

        return modelAndView;
    }

    /**
     * 可以使用 servlet 原生的API作为目标方法的参数， 具体支持以下类型：
     *
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * java.security.Principal
     * Locale InputStream
     * OutputStream
     * Reader
     * Writer
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/testServletAPI")
    public void testServletAPI(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Writer out) throws IOException {
        System.out.println("testServletAPI " + request + ", " + response);
        out.write("hello Spring MVC");
        //return SUCCESS;
    }

    @RequestMapping("/testPojo")
    public String testPojo(User user){
        System.out.println("testPojo " + user);
        return SUCCESS;
    }

    /**
     * 了解： @CookieValue 映射一个cookie值，属性同 @RequestParam
     * @param sessionId
     * @return
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId){
        System.out.println("testCookieValue， sessionId: " + sessionId);
        return SUCCESS;
    }

    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al){
        System.out.println("testRequestHeader, Accept Language: " + al);
        return SUCCESS;
    }

    /**
     * @RequestParam 来映射请求参数
     * value 值即为请求参数的参数名
     * required 该参数是否必须， 默认为true
     * defaultValue 请求参数的默认值
     *
     * @param username
     * @param age
     * @return
     */
    @RequestMapping(value = "/testRequestParam", method = RequestMethod.GET)
    public String testRequestParam(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "age", required = false, defaultValue = "0") int age) {
        System.out.println("testRequestParam, username: " + username + " , and age: " + age);
        return SUCCESS;
    }

    /**
     * Rest 风格的URL
     * 以 CRUD 为例：
     * 新增：/order POST
     * 修改：/order/1 PUT
     * 获取: /order/1 GET
     * 删除 /order/1 DELETE
     *
     * 如何发送 PUT 请求和 DELETE 请求呢？
     * 1. 需要配置HiddenHttpMethodFilter
     * 2. 需要发送POST请求
     * 3. 需要在发送POST请求时携带一个 name="_method" 的隐藏域， 值为DELETE或PUT
     *
     *
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
    public String testRestPut(@PathVariable("id") Integer id){
        System.out.println("Test Rest Put: " + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable("id") Integer id){
        System.out.println("Test Rest Delete: " + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest", method = RequestMethod.POST)
    public String testRestPost(){
        System.out.println("Test Rest Post");
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
    public String testRestGet(@PathVariable("id") Integer id){
        System.out.println("Test Rest Get: " + id);
        return SUCCESS;
    }

    /**
     * @PathVariable 可以来映射URL中的占位符到目标方法的参数中
     * @param id
     * @return
     */
    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id){
        System.out.println("testPathVariable: " + id);
        return SUCCESS;
    }


    @RequestMapping("/testAntPath/*/abc")
    public String testAntPath(){
        System.out.println("testAntPath");
        return SUCCESS;
    }

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
