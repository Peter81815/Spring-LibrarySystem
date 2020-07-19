<%-- 
    Document   : Nopermit
    Created on : 2020/7/12, 下午 10:53:06
    Author     : peichun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>No permission </title>
    </head>
    <body>
        <h1>No permission to borrow books!!</h1>
        <form action="auth.htm" method ="post">
            <input type="hidden" name="option" value="userLogin"/>
            <input type="submit" value="Back"/>
        </form><br/>
<!--        <a href="manipulate.htm">Back</a>-->
    </body>
</html>
