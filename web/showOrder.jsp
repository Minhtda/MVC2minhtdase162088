<%-- 
    Document   : showOrder
    Created on : Oct 30, 2022, 11:13:53 PM
    Author     : minhd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="mintda.order.OrderDTO"%>
<%@page import="minhtda.orderDetail.OrderDetailDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <h1>Electronic bill</h1>
        <c:set var="ckSessiong" value="${sessionScope.SESSION}"/>
        <c:if test="${not empty ckSessiong}">
            <c:set var="Order" value="${sessionScope.ORDER}"/>
            <c:if test="${not empty Order}">
                OrderID: 
                Name: ${Order.name}</br>
                Date Buy: ${Order.dbuy}</br>
                Total Price: ${Order.total}</br>
                <c:set var="OrderDetail" value="${sessionScope.ORDERDETAIL}"/>
                <c:if test="${not empty OrderDetail}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Product ID</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="OrderDetailList" varStatus="count" items="${OrderDetail}">
                                <tr>
                                    <td>
                                        ${count.count}
                                    </td>
                                    <td>
                                        ${OrderDetailList.sku}
                                    </td>
                                    <td>
                                        ${OrderDetailList.quantity}
                                    </td>
                                    <td>
                                        ${OrderDetailList.price}
                                    </td>
                                    <td>
                                        ${OrderDetailList.total}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
            </c:if>
        </c:if>

    </body>
</html>
