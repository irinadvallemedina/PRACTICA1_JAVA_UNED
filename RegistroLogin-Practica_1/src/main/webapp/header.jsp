<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>

<!--
- Author(:Irina Medina Sierra
- Date:17/6/22
- Description: Archivo JSP que muestra el encabezado en todas las páginas jsp, para evitar la redundancia de códito.
- Se utiliza bootstrap, material icons de Google y css personalizado para los estilos.
-->

<!DOCTYPE html>
<html lang="es">
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"         rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"  crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"  crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <title>Banca online - Alta</title>
    <link rel="shortcut icon"  href="img/favicon.jpg" >
    <link href="css/style.css" rel="stylesheet" >
</head>
<body>
<header>
    <div class="container header">
        <div class="row py-4" >
            <div class="col-6">
                <a href="index.jsp"><img src="img/banca.png" width="50%" alt="Logotipo"></a>
            </div>
                <div class="col-6 text-end ">
                    <!--
                    El siguiente bloque se muestra solo cuando se ha logeado correctamente el usuario, donde puede
                    imprimir, exportar a Excel o salir
                    -->
                    <c:set var="usuarioLogeado" scope="session" value="${usuarioL}"/>
                    <c:if test="${usuarioLogeado!=null}">
                    <p class="fs-6 nombre ">  ${nombreL}</p>
                    <!--Código javascript para que pueda activar la impresión en el navegador.-->
                    <a href="exportar.jsp" title="Exportar a Excel" class="printer p-2"><span class="material-icons  iconos">download</span></a>
                    <a href="javascript:window.print()" title="Imprimir" classs="printer p-2"><span class="material-icons  iconos">print</span></a>
                    <!-- Botón que activa el Cotrolador Logout para cerrar la Sesión del Usuario-->
                    <a href="Logout?accion=salir" title="Salir" class="exit p-2"><span class="material-icons  iconos">logout</span></a></p>
                    </c:if>
                </div>
        </div>
    </div>
</header>
<div class="container">
<div class="row">
    <div class="col-12 text-white text-center negro" style="padding: 25px">
        <h1>Sistema de Alta Online</h1>
    </div>
</div>
    <! Se deja abierto el div del container y se cierra en cada archivo jsp de donde es llamado -->
