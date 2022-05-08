package p2.test;
import p2.*;
import p2.JavaBeans.ProductoBD;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ProductoDBTest {
	@Test
	void testObtenerProductosBD() {
		List<ProductoBD> productos = AccesoBD.getInstance().obtenerProductosBD();
		assertNotNull(productos);
	}

	@Test
	void testObtenerPrimerProductosBD() {
		List<ProductoBD> productos = AccesoBD.getInstance().obtenerProductosBD();
		ProductoBD firstItem = productos.get(0); 
		
		ProductoBD expectedItem = new ProductoBD();
		expectedItem.setId(1);
		expectedItem.setNombre("Bulbasaur");
        expectedItem.setDescripcion("Pokemon tipo planta, se encuentra en la región de Kanto.");
        expectedItem.setPokedex(1);
        expectedItem.setPrecio(20.00f);
        expectedItem.setStock(17);
        expectedItem.setImagen("https://pokeres.bastionbot.org/images/pokemon/1.png");
        expectedItem.setEvolucion(1);
        expectedItem.setIv(78);
        
        // Expected producto is iqual to the first item of my BBDD (Bulbasaur)
		assertEquals(firstItem.getId(), expectedItem.getId());
		assertEquals(firstItem.getNombre(), expectedItem.getNombre());
		assertEquals(firstItem.getDescripcion(), expectedItem.getDescripcion());
		assertEquals(firstItem.getPokedex(), expectedItem.getPokedex());
		assertEquals(firstItem.getPrecio(), expectedItem.getPrecio());
		assertEquals(firstItem.getImagen(), expectedItem.getImagen());
		assertEquals(firstItem.getEvolucion(), expectedItem.getEvolucion());
		assertEquals(firstItem.getIv(), expectedItem.getIv());
	}
}
