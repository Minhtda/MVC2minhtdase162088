<%-- 
    Document   : CreateAccount
    Created on : 22-Oct-2022, 09:00:06
    Author     : minhd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchController" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            Username <input type="text" name="txtUsername" value="${param.txtUsername}" /></br>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color ="red">
                    ${errors.usernameLengthError}
                </font></br>
            </c:if>
            Password <input type="password" name="txtPassword" value="" /></br>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color ="red">
                    ${errors.passwordLengthError}
                </font></br>
            </c:if>
            Confirm <input type="password" name="txtConfirm" value="" /></br>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color ="red">
                    ${errors.confirmNotMatched}
                </font></br>
            </c:if>
            Fullname <input type="text" name="txtName" value="${param.txtName}" /></br>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color ="red">
                    ${errors.fullnameLengthError}
                </font></br>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
        </form>
    </body>
</html>
