<%@ page import="es.uned.master.java.registrodeusuarios.modelo.UserDAO" %>
<%@ page import="es.uned.master.java.registrodeusuarios.modelo.Usuario" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="application/vnd.ms-excel; charset=UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: irina
  Date: 29/7/22
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%
    UserDAO dao = new UserDAO();
    List<Usuario> listUser = dao.listar();
    request.setAttribute("listUser", listUser);
    String nombreArchivo = "usuarios.xls";
    response.setHeader("Content-Disposition", "attachment;filename=" + nombreArchivo);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Title</title>
</head>
<body>
<table class="table table-hover">
    <thead class="table-light">
    <tr>
        <td>ID</td>
        <td>Nombre </td>
        <td>Email </td>
        <td>Usuario</td>
        <td>Estado</td>
        <td>Departamento</td>
    </tr>
    </thead>
<c:forEach var="usuario" items="${listUser}">
    <tr>
    <td><c:out value="${usuario.id}"/></td>
    <td><c:out value="${usuario.nombre}"/></td>
    <td><c:out value="${usuario.email}"/></td>
    <td><c:out value="${usuario.usuario}"/></td>
    <td><c:out value="${usuario.estado}"/></td>
    <td><c:out value="${usuario.departamento}"/></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
