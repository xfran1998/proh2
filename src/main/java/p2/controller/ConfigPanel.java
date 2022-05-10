package p2.controller;

import java.io.InputStreamReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.JsonValue;

import p2.AccesoBD;
import p2.JavaBeans.UserBD;

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
		
		// refresh user session data if database has been updated since last login
		UserBD user_session = (UserBD)session.getAttribute("usuario");
		
		if (user_session != null) {
			// session.setAttribute("pedidos", conexion.obtenerPedidosBD(user_session.getUsuario()));
			request.getRequestDispatcher("/usuario_inpage/configuracion.jsp").forward(request, response);
		}
		else {
			System.out.println("No hay usuario en sesion");
			response.sendRedirect("./login.html");
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Updating en perfil");
		AccesoBD con = AccesoBD.getInstance();
		HttpSession sesion = request.getSession(true); //Se accede al entorno de la sesi�n
		
		UserBD user = (UserBD)sesion.getAttribute("usuario");
				
		if (user == null) {
			// send error msg to client
			response.getWriter().println("{\"status\": 400, \"msg\": \"No hay usuario en sesion\"}");
			return;
		}

		// Parse JSON body
		JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
		JsonObject jobj = reader.readObject();
		
		// usuario id
		int id_user = user.getId();

		// Loop through all fields in JSON body
		for (String key : jobj.keySet()) {
			String value = jobj.getString(key);
		 	System.out.println("Key: " + key + " Value: " + value);
		 	con.cambiarUsuarioBD(id_user, key, value);
		}


		// get user from database again
		UserBD user_updated = con.obtenerUserBD(id_user);
		sesion.setAttribute("usuario", user_updated);

		response.getWriter().println("{\"status\": 200, \"msg\": \"Update correct\", \"page\": \"perfil\"}");
	}
}
