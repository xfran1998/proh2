package p2.test;
import p2.*;
import p2.JavaBeans.ProductoBD;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class LoginBDTest {

	@Test
	void testObtenerUsuario() {
		UserBD productos = AccesoBD.getInstance().obtenerProductosBD();
		ProductoBD firstItem = productos.get(0); 
	}

}
