<%--
  Created by IntelliJ IDEA.
  User: xiangxinli
  Date: 2020/2/26
  Time: 10:56 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <a href="springmvc/testMap">Test ModelAndView</a>
  <br><br>

  <a href="springmvc/testModelAndView">Test ModelAndView</a>
  <br><br>

  <a href="springmvc/testServletAPI">Test Servlet API</a>
  <br><br>

  <form action="springmvc/testPojo" method="POST">
    username:<input type="text" name="username">
    <br>
    password:<input type="text" name="password">
    <br>
    email:<input type="text" name="email">
    <br>
    age:<input type="text" name="age">
    <br>
    city:<input type="text" name="address.city">
    <br>
    province:<input type="text" name="address.province">
    <br>
    <input value="submit" type="submit">
  </form>
  <br><br>

  <a href="springmvc/testCookieValue">Test Cookie Value</a>
  <br><br>

  <a href="springmvc/testRequestHeader">Test Request Header</a>
  <br><br>

  <a href="springmvc/testRequestParam?username=Jason&age=11">Test RequestParam</a>
  <br><br>

  <form action="springmvc/testRest/1" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <input value="Test Rest Put" type="submit">
  </form>
  <br><br>

  <form action="springmvc/testRest/1" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <input value="Test Rest Delete" type="submit">
  </form>
  <br><br>

  <form action="springmvc/testRest" method="POST">
    <input value="Test Rest Post" type="submit">
  </form>
  <br><br>

  <a href="springmvc/testRest/1">Test Rest Get</a>
  <br><br>

  <a href="springmvc/testPathVariable/1">Test PathVariable</a>
  <br><br>

  <a href="springmvc/testAntPath/xxyy/abc">Test AntPath</a>
  <br><br>

  <a href="springmvc/testParamsAndHeaders?username=atguigu&age=14">Test ParamsAndHeaders</a>
  <br><br>

  <form action="springmvc/testMethod" method="POST">
    <input value="submit" type="submit">
  </form>
  <br><br>

  <a href="springmvc/testRequestMapping">Test RequestMapping</a>

  <br><br>
  <a href="helloworld">Hello World</a>
  </body>
</html>
