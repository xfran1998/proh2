package p2.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; // Para acceder al entorno de sesi�n

import p2.AccesoBD;
import p2.JavaBeans.UserBD;
import p2.utils.JSON;

/**
 *
 * @author Fran
 */
@WebServlet(name = "Login", urlPatterns = {"/login.html"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String reqBody = JSON.getBody(request);
		Boolean isLoged = false;
		
		JSON miParams = new JSON(reqBody);

		String usuario = miParams.getString("user"); //Se obtiene el nombre de usuario
		String clave = miParams.getString("password"); //Se obtiene la clave de usuario del formulario

	
		HttpSession sesion = request.getSession(true); //Se accede al entorno de la sesi�n
		
		if (sesion.getAttribute("usuario") != null) 
			isLoged = true;
		
		System.out.println("Usuario login...");

		if ((usuario != null) && (clave != null) && !isLoged) //Se si se han pasado usuario y clave
		{
			Boolean isNick = true;
			// check if user is nick or email
			if (usuario.contains("@")) {
				isNick = false;
			}
			
			if (isNick)
				System.out.println("Usuario: " + usuario);
			else
				System.out.println("Email: " + usuario);
			System.out.println("Pass: " + clave);

			AccesoBD con = AccesoBD.getInstance(); //Instancia de la clase factor�a AccesoBD

			if (con.comprobarUsuarioBD(usuario, clave, isNick)) // Se comprueba en la base de datos
			{ // Registramos al usuario en el entorno de la sesi�n
				UserBD user_session = con.obtenerUserBD(usuario);
				isLoged = true;
				sesion.setAttribute("usuario", user_session);
			} else // El usuario/clave no se encuentra en la BD
			{ // Registramos el error en el entorno de la sesi�n
				System.out.println("Usuario not loged");
				request.setAttribute("type", "login");
				request.setAttribute("msg", "Usuario y/o clave incorrectos");
			}
		} else { // Registramos el error en el entorno de la sesi�n
			request.setAttribute("type", "login");
			request.setAttribute("msg", "Falta introducir el usuario o la clave");
		}

		if (isLoged)
			response.sendRedirect("./usuario.html"); // /usuario.html not working
		else
			request.getRequestDispatcher("login.jsp").forward(request,response);
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Boolean isLoged = false;
		HttpSession sesion = request.getSession(true); //Se accede al entorno de la sesi�n
		
		if (sesion.getAttribute("usuario") != null) {
//			sesion.removeAttribute("usuario");
			System.out.println("user:" + ((UserBD) sesion.getAttribute("usuario")).getUsuario());
			isLoged = true;
		}
		
		if (isLoged)
			response.sendRedirect("./usuario.html");
		else
			request.getRequestDispatcher("login.jsp").forward(request,response);
	}
}



