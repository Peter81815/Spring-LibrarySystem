<%-- 
    Document   : Library
    Created on : 2020/7/11
    Author     : peichun
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manipulate Book</title>
    </head>
    <body>
        <h1>Choose Action</h1>
        <form action="manipulate.htm" method="post">

            <select name="choice">
                <option value="Add">Add</option>
                <option value="Remove">Remove</option>
                <option value="Overdue">Overdue</option>
            </select>
            <input type="submit" name="submit" value="Send"/>
            <input type="hidden" name="option" value="home"/>
            
        </form>
    </body>
</html>
