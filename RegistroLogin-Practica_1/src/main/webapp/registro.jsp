<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>

<%--
  Created by IntelliJ IDEA.
  User: Irina Medina Sierra
  Date: 16/6/22
  Time: 23:24
  Despription: Página de registro  compuesta por un formulario  que se utiliza en tres diferentes sitios de la aplicacion:
  1. En el index, es llamado para solicitar la información necesaria para dar de alta a un nuevo usuario
  2. En el CRUD, igualmente para crear un nuevo usuario
  3. En el CRUD para editar un usuario que ya este de alta
  Como Framework de estilos se utilizando bootstrap.  También utiliza una hoja de estilos llamas style.css
  Para la llamada de datos, se utiliza la biblioteca de etiquetas estándar de JavaServer Pages (JSTL)
--%>
<jsp:include page="header.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Sistema de Alta Online - Registro</title>
</head>
<body>
    <div class="row bg-white py-4 text-center">
        <p>Por favor, complete sus credenciales para Registrarse.</p>
        <!-- Div donde se muestra en caso que haya un mensaje de error, de color rojo -->
        <div class="row  justify-content-center text-danger mensaje fs-5  ">
            ${mensajeDeError}
        </div>
        <!-- Div donde se muestra en caso que haya un mensaje de exito,  de color verda -->
        <div class="row  justify-content-center text-success mensaje fs-5  ">
            ${mensaje}
        </div>
        <form action="Registrador" method="Post" class="needs-validation " novalidate>
            <c:if test="${user.id != null}">
                <input type="hidden" name="id" value="<c:out value='${user.id}'/>"/>
            </c:if>
            <div class="row my-4 justify-content-center  ">
                <div class="col-4  ">
                    <label for="nombre" class="form-label">Nombres y Apellidos:</label>
                    <input type="text" name="nombre" id="nombre" class="form-control border-0 border-bottom" required value="<c:out value='${user.nombre}' />" >
                </div>
                <div class="col-4 ">
                    <label for="email" class="form-label">Email:</label>
                    <input type="text" name="email" id="email" class="form-control border-0 border-bottom" required value="<c:out value='${user.email}' />">
                </div>
            </div>
            <div class="row my-4 justify-content-center">
                <div class="col-4">
                    <label for="user" class="form-label">Usuario:</label>
                    <input type="text" name="user" id="user" class="form-control border-0 border-bottom" required value="<c:out value='${user.usuario}' />">
                </div>
                <div class="col-4">
                    <label for="pass" class="form-label">Contraseña:</label>
                    <input type="password" name="pass" id="pass" class="form-control border-0 border-bottom" required value="<c:out value='${user.password}' />">
                </div>
            </div>
            <div class="row my-4 justify-content-center">
                <div class="col-4">
                    <label for="pass" class="form-label">Permiso de Entrada:</label><br>
                    <select name="idEstado" class="form-select" >
                        <%--
                        En la siguiente sección, se ejecuta el código donde se muestra en caso de ser un alta nueva,
                         los estados  (activo o inactivo) en un control Select. En caso de ser una Actualización de datos
                         se lee desde la base de datos que opción tiene el registro mostrado y se muestra como seleccionado
                         --%>
                        <c:choose>
                          <%--
                            Se  valida si tiene no tiene estado el registro, esto indicaria que es un usuario nuevo,
                            por lo que mostraria el listado de los estados para que se seleccione uno
                             --%>
                            <c:when test = "${user.idEstado == null}">
                                <option value=""></option>
                                <c:forEach var="EstadoTem" items="${listEstado}">
                                    <option value="<c:out value='${EstadoTem.id}'/>"><c:out value="${EstadoTem.estado}"/>
                                    </option>
                                </c:forEach>
                            </c:when>

                            <c:when test = "${user.idEstado > 0 }">
                                <c:forEach var="EstadoTem" items="${listEstado}">
                                    <c:choose>
                                        <c:when test = "${user.idEstado == EstadoTem.id}">
                                            <option value="<c:out value='${EstadoTem.id}'/>" selected><c:out value="${EstadoTem.estado}"/>
                                            </option>
                                        </c:when>

                                        <c:when test="${user.idEstado != EstadoTem.id}">
                                            <option value="<c:out value='${EstadoTem.id}'/>">
                                                <c:out value="${EstadoTem.estado}"/>
                                            </option>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </select>
                </div>
                <div class="col-4">
                    <label for="pass" class="form-label">Departamento:</label><br>
                        <select name="idDepartamento" class="form-select" >
                            <%--
                            En la siguiente sección, se ejecuta el código donde se muestra en caso de ser un alta nueva,
                       los Departamentos en un control Select. En caso de ser una Actualización de datos
                       se lee desde la base de datos que opción tiene el registro mostrado y se muestra como seleccionado
                       --%>
                            <c:choose>
                                <c:when test = "${user.id == null}">
                                    <%--
                                    Se  valida si tiene no tiene Departamento el registro, esto indicaria que es un usuario nuevo,
                                    por lo que mostraria el listado de los Departamentos  para que se seleccione uno
                                    --%>
                                    <option value=""></option>
                                    <c:forEach var="depTem" items="${listDep}">
                                        <option value="<c:out value='${depTem.id}'/>"><c:out value="${depTem.departamento}"/></option>
                                    </c:forEach>
                                </c:when>
                                <%--
                                Como los Departamentos son autonumericos mayores a 0, en la siguiente condicion, en caso de que no sea null y sea mayor a 0, esto indicaría, que estamos
                                leyendo un Departamento existente, por lo cual se procede a buscar el registro que tiene en la base de datos para
                                mostrarlo como seleccionado y el resto para poder ser cambiado.
                                --%>
                                <c:when test = "${user.idDepartamento > 0 }">
                                    <c:forEach var="depTem" items="${listDep}">
                                        <c:choose>
                                            <c:when test = "${user.idDepartamento == depTem.id}">
                                                <option value="<c:out value='${depTem.id}'/>" selected><c:out value="${depTem.departamento}"/>
                                                </option>
                                            </c:when>
                                            <c:when test="${user.idDepartamento  != depTem.id}">
                                                <option value="<c:out value='${depTem.id}'/>">
                                                    <c:out value="${depTem.departamento}"/>
                                                </option>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </select>
                </div>
            </div>

        <div class="row my-4 text-center justify-content-center" >
                        <div class="col-5">
                            <!--
                            código javascript para devolver a la página anterior
                            -->
                            <button value="Volver" onclick="window.history.back();" class="btn boton">Volver</button>
                            <input type="submit" name="boton" value="Guardar" class="btn boton ">
                        </div>
                </div>

        </form>
        <!--
         Código donde se le muestra al usuario, los requisitos que debe cumplir la contraseña creada o actualizada
        -->
        <div class="row my-4 justify-content-center">
            <div class="col-5">
                <h4>Requisitos de la Contraseña:</h4>
                <ul class="list-group">
                    <li class="list-group-item">Al menos ocho caracteres</li>
                    <li class="list-group-item">Al menos dos letras mayúsculas</li>
                    <li class="list-group-item">AL menos dos letras minúsculas</li>
                    <li class="list-group-item">Al menos dos símbolos especiales</li>
                </ul>
            </div>
        </div>
    </div>
</div>
    <!--
    Código javaScript complemento a boostrap para controlar y mostrar los campos del formulario vacios.
    -->
<script>
    const forms = document.querySelectorAll('.needs-validation')

    // Cuando pulsen click en siguiente, valida que no esten vacio los campos
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })
</script>
</body>

</html>
