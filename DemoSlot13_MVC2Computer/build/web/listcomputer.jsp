<%--
    Document   : listcomputer.jsp
    Created on : Feb 17, 2022, 12:55:59 AM
    Author     : DELL
--%>

<%@page import="fu.ex.dtos.ComputerDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Computer</title>
    </head>
    <body>
        <h1>Computer Management</h1>
        <table border="1">

            <thead>
                <tr>
                    <th>ID</th>
                    <th>CPU</th>
                    <th>VGA</th>
                    <th>HardDisk</th>
                    <th>RAM</th>
                    <th>Monitor</th>
                    <th>Room</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<ComputerDTO> lst = new ArrayList<ComputerDTO>();
                    lst = (ArrayList<ComputerDTO>) request.getAttribute("listComputer");
                    if (lst != null) {
                        for (ComputerDTO cdto : lst) {
                %>
                <tr>
                    <td><%=cdto.getId()%></td>
                    <td><%=cdto.getCpu()%></td>
                    <td><%=cdto.getVga()%></td>
                    <td><%=cdto.getHardDisk()%></td>
                    <td><%=cdto.getRam()%></td>
                    <td><%=cdto.getMonitor()%></td>
                    <td><%=cdto.getRoom().getName()%> - <%=cdto.getRoom().getBuilding()%></td>
                    <td>Edit</td>
                    <td><a href="DeleteServlet?pid=<%=cdto.getId()%>">delete</a></td>

                </tr>
                <%

                        }
                    } else {
                    }
                %>
            </tbody>
        </table>
        <br><a href="CreateFormServlet">Add New Computer</a>
    </body>
</html>
