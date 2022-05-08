package p2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import p2.AccesoBD;
import p2.JavaBeans.UserBD;

/**
 *
 * @author Fran
 */
@WebServlet(name = "UserProfile", urlPatterns = {"/perfil.html"})
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entrando en perfil");
		AccesoBD conexion = AccesoBD.getInstance();
		HttpSession session = request.getSession(true);
		UserBD user_session = (UserBD)session.getAttribute("usuario");
		
		if (user_session != null) {
			session.setAttribute("pedidos", conexion.obtenerPedidosBD(user_session.getUsuario()));
			request.getRequestDispatcher("/usuario_inpage/perfil.jsp").forward(request, response);
		}
		else {
			System.out.println("No hay usuario en sesion");
			response.sendRedirect("/login.html");
		}
	}
}
