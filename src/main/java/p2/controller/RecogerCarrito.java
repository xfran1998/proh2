package p2.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import p2.AccesoBD;
import p2.JavaBeans.ProductoBD;
import p2.JavaBeans.UserBD;

import java.io.InputStreamReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Fran
 */
@WebServlet(name = "RecogerCarrito", urlPatterns = {"/recibo.html"})
public class RecogerCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entrando a recogerCarrito");
		AccesoBD con = AccesoBD.getInstance();
		HttpSession sesion = request.getSession(true); //Se accede al entorno de la sesi�n
		
		UserBD user = (UserBD)sesion.getAttribute("usuario");
				
		if (user == null) {
			// send error msg to client
			response.sendRedirect("./login.html");
			return;
		}
		
		ArrayList<ProductoBD> carrito = new ArrayList<ProductoBD>();
		JsonReader jsonReader = Json.createReader(
		new InputStreamReader(request.getInputStream()));
		JsonObject jobj = jsonReader.readObject();

		for (String key : jobj.keySet()) { // Se recorren todos los productos pasados en el JSON
			JsonObject prod = (JsonObject)jobj.getJsonObject(key);

			ProductoBD nuevo = new ProductoBD();
			nuevo.setId(Integer.parseInt(prod.getString("id")));
			nuevo.setNombre(prod.getString("nombre"));
			nuevo.setPrecio(Float.parseFloat(prod.getString("precio")));
			nuevo.setStock(Integer.parseInt(prod.getString("cantidad")));
			carrito.add(nuevo);

			ProductoBD prodBD = con.obtenerProductoBD(nuevo.getId());
			
			if (prodBD.getStock() < nuevo.getStock()) 
				nuevo.setStock(prodBD.getStock());

		}
		
		request.setAttribute("carrito", carrito);
        
        request.getRequestDispatcher("/recibo.jsp").forward(request, response);
	}
}
