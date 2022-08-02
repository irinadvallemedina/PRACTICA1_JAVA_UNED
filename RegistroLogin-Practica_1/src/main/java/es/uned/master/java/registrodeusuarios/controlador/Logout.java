package es.uned.master.java.registrodeusuarios.controlador;

import es.uned.master.java.registrodeusuarios.modelo.UsuarioException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/*
* @author: Irina Medina Sierra
* @version: 15/06/2022
* @Description:  Este servlet realiza el cierre de la sesion del usuario cuando selecciona salir en el CRUD
*  y vuelve a la página de inicio index.jsp
*/

@WebServlet(name = "Logout", value = "/Logout")
public class Logout extends HttpServlet {
    public Logout() {
    }

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(true);
        String enlace = request.getParameter("accion");
        if (enlace != null) {
            try {
                if (enlace.equals("salir")) {
                    sesion.invalidate();
                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
                }
            } catch (ClassCastException e) {
                throw new UsuarioException("013: Sesión cerrada incorrectamente");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doService(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doService(request, response);
    }
}

