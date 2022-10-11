<%-- 
    Document   : search
    Created on : Oct 3, 2022, 10:55:15 AM
    Author     : loqua
--%>

<%@page import="minhtda.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <div>Welcome to search page</div>
        <form action="DispatchController">
            Search <input type="text" name="txtSearch" 
                          value="<%= request.getParameter("txtSearch")%>" />
            <br>
            <input type="submit" value="Search" name="btAction" />
        </form><br>
        <%
            String searchValue = request.getParameter("txtSearch");
            if (searchValue != null) {
                //Ket qua tra ve co kieu du lieu la List<RegistrationDTO>
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full Name</th>
                    <th>Role</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchController"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername() 
                                + "&lastSearchValue=" + searchValue;
                %>
                <tr>
                    <td>
                        <%= ++count %>
                        .</td>
                    <td>
                        <%= dto.getUsername() %>
                    </td>
                    <td>
                        <%= dto.getPassword() %>
                    </td>
                    <td>
                        <%= dto.getFullName() %>
                    </td>
                    <td>
                        <!--phuong thuc co kieu boolean thi chuyen thanh isRole-->
                        <%= dto.isRole() %>
                    </td>
                    <td>
                        <a href="<%= urlRewriting %>">Delete</a>
                    </td>
                </tr>   
                <%
                    }//end traverse do in result
                %>
            </tbody>
        </table>

        <%
        } else {
        %>
        <h1>
            No record is matched!!!
        </h1>

        <%
                }

            }//end search Value has existed
        %>

    </body>
</html>
