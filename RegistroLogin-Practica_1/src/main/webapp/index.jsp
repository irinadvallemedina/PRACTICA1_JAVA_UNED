<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"  errorPage="error.jsp"%>

<%--
  Created by IntelliJ IDEA.
  User: Irina Medina Sierra
  Date: 16/6/22
  Time: 23:24
  Despription: Página principal donde el usuario se auntetica para entrar en el CRUD. Esta compuesta por un formulario
  solicita la información necesaria, y en caso de no estar registrado, tiene un boton, que lleva a otro formulario de
  registro
--%>
<jsp:include page="header.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Sistema de Alta Online - Registro</title>

</head>
<body>
    <div class="row bg-white py-4 text-center alto">
            <p>Por favor, complete sus credenciales para iniciar sesión.</p>
    <p class="justify-content-center mensaje fs-5">
        <span class="text-danger "> ${error}</span><span class="text-success">   ${successMsn}</span>
    </p>
    <form action="Controlador" method="post" class="needs-validation" novalidate>
        <div class="row  justify-content-center">
            <div class="col-4">
                <label for="user" class="form-label">Usuario:</label>
                <input type="text" name="user" id="user" class="form-control border-0 border-bottom" required autocomplete>
            </div>
        </div>
        <div class="row my-4 justify-content-center">
            <div class="col-4">
                <label for="pass" class="form-label">Contraseña:</label>
                <input type="password" name="pass" class="form-control border-0 border-bottom" id="pass" required autocomplete>
            </div>
        </div>
        <div class="row my-4 text-center justify-content-center">
            <div class="col-5">
                <input type="submit" name="submit" value="Entrar" class="btn boton">
            </div>
        </div>
    </form>

    <div class="row my-4 mx-5">
        <div class="col text-center">
            <p>Si no esta registrado, puede hacerlo pulsando clic en
                <a href='Registrador?opcion=altaDesdeIndex' class="iconos-crud">Aqui</a>
        </div>
    </div>

    </div>
</div>
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
</div>
</body>

</html>