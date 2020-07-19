
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Over Due Books</title>
        </head>

        <body>
        <h1>Over Due Books</h1>

       
        <table>
            <c:if test="${not empty model.get('overDueBooks')}">
                <thead>
                    <th>ID</th>
                    <th>ISBN</th>
                    <th>User</th>
                    <th>Time</th>
                </thead>
            </c:if>
            <tbody>
                <c:forEach var="pro" items="${model.get('overDueBooks')}"> 
                    <tr>
                        <td><c:out value="${pro.getId()}" /></td>
                        <td><c:out value="${pro.getIsbn()}" /></td>
                        <td><c:out value="${pro.getUsername()}" /></td>
                        <td><c:out value="${pro.getTime()}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="manipulate.htm">Back</a>
        </body>
    </html>