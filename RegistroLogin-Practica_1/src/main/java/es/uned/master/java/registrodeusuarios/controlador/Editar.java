package es.uned.master.java.registrodeusuarios.controlador;

/**
 * @author: Irina Medina Sierra
 * @version: 15/06/2022
 * @Description:  El servlet 'Editar' se utiliza como enlace con el id del usuario seleccionado en el 'ClientesCRUD'
 * y la Clase 'UserDAO' y  así  permitir actualizar los datos en la BBDD
 * Para ello, llama a la Vista  'registrar.jsp', y mostrar los datos editables del usuario.
 * Cabe destacar que una de los datos editables es el Estado y el Departamento, debe tambien llamar estos
 * Servlet y crear la colección antes de mostrar la Vista.
* @param: id
 */

import es.uned.master.java.registrodeusuarios.modelo.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Editar", value = "/Editar")

public class     Editar extends HttpServlet {
    UserDAO newDAO = new UserDAO();

    DepartamentoDAO d = new DepartamentoDAO();
    EstadoDeLaCuentaDAO c = new EstadoDeLaCuentaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("el id seleccionado  en editar es: " + id);
        Usuario registroUsuario = newDAO.seleccioanrUsuario(id);
        List<Departamento> listDep = d.listarDep();
        request.setAttribute("listDep", listDep);
        List<EstadoDeLaCuenta> listEstado = c.listarEstado();
        request.setAttribute("listEstado", listEstado);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Registrador?opcion=editarDesdeCRUD");
        request.setAttribute("user", registroUsuario);
        dispatcher.forward(request, response);
    }
}