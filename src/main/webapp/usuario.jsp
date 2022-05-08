<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Control</title>
</head>
<body>
    <nav id="user-nav"> 
        <ul>
            <li data-page="perfil">
                <div class="icon-nav"><i class="fa-solid fa-user"></i></div>
                <div class="number-nav">
                    <h3>4</h3>
                    <p>Notificaciones</p>
                </div>
            </li>
            <li data-page="historial">
                <div class="icon-nav"><i class="fa-solid fa-cart-shopping"></i></div>
                <div class="number-nav">
                    <h3>1</h3>
                    <p>Productos</p>
                </div>
            </li>
            <li data-page="configuracion">
                <div class="icon-nav"><i class="fa-solid fa-gear"></i></div>
                <div class="number-nav">
                    <h3>0</h3>
                    <p>Configuración</p>
                </div>
            </li>
        </ul>
    </nav>
    <div id="ubody">
    </div>
    <script src="js/user_nav.js"></script>
</body>
</html>