package es.uned.master.java.registrodeusuarios.controlador;

import es.uned.master.java.registrodeusuarios.modelo.UserDAO;
import es.uned.master.java.registrodeusuarios.modelo.Usuario;
import es.uned.master.java.registrodeusuarios.modelo.UsuarioException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LitarCRUD", value = "/ListarCRUD")
public class ListarCRUD extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            UserDAO dao = new UserDAO();
            List<Usuario> listUser = dao.listar();
            request.setAttribute("listUser", listUser);
            int totalFilas = listUser.size();
            int porPagina = 10;
            request.setAttribute("pages", totalFilas);
            request.setAttribute("porPagina", porPagina);
            request.getRequestDispatcher("restringido/crudClientes.jsp").forward(request, response);


    }
}
