<%-- 
    Document   : search
    Created on : Oct 3, 2022, 10:55:15 AM
    Author     : minhd
--%>

<%--<%@page import="minhtda.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page import="javax.servlet.http.Cookie"%>--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER.fullName}
        </font>
        <h1>Welcome to search page</h1>
        <form action="DispatchController">
            <input type="submit" value="Logout" name="btAction" />
        </form>
        <div>Welcome to search page</div>
        <form action="DispatchController">
            Search <input type="text" name="txtSearch" 
                          value="${param.txtSearch}" />
            <br>
            <input type="submit" value="Search" name="btAction" />
        </form><br>
        <c:set var="searchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="errors" value="${sessionScope.ERROR_SUPDATE}"/>
                        <c:set var="uerror" value="${sessionScope.USERNAME}"/>
                        <c:set var="perror" value="${sessionScope.PASSWORD}"/>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchController">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>


                                    <c:if test="${dto.username == uerror}">
                                        <c:if test="${not empty errors.passwordLengthError}">
                                            <input type="text" name="txtPassword" value="${perror}" />
                                            <br/>
                                            <font color='red'>
                                            ${errors.passwordLengthError}
                                            </font>
                                        </c:if>

                                    </c:if>
                                    <c:if test="${dto.username != uerror}">
                                        <input type="text" name="txtPassword" value="${dto.password}" />
                                    </c:if>
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="Chekced"
                                           </c:if>/>

                                </td>
                                <td>
                                    <c:if test="${not dto.role}">
                                        <c:url var="urlRewriting" value="DispatchController">
                                            <c:param name="btAction" value="delete"/>
                                            <c:param name="pk" value="${dto.username}"/>
                                            <c:param name="lastSearchValue" value="${searchValue}"/>
                                        </c:url>
                                        <a href="${urlRewriting}">Delete</a>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="submit" name="btAction" value="update" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>


                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            Invalid Search
        </c:if>
    </c:if>

</body>


</html>
