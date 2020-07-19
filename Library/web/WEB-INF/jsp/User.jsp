<%-- 
    Document   : User login page
    Created on : 2020/7/11
    Author     : peichun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>User interface</h1>
        <h3>Setting Your Information</h3>
        <form action="authentication.htm" method="POST">
            <label> Username :  </label><input type="text" name="userName" />
            <label> Password :  </label><input type="password" name="password" />            
            <input type="hidden" value="userRegister" name="option" />
            <input type="submit" value="Set"/>
        </form>
        <br/><br/><br/><br/><br/><br/><br/>
        <h3>Login To System</h3>
        <form action="authentication.htm" method="POST">
            <label> Username :  </label><input type="text" name="userName" />
            <label> Password :  </label><input type="password" name="password" />
            <input type="hidden" value="userLogin" name="option" />
            <input type="submit" value="Login"/>
        </form>
        <br/><br/><br/><br/><br/><br/><br/>

    </body>
</html>

