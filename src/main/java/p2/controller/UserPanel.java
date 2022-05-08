package p2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import p2.JavaBeans.UserBD;

/**
 *
 * @author Fran
 */
@WebServlet(name = "UserPanel", urlPatterns = {"/usuario.html"})
public class UserPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entrando en usuario");
		// Cargar Posibles Cantidad nuevas notificaciones, nuevas ocurrencias en historial de productos, nueva configuracion usuario 
		HttpSession session = request.getSession(true);
		UserBD user_session = (UserBD)session.getAttribute("usuario");
		
		if (user_session != null) {
			request.getRequestDispatcher("usuario.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("/login.html");
		}
	}
}
