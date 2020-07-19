<%-- 
    Document   : Removeid
    Created on : 2020/7/11, 下午 07:26:36
    Author     : peichun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reomve a Book</title>
    </head>
    <body>
        <h1>Reomve a Book</h1>
        <form action="manipulate.htm" method="post">
            Book ID<input type="text" name="bookid" /><br/><br/>
            <input type="hidden" value="removebook" name="choice" />
            <input type="submit" value="Add Product" name="addproduct" />
        </form>
        <form action="manipulate.htm" method ="post">
            <input type="hidden" name="formtype" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
    </body>
</html>
