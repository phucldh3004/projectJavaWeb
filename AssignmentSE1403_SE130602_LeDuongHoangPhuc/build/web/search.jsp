<%-- 
    Document   : search
    Created on : Mar 19, 2020, 4:41:12 PM
    Author     : DELL
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.FULLNAME}
        </font>
        <form action="Logout" method="POST">
            <input type="submit" value="Logout" name="btAction" />
        </form>
        
        <h1>Search Page</h1>

        <form action="SearchFullname" >
            Search value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /> </br>
            <input type="submit" value="Search" name="btAction" />
        </form>
        </br>


        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEACHRESULT}" />

            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>NO.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>

                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="dto" varStatus="counter" items="${result}">

                        <form action="UpdatePassRole" method="POST"> 
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>

                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>

                                <td>${dto.fullname}</td>
                                <td>
                                    ${dto.role}
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>

                                <td> 
                                    <c:url var="delLink" value="DeleteAccount">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${delLink}">Delete</a>
                                </td>
                                <td>

                                    <input type="submit" value ="Update" name ="btAction"/> 
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />     
                                </td>
                            </tr>
                        </form>

                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>NO RECORD FOUND</h2>

        </c:if>

    </c:if>






</body>
</html>
