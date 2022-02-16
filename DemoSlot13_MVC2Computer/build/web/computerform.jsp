<%--
    Document   : computerform.jsp
    Created on : Feb 17, 2022, 1:10:03 AM
    Author     : DELL
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="fu.ex.dtos.RoomDTO"%>
<%@page import="fu.ex.dtos.ComputerErrorObject"%>
<%@page import="fu.ex.dtos.ComputerDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Computer</title>
    </head>
    <body>
        <h1>Add New Computer Information </h1>
        <%
            ComputerDTO cdto = (ComputerDTO) request.getAttribute("cdto");
            ComputerErrorObject ceo = (ComputerErrorObject) request.getAttribute("ceo");

        %>
        <form action="CreateServlet" method="POST">
            <table>
                <tr>
                    <td> ID</td>
                    <td>:<input type="text" name="txtID" value="<% if (cdto != null) {
                            out.println(cdto.getId());
                        }%>" /><font color="red"><% if (ceo != null) {
                                out.print(ceo.getIdError());
                            } %></font></td>
                </tr>
                <tr>
                    <td> CPU</td>
                    <td>:<input type="text" name="txtCPU" value="<% if (cdto != null) {
                            out.println(cdto.getCpu());
                        }%>" /><font color="red"><% if (ceo != null) {
                                out.print(ceo.getCpuError());
                            } %></font></td>
                </tr>
                <tr>
                    <td> Hard Disk</td>
                    <td>:<input type="text" name="txtHardDisk" value="<% if (cdto != null) {
                            out.println(cdto.getHardDisk());
                        }%>" /><font color="red"><% if (ceo != null) {
                                out.print(ceo.getHardDiskError());
                            } %></font></td>
                </tr>
                <tr>
                    <td> VGA</td>
                    <td>:<input type="text" name="txtVGA" value="<% if (cdto != null) {
                            out.println(cdto.getVga());
                        }%>" /><font color="red"><% if (ceo != null) {
                                out.print(ceo.getVgaError());
                            } %></font></td>
                </tr>
                <tr>
                    <td> RAM</td>
                    <td>:<input type="text" name="txtRAM" value="<% if (cdto != null) {
                            out.println(cdto.getRam());
                        }%>" /><font color="red"><% if (ceo != null) {
                                out.print(ceo.getRamError());
                            } %></font></td>
                </tr>
                <tr>
                    <td> Monitor</td>
                    <td>:<input type="text" name="txtMonitor" value="<% if (cdto != null) {
                            out.println(cdto.getMonitor());
                        }%>" /><font color="red"><% if (ceo != null) {
                                out.print(ceo.getMonitorError());
                            } %></font></td>
                </tr>
                <tr>
                    <td> Room</td>
                    <td>:<select name="cboRoom">
                            <% ArrayList<RoomDTO> listRooms = new ArrayList<RoomDTO>();
                                listRooms = (ArrayList<RoomDTO>) request.getAttribute("listRooms");
                                if (listRooms != null) {
                                    for (RoomDTO rdt : listRooms) {%>
                            <option><%=rdt.getId()%> - <%= rdt.getName()%> </option>

                            <%}
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="create" /></td>
                </tr>

            </table>
        </form>
        <br><a href="LoadListComputerServlet">list computer</a>
    </body>
</html>
