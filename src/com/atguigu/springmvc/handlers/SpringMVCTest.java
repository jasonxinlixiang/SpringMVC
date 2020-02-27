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

//@SessionAttributes(value = {"user"}, types = {String.class})
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {

    private static final String SUCCESS = "success";

    /**
     * 由  @ModelAttribute 标记的方法，会在每个目标方法执行之前被SpringMVC调用！！
     * @param id
     * @param map
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id,
                        Map<String, Object> map){
        System.out.println("ModelAttribute method.......");
        if (id != null){
            //模拟从数据库中获取一个对象
            User user = new User(1, "Tom", "123456", "tom@guigu.com", 12);
            System.out.println("从数据库中获取一个对象 " + user);

            map.put("user", user);
        }

    }

    /**
     *
     * 运行流程：
     * 1. 执行 @ModelAttribute 注解修饰的方法：从数据库中取出对象，把对象放入到Map中。键为user
     * 2. SpringMVC 从Map中取出 User 对象，并把表单的请求参数赋给该user对象的对应属性
     * 3. SpringMVC 把上述对象传入目标方法的参数
     *
     * 注意：在 @ModelAttribute 修饰的方法中，放入到Map时的键需要和目标方法入参类型的第一个字母小写的字符串一致！
     *
     * SpringMVC 确定目标方法POJO类型入参的过程
     *  1. 确定一个Key
     *  1）若目标方法的POJO类型的参数没有使用 @SessionAttributes 作为修饰，则key为POJO类名第一个字母的小写
     *  2）若使用了@SessionAttributes 来修饰，则key 为@ModelAttribute 注解的value属性值
     *  2. 在implicitModel中查找key 对应的对象，若存在，则作为入参传入
     *  1）若在@ModelAttribute 标记的方法中在Map 中保存过，且Key 和 1 确定的key一致，则会获取到。
     *  3. 若implicitModel中不存在key 对应的对象，则检查当前的handler 是否使用@SessionAttributes 注解修饰，
     *  若使用了该注解，且@SessionAttributes 注解的value 属性值中包含了key, 则会从HttpSession 中来获取key 所
     *  对应的value 值，若存在则直接传入到目标方法的入参中，若不存在则将抛出异常。
     *  4. 若Handler 没有标识@SessionAttributes 注解或 @SessionAttributes 注解的value值中不包含key, 则会
     *  通过反射来创建POJO类型的参数，传入为目标方法的参数
     *  5. SpringMVC 会把key和POJO类型的对象保存到implicitModel中，进而会保存到request 中。
     *
     *
     * @param user
     * @return
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(User user){
        System.out.println("修改： " + user);
        return SUCCESS;
    }

    /**
     * @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外（实际上使用的是 value 属性值），
     * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中（实际上使用的是 types 属性值）
     *
     * 注意：该注解只能放在类上，不能放在方法上
     *
     * @param map
     * @return
     */
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String, Object> map){
        User user = new User("Tom", "12345", "tom@163.com", 15);
        map.put("user", user);
        map.put("school", "atguigu");
        return SUCCESS;
    }

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
