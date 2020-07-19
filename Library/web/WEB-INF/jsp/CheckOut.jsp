<%-- 
    Document   : CheckOut
    Created on : 2020/7/12, 下午 08:33:19
    Author     : peichun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out Book</title>
    </head>
    <body>
        <h1>Enter Book Information</h1>
        <form action="checkout.htm" method="post">
            Book ID<input type="text" name="bookId" /><br/><br/>
            Book ISBN<input type="text" name="bookIsbn" /><br/><br/>
            <input type="hidden" value="checkout" name="choice" />
            <input type="submit" value="Check Out" name="CheckOut" />
        </form>
        <form action="auth.htm" method ="post">
            <input type="hidden" name="option" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
        <a href="addcompany.htm">Back</a>
    </body>
</html>
