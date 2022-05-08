<%@page language="java" contentType="text/html" import="p2.JavaBeans.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
</head>
<body>  
	<%  
        UserBD user = (UserBD)session.getAttribute("usuario"); 
        String nick = user.getUsuario();
        String email = user.getEmail();
		String clave = user.getClave();
        String nombre = user.getNombre();
		String apellidos = user.getApellidos();
		String domicilio = user.getDomicilio();
		String poblacion = user.getPoblacion();
		String provincia = user.getProvincia();
		String cp = user.getCp();
        String telefono = user.getTelefono();
    %>
    <div class="row">
		<form id="form-contacto" action="#" class="neuromorphic2">
			<label for="nick">Usuario</label>
			<input type="text" name="usuario" id="usuario" class="form__input" value="<%=nick%>">
			<label for="email">Email</label>
			<input type="email" name="email" id="email" class="form__input" value="<%=email%>">
			<label for="clave">Clave</label>
			<input type="password" name="clave" id="clave" class="form__input" value="<%=clave%>">
			<label for="nombre">Nombre</label>
			<input type="text" name="nombre" id="nombre" class="form__input" value="<%=nombre%>">
			<label for="nombre">Apellidos</label>
			<input type="text" name="apellidos" id="apellidos" class="form__input">
			<label for="nombre">Domicilio</label>
			<input type="text" name="domicilio" id="domicilio" class="form__input">
			<label for="nombre">Poblacion</label>
			<input type="text" name="poblacion" id="poblacion" class="form__input">
			<label for="nombre">Provincia</label>
			<input type="text" name="provincia" id="provincia" class="form__input">
			<label for="provincia">CP</label>
			<input type="text" name="cp" id="cp" class="form__input" pattern="[0-9]{5}">
			<label for="telefono">Teléfono</label>
			<input type="tel" name="telefono" id="telefono" pattern="[0-9]{9}" class="form__input">
			<input type="submit" value="Actualizar" class="neuromorphic2 bg-neuromorphic">
		</form>
	</div>
	<script>
		var mi_form_actualizar = document.getElementById("form-contacto");

		mi_form_actualizar.addEventListener("submit", function(e){
			e.preventDefault();
			
			var body_form = {};
	
			mi_form_actualizar.querySelectorAll('input').forEach( el => {
				body_form[el.name] = el.value;
			});
			
			put_page_async(body_form);
		});
		
		async function put_page_async(body_form){
			console.log('Enviando FORM')
			var result_put = await get_page_async('configuracion', 'PUT', body_form, null, true);

			result_put = JSON.parse(result_put);

			if (result_put.status >= 400) {
				console.log('Error al actualizar')
				alert('Error al actualizar');
			}
			else if (result_put.status == 200) {
				console.log('Actualizado')
				alert('Actualizado');
			}

		}
	</script>
</body>
</html>