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
			<div id="body-content-wrapper" class="pd-tb20">
				<h1>Ticket Compra</h1>
				<br>
				<br>
			   <table>
				   <thead>
					   <tr>
						   <th>Nombre</th>
						   <th>Cantidad</th>
						   <th>Precio</th>
						   <th>Total</th>
					   </tr>
				   </thead>
				   <tbody>
			<%	
            	List<ProductoBD> productos = (List<ProductoBD>)request.getAttribute("carrito");
			float precio_total = 0;
               for (ProductoBD producto : productos) {
            	   int id = producto.getId();
                   String nombre = producto.getNombre();
                   String descripcion = producto.getDescripcion();
                   float precio = producto.getPrecio();
                   int existencias = producto.getStock();
                   String imagen = producto.getImagen();
                   precio_total += precio*existencias;
             %>
						<tr>
							<td class="prod-nombre"><%=nombre%></td>
							<td class="prod-cantidad">x<%=existencias%></td>
							<td class="prod-precio"><%=precio%>€</td>
							<td class="prod-precio-total precio-producto"><%=precio*existencias%>€</td>
						</tr>
				
			 <%
			}
			%>
						<tr>
							<td class="total-precio-tittle">Precio Total</td>
							<td></td>
							<td></td>
							<td class="total-precio"><%=precio_total %>€</td>
						</tr>
					</tbody>
				</table>
				<div class="row">
					<button id="btn-back" class="neuromorphic2 btn-carrito">Volver</button>
					<button id="btn-comprar" class="neuromorphic2 btn-carrito">Pagar</button>
				</div>
			</div>
		</div>
	</div>
	<script src="js/productos.js"></script>
	<script>
		var volver = document.getElementById("btn-back");
		var comprar = document.getElementById("btn-comprar");

		console.log(volver);
		console.log(comprar);
		volver.addEventListener("click", function(){
			(async => {
				console.log("Volviendo...");
				get_page_async('carrito');
			})();
		});

		comprar.addEventListener("click", function(){
			console.log("Comprando...");
			popUpCard();
		});

		// Generate a credit card popup to be used in the checkout page
		function popUpCard(){
			// Create credit card popup element
			var cardPopup = document.createElement("div");
			cardPopup.className = "card-popup neuromorphic2 start-popup";
			cardPopup.id = "card-popup";
			cardPopup.innerHTML = "<div class='card-popup-content'>" +
									"<div class='card-popup-header'>" +
										"<h3>Pago</h3>" +
									"</div>" +
									"<form class='card-popup-body'>" +
										"<h2>Datos de la tarjeta</h2>" +
										"<input type='text' placeholder='Nombre del titular' class='form__input'>" +
										"<input type='text' placeholder='Número de tarjeta' class='form__input'>" +
										"<input type='text' placeholder='Fecha de expiración' class='form__input'>" +
										"<input type='text' placeholder='Código de seguridad' class='form__input'>" +
										"<input type='submit' class='neuromorphic2 btn-carrito' value='Pagar'>"+
								"</form>";
			// Add the popup to the body
			document.body.appendChild(cardPopup);
			// Add the close button
			var closeButton = document.createElement("div");
			closeButton.className = "card-popup-close";
			closeButton.innerHTML = "<i class='fas fa-times'></i>";
			cardPopup.appendChild(closeButton);
			
			// Add the event listener to the close button
			closeButton.addEventListener("click", () => {
				cardPopup.classList.add("start-popup");
				setTimeout( () => {
					cardPopup.remove();
				}, 1000);
			});

			// Add the event listener to the pay button
			var payButton = document.getElementsByClassName("btn-carrito")[0];
			payButton.addEventListener("click", () => {
				cardPopup.classList.add("start-popup")
				setTimeout( () => {
					cardPopup.remove();
				}, 1000);	
				console.log("Pago realizado");
			});
			
			setTimeout( () => {
				cardPopup.classList.remove("start-popup");
			}, 50);		
		}
	</script>
</body>
</body>
</html>