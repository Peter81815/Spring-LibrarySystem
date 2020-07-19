<%-- 
    Document   : Returninfo
    Created on : 2020/7/12, 下午 11:26:28
    Author     : peichun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Return A Book</title>
    </head>
    <body>
        <h1>Enter Book ID</h1>
        <form action="returnone.htm" method="post">
            Book ID<input type="text" name="bookId" /><br/><br/>
            <input type="hidden" value="returnbook" name="choice" />
            <input type="submit" value="Return Book" name="Return Book" />
        </form>
        <form action="auth.htm" method ="post">
            <input type="hidden" name="option" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
    </body>
</html>

