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
        String nombre = user.getNombre();
        String email = user.getEmail();
        String tel = user.getTelefono();
    %>
    <div id="profile-container" class="card">
        <div class="card-body">
            <aside>
                <img src="img/avatar.png" alt="avatar perfil">
                <!-- <p class="neuromorphic3 neu-in">XFran1998X</p> -->
                <p class="neuromorphic3 neu-in"><%=nick%></p>
            </aside>
            <div id="profile-info">
                <h2>Nombre</h2>
                <p><%=nombre%></p>
                <h2>Correo</h2>
                <p><%=email%></p>
                <h2>Telefono</h2>
                <p><%=tel%></p>
            </div>
        </div>
    </div>
</body>
</html>