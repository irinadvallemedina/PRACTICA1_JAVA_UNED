package es.uned.master.java.registrodeusuarios.controlador;

/**
 * @author: Irina Medina Sierra
 * @version: 15/06/2022
 * @Description:  **
 * El servlet 'Eliminar' se utiliza como enlace con el id del usuario seleccionado en el 'ClientesCRUD' y el Modelo
 * 'UserDAO' y  así  permitir eliminar  los datos en la BBDD
 * * @param: id
 */

import es.uned.master.java.registrodeusuarios.modelo.UserDAO;
import es.uned.master.java.registrodeusuarios.modelo.Usuario;
import es.uned.master.java.registrodeusuarios.modelo.UsuarioException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Eliminar", value = "/Eliminar")

public class Eliminar extends HttpServlet {
    UserDAO dao = new UserDAO();
    String mensaje;
    boolean salida;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = (request.getParameter("id"));
            salida = dao.borrar(id);
            if (salida) {
                List<Usuario> listUser = dao.listar();
                request.setAttribute("listUser", listUser);
                mensaje = "&#10140;El usuario se ha BORRADO correctamente";
                request.getSession().setAttribute("mensajeCRUD", mensaje);
                response.sendRedirect("ListarCRUD");
            }
    }
}

