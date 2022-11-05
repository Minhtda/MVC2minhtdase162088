<%-- 
    Document   : shopping
    Created on : 25-Oct-2022, 12:31:32
    Author     : minhd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <h1>Choose your product</h1>
        <c:set var="list" value="${sessionScope.ProductList}"/>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quanity</th>
                        <th>Add to cart</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="plist" varStatus="count" items="${list}">
                    <form action="DispatchController">
                        <tr>
                            <td>
                                ${count.count}
                            </td>
                            <td>
                                ${plist.name}
                                <input type="hidden" name="txtName" value="${plist.name} " />
                            </td>
                            <td>
                                ${plist.description}
                            </td>
                            <td>
                                ${plist.price}
                                <input type="hidden" name="txtPrice" value="${plist.price} " />
                            </td>
                            <td>
                                ${plist.quanity}
                            </td>
                            <td>
                                <input type="submit" name="btAction" value="Add book to your cart" />
                                <input type="hidden" name="txtSku" value="${plist.sku} " />
                            </td>
                        </tr>
                    </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty list}">
            there is something wrong
        </c:if>
            <form action="DispatchController">
                <input type="submit" name="btAction" value="View your cart" />
            </form>
    </body>
</html>
