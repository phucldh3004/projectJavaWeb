<%-- 
    Document   : CreateNewAccount
    Created on : Mar 20, 2020, 4:23:59 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Account</title>
    </head>
    <body>
        <h1> Create New Account</h1>
        <form action="CreateNewAccount" method="POST">
            <c:set var="error" value="${requestScope.INSERTER}"/>

            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6-20 chars)<br/>
            <c:if test="${not empty error.usernameLengthErr}">
                <font color="red">

                ${error.usernameLengthErr}

                </font> </br>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(6-30 chars)<br/>
            <c:if test="${not empty error.passwordLengthErr}">
                <font color="red">

                ${error.passwordLengthErr}

                </font> </br>
            </c:if>  
            Confirm* <input type="password" name="txtConfirm" value="" />(6-30 chars)<br/>
            <c:if test="${not empty error.confirmNotMatch}">
                <font color="red">

                ${error.confirmNotMatch}

                </font> </br>
            </c:if> 
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" />(2-50 chars)<br/>
            <c:if test="${not empty error.fullnameLengthErr}">
                <font color="red">

                ${error.fullnameLengthErr}

                </font> </br>
            </c:if> 
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form></br>

        <c:if test="${not empty error.usernameIsExisted}">
            <font color="red">
            ${error.usernameIsExisted}
            </font> </br>
        </c:if>    




    </body>
</html>
