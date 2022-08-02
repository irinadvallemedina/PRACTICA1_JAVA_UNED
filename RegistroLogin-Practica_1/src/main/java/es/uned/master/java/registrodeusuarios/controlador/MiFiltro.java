package es.uned.master.java.registrodeusuarios.controlador;
/**
 * @author: Irina Medina Sierra
 * @version: 15/06/2022
 * @Description:  La clase Filter para crear el control de sesiones en la aplicación.
 */

        import java.io.IOException;
        import javax.servlet.Filter;
        import javax.servlet.FilterChain;
        import javax.servlet.FilterConfig;
        import javax.servlet.ServletException;
        import javax.servlet.ServletRequest;
        import javax.servlet.ServletResponse;
        import javax.servlet.http.*;

public class MiFiltro implements Filter {

    public void destroy() {
        // TODO Auto-generated method stub

    }

    // Esta filtro gestionará la sesión iniciada
    // Para ello debe garantizarse que la sesión ha sido previamente iniciada.
    // Por eso gestionamos de la sesión "usuarioL" como objeto de sesión a incluir en el sesión.
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // Antes valido si es un HttpServletRequest
        if (request instanceof HttpServletRequest){
            // El objeto sessión que solicito del request

            HttpSession sesion =  ((HttpServletRequest) request).getSession();
            if (sesion.isNew()){
                String errores = " &#10140; Debe iniciar sesion. Acceso Denegado";
                ((HttpServletRequest) request).getSession().setAttribute("error", errores);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                // Que siga con el siguiente filtro u serlvet
                chain.doFilter(request,response);
            }
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }
}

