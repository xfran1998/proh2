package p2;
import java.sql.*;
import p2.JavaBeans.*;
import java.util.ArrayList;
import java.util.List;

import p2.JavaBeans.ProductoBD;

public final class AccesoBD {

    private static AccesoBD instanciaUnica = null;
    private Connection conexionBD = null;

    public static AccesoBD getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new AccesoBD();
        }
        return instanciaUnica;
    }

    private AccesoBD() {
        abrirConexionBD();
    }

    public void abrirConexionBD() {
        if (conexionBD == null) { // daw es el nombre de la base de datos que hemos creado con anterioridad.
            String nombreConexionBD = "jdbc:mysql://localhost:3306/cascoshop";
            try { // root y sin clave es el usuario por defecto que crea XAMPP.
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexionBD = DriverManager.getConnection(nombreConexionBD, "root", "");
            } catch (Exception e) {
                System.err.println("No se ha podido conectar a la base de datos");
                System.err.println(e.getMessage());
            }
        }
    }
    
    public void cerrarConexionBD()
    {
        if (conexionBD != null)
        {
            try{
                conexionBD.close();
                conexionBD = null;
            }
            catch(Exception e){ //Error en la conexi�n con la BD
                System.err.println("No se ha completado la desconexi�n a la base de datos");
                System.err.println(e.getMessage());
            }
        }
    }
    
    public Boolean isBDConnected() {
    	return conexionBD != null;
    }
    
    public List<ProductoBD> obtenerProductosBD() {
        abrirConexionBD();
        ArrayList<ProductoBD> productos = new ArrayList<>();
        try {
            String con;
            Statement s = conexionBD.createStatement();
            // hay que tener en cuenta las columnas de vuestra tabla de productos
            // tambi�n se puede utilizar una consulta del tipo:
            // con = "SELECT * FROM productos";
            con = "SELECT * FROM productos";
            ResultSet resultado = s.executeQuery(con);
            
            while (resultado.next()) {
                ProductoBD producto = new ProductoBD();
                producto.setId(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setDescripcion(resultado.getString("descripcion"));
                producto.setPrecio(resultado.getFloat("precio"));
                producto.setStock(resultado.getInt("existencias"));
                producto.setImagen(resultado.getString("imagen"));
                productos.add(producto);
            }
        } catch (Exception e) {
            System.err.println("Error ejecutando la consulta a la base de datos");
            System.err.println(e.getMessage());
        }
        
        cerrarConexionBD();
        return productos;
    }
    
    public List<ProductoBD> obtenerPedidosBD(String user) {
        abrirConexionBD();
        ArrayList<ProductoBD> productos = new ArrayList<>();
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT * FROM `pedidos` WHERE id_user = (SELECT id FROM `usuarios` WHERE usuario = '" + user + "')";
            ResultSet resultado = s.executeQuery(con);
            
            while (resultado.next()) {
                ProductoBD producto = new ProductoBD();
                producto.setId(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setDescripcion(resultado.getString("descripcion"));
                producto.setPrecio(resultado.getFloat("precio"));
                producto.setStock(resultado.getInt("existencias"));
                producto.setImagen(resultado.getString("imagen"));
                productos.add(producto);
            }
        } catch (Exception e) {
            System.err.println("Error ejecutando la consulta a la base de datos");
            System.err.println(e.getMessage());
        }
        
        cerrarConexionBD();
        return productos;
    }
    
    public boolean comprobarUsuarioBD(String usuario, String clave, Boolean isNick) {
        abrirConexionBD();
        try {
            String con;
            System.out.print("isNick: " + isNick + '\n');
            // isNick es true si se usa el nick, false si se usa el email
            if (isNick)
               con = "SELECT * FROM `usuarios` WHERE usuario=? AND clave=?";
            else
               con = "SELECT * FROM `usuarios` WHERE email=? AND clave=?";
            
                
            PreparedStatement stmt = conexionBD.prepareStatement(con);
            stmt.setString(1, usuario);
            stmt.setString(2, clave);
            // Consulta, buscamos una correspondencia usuario/clave
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) // El usuario/clave se encuentra en la BD
            {
            	cerrarConexionBD();
                return true;
            } else // El usuario/clave no se encuentra en la BD
            {
            	cerrarConexionBD();
                return false;
            }
        } catch (Exception e) { // Error en la conexi�n con la BD
            System.err.println("Error verificando usuaro/clave2");
            System.err.println(e.getMessage());
            cerrarConexionBD();
            return false;
        }
    }
    
    public UserBD obtenerUserBD(String usuario) {
    	abrirConexionBD();
    	UserBD user = new UserBD();
        try {
            String con;
            con = "SELECT * FROM usuarios WHERE usuario = ?";
            PreparedStatement stmt = conexionBD.prepareStatement(con);
            // Statement s = conexionBD.createStatement();
            stmt.setString(1, usuario);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {            	
            	user.setId(resultado.getInt("codigo"));
            	user.setActivo(resultado.getInt("activo"));
            	user.setAdmin(resultado.getInt("admin"));
            	user.setUsuario(resultado.getString("usuario"));
            	user.setEmail(resultado.getString("email"));
            	user.setClave(resultado.getString("clave"));
            	user.setNombre(resultado.getString("nombre"));
            	user.setApellidos(resultado.getString("apellidos"));
            	user.setDomicilio(resultado.getString("domicilio"));
            	user.setPoblacion(resultado.getString("poblacion"));
            	user.setProvincia(resultado.getString("provincia"));
            	user.setCp(resultado.getString("cp"));
            	user.setTelefono(resultado.getString("telefono"));
            	System.out.println("User Setted");
            }
            else{
            	System.out.println("User NO Setted");
            }
            
        } catch (Exception e) {
            System.err.println("Error ejecutando obtener usuarios");
            System.err.println(e.getMessage());
        }
    	
        cerrarConexionBD();
    	return user;
    }
    
    public UserBD obtenerUserBD(int id_user) {
    	abrirConexionBD();
    	UserBD user = new UserBD();
        try {
            String con;
            con = "SELECT * FROM usuarios WHERE codigo = ?";
            PreparedStatement stmt = conexionBD.prepareStatement(con);
            // Statement s = conexionBD.createStatement();
            stmt.setInt(1, id_user);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {            	
            	user.setId(resultado.getInt("codigo"));
            	user.setActivo(resultado.getInt("activo"));
            	user.setAdmin(resultado.getInt("admin"));
            	user.setUsuario(resultado.getString("usuario"));
            	user.setEmail(resultado.getString("email"));
            	user.setClave(resultado.getString("clave"));
            	user.setNombre(resultado.getString("nombre"));
            	user.setApellidos(resultado.getString("apellidos"));
            	user.setDomicilio(resultado.getString("domicilio"));
            	user.setPoblacion(resultado.getString("poblacion"));
            	user.setProvincia(resultado.getString("provincia"));
            	user.setCp(resultado.getString("cp"));
            	user.setTelefono(resultado.getString("telefono"));
            	System.out.println("User Setted");
            }
            else{
            	System.out.println("User NO Setted");
            }
            
        } catch (Exception e) {
            System.err.println("Error ejecutando obtener usuarios");
            System.err.println(e.getMessage());
        }
    	
        cerrarConexionBD();
    	return user;
    }

    public void registrarUsuarioBD(String usuario, String clave, String email) {
    	abrirConexionBD();
        try {
            String con;
            con = "INSERT INTO usuarios (activo, admin, usuario, clave, email) VALUES (1, 0, ?, ?, ?)";
            PreparedStatement stmt = conexionBD.prepareStatement(con);
            stmt.setString(1, usuario);
            stmt.setString(2, clave);
            stmt.setString(3, email);
            stmt.executeUpdate();             
        } catch (Exception e) {
            System.err.println("Error ejecutando registro usuarios");
            System.err.println(e.getMessage());
        }
    	
        cerrarConexionBD();
    }

    public void cambiarUsuarioBD(int id_usuario, String campo, String dato){
        abrirConexionBD();

        try{
            String con;
            con = "UPDATE usuarios SET ? = ? WHERE usuario = ?";
            PreparedStatement stmt = conexionBD.prepareStatement(con);
            stmt.setString(1, campo);
            stmt.setString(2, dato);
            stmt.setInt(3, id_usuario);
            stmt.executeUpdate();
        }catch(Exception e){
            System.err.println("Error ejecutando cambiar usuario");
            System.err.println(e.getMessage());
        }

        cerrarConexionBD();
    }
}