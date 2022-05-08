package p2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import p2.AccesoBD;
import p2.utils.JSON;

/**
 * Servlet implementation class Register
 */
@WebServlet(name = "Register", urlPatterns = {"/register.html"})
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String reqBody = JSON.getBody(request);
		JSON miParams = new JSON(reqBody);

		String usuario = miParams.getString("user");
		String email = miParams.getString("email");
		String clave = miParams.getString("password");
		String confirm_clave = miParams.getString("confirm-password");

		// En caso de que falle el JS or se haga un POST desde Curl o similares
		if (!clave.equals(confirm_clave)) {
			System.out.println("Las claves no coinciden");
			request.setAttribute("type", "register");
			request.setAttribute("msg", "Ha habido un fallo al registrarse. Las contraseñas no coinciden.");
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		
		System.out.println("Usuario: " + usuario);
		System.out.println("Email: " + email);
		System.out.println("Clave: " + clave);
		AccesoBD con = AccesoBD.getInstance();
		// check if user exists
		if ((usuario != null) && (clave != null) && (email != null)){
			if (con.comprobarUsuarioBD(usuario, clave, true)) {
				System.out.println("Usuario ya existe");
				request.setAttribute("type", "register");
				request.setAttribute("msg", "Usuario ya existe");
				request.getRequestDispatcher("login.jsp").forward(request,response);
				return;
			}
			
			if (con.comprobarUsuarioBD(email, clave, false)) {
				System.out.println("Email ya existe");
				request.setAttribute("type", "register");
				request.setAttribute("msg", "Correo ya registrado");
				request.getRequestDispatcher("login.jsp").forward(request,response);
				return;
			}
			
			con.registrarUsuarioBD(usuario, clave, email);

			
			System.out.println("Todo correcto");
			request.setAttribute("type", "info");
			request.setAttribute("msg", "Usuario registrado correctamente");
			request.getRequestDispatcher("login.jsp").forward(request,response);
			return;
		}
		else{
			request.setAttribute("type", "register");
			request.setAttribute("msg", "Falta introducir el usuario o la clave");
			request.getRequestDispatcher("login.jsp").forward(request,response);
			return;
		}
	}
}
