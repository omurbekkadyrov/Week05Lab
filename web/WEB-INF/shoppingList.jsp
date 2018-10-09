<%-- 
    Document   : shoppingList
    Created on : Oct 9, 2018, 12:31:33 PM
    Author     : 759388
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <c:if test="${message != null}">
            <div role="alert">${message}</div>
        </c:if>

        <c:if test="${info != null}">
            <div role="alert">${info}</div>
        </c:if>
                    
        <p>Hello, <c:out value="${username}"/>. <a href="shoppingList?action=logout">Logout</a>.</p>
            <div>
                <div>
                    <form action="shoppingList" method="post">
                        <input type="hidden" name="action" value="add">
                        <input type="text" name="item" id="item">
                        <input type="submit" value="Add">
                    </form>
                </div>
            </div>
                    
                    
            <div>
                <div>      
                    <form action="shoppingList" method="post">
                        <input type="hidden" name="action" value="delete">
                        <ul>
                            <c:forEach items="${items}" var="item">
                                <li>
                                    <div>
                                        <label>
                                          <input type="radio" name="item" value="<c:out value="${item}"/>">
                                          <c:out value="${item}"/>
                                        </label>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        
                        <button type="submit">Remove</button>
                    </form>
                </div>
            </div>
    </body>
</html>
