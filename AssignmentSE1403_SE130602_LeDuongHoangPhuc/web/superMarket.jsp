<%-- 
    Document   : superMarket
    Created on : Mar 21, 2020, 8:52:10 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <form action="Cart" method="POST">


            Choose your favourite book 
            <select name="cboBook" >
                <c:forEach var="list" items="${requestScope.LISTPRODUCT}">
                    <option value="${list.productName}"> ${list.productName}</option>
                    
                </c:forEach>

            </select><br/>

            <input type="submit" value="Add Book To Your Cart" name="btAction" />
            <input type="submit" value="View Your Cart" name="btAction" />  
            <input type="submit" value="Go Login" name="btAction" />
        </form>
        
    </body>
</html>
