package es.uned.master.java.registrodeusuarios.controlador;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyErrorServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        request.getSession().setAttribute("errorServlet", throwable);
    }
}