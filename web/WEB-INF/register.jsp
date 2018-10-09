<%-- 
    Document   : register
    Created on : Oct 9, 2018, 12:31:22 PM
    Author     : 759388
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        
            <c:if test="${message != null}">
               <div role="alert">${message}</div>
            </c:if>

            <c:if test="${info != null}">
               <div role="alert">${info}</div>
            </c:if>
                    
            <form  action="shoppingList" method="post">
            Username: <input type="hidden" name="action" value="register">
            <input type="text" name="username" id="username">
            <input type="submit" value="Register">
            </form>
    </body>
</html>
