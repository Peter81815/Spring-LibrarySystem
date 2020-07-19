<%-- 
    Document   : New Book
    Created on : 2020/7/11
    Author     : peichun
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Book</title>
    </head>
    <body>
        <h1>Add New Book</h1>
        <form action="add.htm" method="post">
            Book ISBN<input type="text" name="bookIsbn" /><br/><br/>
            <input type="hidden" value="submitbook" name="choice" />
            <input type="submit" value="Add Book" name="addbook" />
        </form>
        <form action="manipulate.htm" method ="post">
            <input type="hidden" name="formtype" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
    </body>
</html>