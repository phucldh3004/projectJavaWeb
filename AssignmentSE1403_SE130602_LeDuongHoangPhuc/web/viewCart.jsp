<%-- 
    Document   : viewCart
    Created on : Mar 20, 2020, 8:36:27 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart</h1>

        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart.getItems()}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="RemoveBook" method="GET">

                    <c:forEach var="entry" items="${cart.getItems()}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${entry.key} 
                            </td>
                            <td>
                                ${entry.value} 
                            </td>
                            <td>
                                <input type="checkbox" name="chkItem" value="${entry.key}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3">
                            <a href="SearchBook">Add More Books to Your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Seclected Items" name="btAction" />
                        </td>
                    </tr>



                </form>
                <form action="CheckOut" method="GET">
                    <input type="submit" value="CheckOut" name="btAction" />
                </form>
            </tbody>
        </table>

    </c:if>
    <c:if test="${empty cart.items}">
        <a href="SearchBook">Shopping now! </a>&nbsp;
        <a href="login">Login </a>
        
        <h2> No cart exists </h2>
        
    </c:if>

</body>
</html>
