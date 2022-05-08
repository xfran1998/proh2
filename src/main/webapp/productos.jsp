<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html" import="p2.JavaBeans.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<div class="row">
		<div id="product-container-main" class="card neuromorphic2 w-all center">
			<!-- TODO: Crear plantilla de productos -->
			<%	
            	List<ProductoBD> productos = (List<ProductoBD>)request.getAttribute("lista");

               for (ProductoBD producto : productos) {
            	   int id = producto.getId();
                   String nombre = producto.getNombre();
                   String descripcion = producto.getDescripcion();
                   float precio = producto.getPrecio();
                   int existencias = producto.getStock();
                   String imagen = producto.getImagen();
             %>
			<div class="product-container neuromorphic2">
				<div class="img-container neuromorphic">
					<img src="img/<%=imagen %>" alt="auriculares">
				</div>
				<div class="row">
					<div class="precio-container card neuromorphic2"><p><%=precio%></p></div>
					<div class="cantidad-container card neuromorphic2"><button data-id="<%=id%>" data-img="<%=imagen%>" data-nombre="<%=nombre%>" data-existencias="<%=existencias%>" data-precio="<%=precio%>" class="add-carrito"><i class="fa-solid fa-cart-plus"></i></button></div>
				</div>
				<div class="description-prod">
					<p><%=descripcion%></p>
				</div>
			</div>
			<%
               }
			%>
		</div>
	</div>
	<script src="js/productos.js"></script>
</body>
</body>
</html>