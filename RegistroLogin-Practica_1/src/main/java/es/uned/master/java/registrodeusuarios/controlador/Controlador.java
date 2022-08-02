package es.uned.master.java.registrodeusuarios.controlador;
/**
 * @author: Irina Medina Sierra
 * @version: 15/06/2022
 * @Description: El Servlet Controlador se utiliza para leer los datos  informados en "index.jsp",
 * se crea el objeto usuario, a través del Método Post y se valida si ha insertado correctamente los datos,
 * En caso de que los datos esten en vacios o no se encuentre registrado en la Base de datos, se envia como
 * mensaje de error, que existe un error. En caso de que los datos sean correctos, se crea la sesion logeado, se captura
 * el nombre y se envia al Servlet ListarCRUD.
 * @param: usuario
 * @param: password
 */
import es.uned.master.java.registrodeusuarios.modelo.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Controlador", value = "/Controlador")

public class Controlador extends HttpServlet {
    UserDAO dao = new UserDAO();
    Usuario u = new Usuario();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Si el usuario entra, se lee los datos de usuario y password para enviarlo a la interfaz validar
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        u.setUsuario(user);
        u.setPassword(pass);
        //dao.validar nos devuelve una excepcion si no existe el usuario, y true porque es valido y nos lleva al CRUD
        boolean salida = dao.validar(u);
        if (salida) {
            String nombre = "Usuario: " + u.getNombre();
            request.getSession().setAttribute("usuarioL", user);
            request.getSession().setAttribute("nombreL", nombre);
         //   System.out.println(request.getRequestedSessionId());
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("EstadoSesion", "Logado");
            response.sendRedirect("ListarCRUD");
        } else {
            String errores = " &#10140; Existe un error en el usuario o en la contraseña. Verifique sus datos";
            request.getSession().setAttribute("error", errores);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
