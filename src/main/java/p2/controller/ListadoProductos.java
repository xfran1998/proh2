/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import p2.AccesoBD;
import p2.JavaBeans.*;

/**
 *
 * @author Fran
 */
@WebServlet(name = "ListadoProductos", urlPatterns = {"/productos.html"})
public class ListadoProductos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entrando en productos");
        List<ProductoBD> productos = AccesoBD.getInstance().obtenerProductosBD();
        request.setAttribute("lista", productos);
        
        request.getRequestDispatcher("/productos.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
