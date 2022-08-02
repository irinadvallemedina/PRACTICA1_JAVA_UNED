<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: irina
  Date: 19/6/22
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<jsp:include page="header.jsp"></jsp:include>
 <div class="row bg-white py-4 text-center alto ">
            <div class="col my-4   mensaje fs-5">
                <h5 class="text-danger ">
                <%= exception.getMessage() %>
                    </h5>
                <p>Por favor contacte con su administrador.</p>
                <form method="post" action="index.jsp">
                    <input type="submit" value="Volver al Inicio" class="btn boton">
                </form>
            </div>
        </div>
</body>
</html>
