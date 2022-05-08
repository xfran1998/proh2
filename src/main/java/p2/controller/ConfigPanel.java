package p2.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import p2.AccesoBD;
import p2.JavaBeans.UserBD;
import p2.utils.JSON;

/**
 *
 * @author Fran
 */
@WebServlet(name = "ConfigPanel", urlPatterns = {"/configuracion.html"})
public class ConfigPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entrando en configuracion");
		AccesoBD conexion = AccesoBD.getInstance();
		HttpSession session = request.getSession(true);
		UserBD user_session = (UserBD)session.getAttribute("usuario");
		
		if (user_session != null) {
			session.setAttribute("pedidos", conexion.obtenerPedidosBD(user_session.getUsuario()));
			request.getRequestDispatcher("/usuario_inpage/configuracion.jsp").forward(request, response);
		}
		else {
			System.out.println("No hay usuario en sesion");
			response.sendRedirect("/login.html");
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Updating en perfil");
		AccesoBD conexion = AccesoBD.getInstance();
		String reqBody = JSON.getBody(request);
		HttpSession sesion = request.getSession(true); //Se accede al entorno de la sesi�n
		UserBD user = (UserBD)sesion.getAttribute("usuario");
		
		if (sesion.getAttribute("usuario") == null) {
			// send error msg to client
			response.getWriter().println("{code: 400, error: 'No hay usuario en sesion'}");
			return;
		}

		JSON miParams = new JSON(reqBody);
		Map<String, String> params = miParams.getAll();

		//check if there are no params
		if (params.size() == 0) {
			// send error msg to client
			response.getWriter().println("{code: 401, error: 'No hay parametros en el body'}");
			return;
		}
		

		// usuario id
		int id_user = user.getId();

		// call	cambiarUsuarioBD(String campo, String valor) with all params of params map
		for (String key : params.keySet()) {
			System.out.println("Key: " + key + " Value: " + params.get(key));
			conexion.cambiarUsuarioBD(id_user, key, params.get(key));
		}
		
		// Update user session
		UserBD user_session = conexion.obtenerUserBD(id_user);
		sesion.setAttribute("usuario", user_session);
	}
}
