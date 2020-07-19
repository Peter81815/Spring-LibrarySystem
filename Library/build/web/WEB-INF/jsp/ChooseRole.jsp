<%-- 
    Document   : Book Inventory
    Created on : 2020/7/11
    Author     : peichun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Inventory</title>
    </head>
    <body>
        <h1>Choose Role</h1><br/>
        <div>Please Make Your Selection Below</div><br/>
        <form action="authentication.htm" method="post">

            <select name="choice">
                <option value="Librarian">Librarian</option>
                <option value="User">User</option>
            </select>
            <input type="submit" name="submit" value="Send"/>
            <input type="hidden" name="option" value="home"/>
            
        </form>
    </body>
</html>
