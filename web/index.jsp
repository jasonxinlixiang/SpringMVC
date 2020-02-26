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
