package p2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import p2.JavaBeans.UserBD;
import p2.AccesoBD;

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
		AccesoBD con = AccesoBD.getInstance();
		HttpSession session = request.getSession(true);

		// refresh user session data if database has been updated since last login
		UserBD user_session = (UserBD)session.getAttribute("usuario");
		session.setAttribute("usuario", con.obtenerUserBD(user_session.getUsuario()));

		if (user_session != null) {
			request.getRequestDispatcher("usuario.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("./login.html");
		}
	}
}
