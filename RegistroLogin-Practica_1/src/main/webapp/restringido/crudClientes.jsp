<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"></jsp:include>
<!--
- Author(:Irina Medina Sierra
- Date:17/6/22
- Description: Archivo JSP que lee los datos de la base de datos Usuarios y los muestra de 10 registros por página
- utilizando una paginación aplicando taglibs
-->
         <div class="row bg-white py-4 alto">
                <div class="row justify-content-center">
                        <!--    Mensaje de error o exito cuando se da de alta un nuevo registro -->
                    <span class="text-center text-success">${mensajeCRUD}</span>
                    <div class="col-9 ">
                        <!--    Se utiliza el método get para indicar al controlador que se activa dar de alta un nuevo usuario, donde
                        se llama al registro.jsp-->
                            <a href='Registrador?opcion=altaDesdeCRUD' ><span class="material-icons">person_add
                             </span> Nuevo Usuario</a>
                    </div>
                </div>
             <!--  Fin de la seccion de alta nueva -->
             <!--  A partir de el sigueinte div, se comienza a mostrar el encabezado del CRUD por medio de una tabla-->
             <div class="row justify-content-center">
                 <div class="col-9 ">
                      <table class="table table-hover">
                           <thead class="table-light">
                           <tr>
                               <th>Nombre </td>
                               <td>Email </td>
                               <td>Usuario</td>
                               <td>Estado</td>
                               <td>Departamento</td>
                               <td>Opciones</td>
                           </tr>
                           </thead>
                          <!-- ****Código que establece la paginación del CRUD****-->
                          <!-- Desde el Controlador, se puede leer el numero total de registros a mostrar y se le asigna el valor a la variable totalCount-->
                           <c:set var="totalCount" scope="session" value="${pages}"/>
                          <!-- Número total de registros por página y se le asigna el valor a la variable perPage-->
                           <c:set var="perPage" scope="session"  value="${porPagina}"/>
                          <!-- Se establece una variable  llamada pageStart, para ir indicando donde comienza cada paginación, según el cálulo que se hace mas adelante-->
                           <c:set var="pageStart" value="${param.start}"/>
                          <!-- Se hace una condición if donde se asegura que la pagina de inicio (pafeStart) no este vacio o menor a 0, si es true pageStart=0-->
                           <c:if test="${empty pageStart or pageStart < 0}">
                               <c:set var="pageStart" value="0"/>
                           </c:if>
                          <!-- Condición if donde se comprueba que el número de registros sea menor que el comienzo de la página, en caso de ser true,
                           se resta a pógina de comienzo el número de registos que deseamos mostrar por pagina. Básicamente esta función controla el enlace
                           ANTERIOR -->
                           <c:if test="${totalCount < pageStart}">
                               <c:set var="pageStart" value="${pageStart - perPage}"/>
                           </c:if>
                            <!--A partir del siguiente bucle FOREACH se muestra los registros del CRUD, indicando donde comienza, donde termina  -->
                           <c:forEach var="usuario" items="${listUser}" begin="${pageStart}" end="${pageStart + perPage - 1}">
                           <tr>
                            <!--   <td id="usuarioID"><c:out value="${usuario.id}"/></td>-->
                               <td><c:out value="${usuario.nombre}"/></td>
                               <td><c:out value="${usuario.email}"/></td>
                               <td><c:out value="${usuario.usuario}"/></td>
                               <td><c:out value="${usuario.estado}"/></td>
                               <td><c:out value="${usuario.departamento}"/></td>
                               <td>
                                   <a href="Editar?id=${usuario.id}" id="editar">
                                       <span class="material-icons iconos-crud">edit</span></a>
                                   <a href="Eliminar?id=${usuario.id}" id="eliminar">
                                       <span class="material-icons iconos-crud">delete</span></a>
                               </td>
                           </tr>
                           </c:forEach>
                           </tbody>
                      </table>
                     <!--A partir de este bloque, se muestra los enlaces que permiten hacer la paginación -->
                     <div class="row">
                         <div class="col-12 text-center">
                                <!-- Enlace donde se le indica por medio del método GET donde comienza a recorrer
                                la lista de registros. Ej: si pageStart =50 y perPage=10, mostrara ?starh=40 -->
                                 <a href="ListarCRUD?start=${pageStart - perPage}">
                                     <span class="iconos-crud">« </span>Anterior</a>
                                <!--Mensaque que incluyo para saber en que registros estamos -->
                                 | Registros desde el: ${pageStart + 1} al  ${pageStart + perPage}|
                                <!-- Igual que al estar, en esta enlace Siguiente se calcula los siguiente registros a mostrar
                                sumando 10 al pageStart -->
                                 <a href="ListarCRUD?start=${pageStart + perPage}"> Siguiente
                                     <span class="iconos-crud"> »</span></a>
                         </div>
                     </div>
                 </div>
             </div>
            </div>
    </div>
</body>
</html>
