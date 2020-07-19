<%-- 
    Document   : makeSure
    Created on : 2020/7/12, 下午 11:47:43
    Author     : peichun
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>All Books</title>
        </head>

        <body>
        <h1>Are you sure to retun these books</h1>

       
        <table>
            <c:if test="${not empty map.get('allBooks')}">
                <thead>
                    <th>ID</th>
                    <th>ISBN</th>
                    <th>User</th>
                    <th>Time</th>
                </thead>
            </c:if>
            <tbody>
                <c:forEach var="pro" items="${map.get('allBooks')}"> 
                    <tr>
                        <td><c:out value="${pro.getId()}" /></td>
                        <td><c:out value="${pro.getIsbn()}" /></td>
                        <td><c:out value="${pro.getUsername()}" /></td>
                        <td><c:out value="${pro.getTime()}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/><br/><br/><br/><br/><br/><br/>
        <form action="returnall.htm" method="POST">
            <input type="hidden" value="sure" name="choice" />
            <input type="submit" value="OK!"/>
        </form>
        <a href="manipulate.htm">Cancel</a>
        </body>
    </html>
