<%-- 
    Document   : platform
    Created on : 2020/7/12, 上午 10:07:54
    Author     : peichun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Platform</title>
    </head>
    <body>
        <h1>Choose Action</h1>
        <form action="manipulate.htm" method="post">

            <select name="choice">
                <option value="Check">Check Out</option>
                <option value="Return">Return a Book</option>
                <option value="All">Return All Books</option>
            </select>
            <input type="submit" name="submit" value="Send"/>
            <input type="hidden" name="option" value="home"/>
            
        </form>
    </body>
</html>

