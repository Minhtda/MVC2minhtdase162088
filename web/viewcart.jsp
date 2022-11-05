<%-- 
    Document   : viewcart
    Created on : 15-Oct-2022, 08:39:04
    Author     : minhd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="minhtda.cart.CartObject"%>
<%@page import="java.util.Map"%>
<%@page import="minhtda.product.ProductDTO"%>
<%@page import="minhtda.product.ProductDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Web Mart</h1>
        <h2>Your cart include</h2>
        <form action="DispatchController">
            <c:set var="ckSession" value="${sessionScope.SESSION}"/>
            <c:if test="${not empty ckSession}">
                <c:set var="list" value="${sessionScope.CART}"/>
                <c:if test="${not empty list}">
                    <c:set var="itemslist" value="${sessionScope.CART.items}"/>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>name</th>
                                <th>quanity</th>
                                <th>price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" varStatus="count" items="${itemslist}">
                                <tr>
                                    <td>
                                        ${count.count}
                                    </td>
                                    <td>
                                        ${dto.value.name}
                                    </td>
                                    <td>
                                        ${dto.value.quanity}
                                    </td>
                                    <td>
                                        ${dto.value.price*dto.value.quanity}

                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
            </c:if>

            <input type="submit" value="Go to shopping page" name="btAction" />

            <input type="submit" value="Check out" name="btAction" />
        </form>
        <%--<%
            if(session != null){
                CartObject cart = (CartObject)session.getAttribute("CART");
                if(cart!=null){
                    Map<String, Integer> items = cart.getItems();
                    if(items != null){
                        %>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quanity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <form action="DispatchController">
                            <tbody>
                                <%
                                    int count = 0;
                                    for(String key: items.keySet){
                                        int value = items.get(key);
                                        %><tr>
                                            <td>
                                                <%= ++count %>
                                            </td>
                                            <td>
                                                <%= key %>
                                            </td>
                                            <td>
                                                <%= value %>
                                            </td>
                                            <td>
                                                <input type="checkbox" 
                                                       name="checkbox" 
                                                       value="<%= key %>" />
                                            </td>
                                        </tr>
                                        <%
                                    }// end for
                                %>
                                <tr>
                                    <td colspan="3">
                                        <a href="cart.html">Add more items to your cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Items" name="btAction" />
                                    </td>
                                </tr>
                            </tbody>
                            </form>
                        </table>

        
        <%
                        return;
                    }
                }
            }
        
        %>--%>
    </body>
</html>
