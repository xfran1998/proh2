package p2.JavaBeans;

public class DetallesBD {
	int id;
	int id_pedido;
	int id_producto;
	int cantidad;
	float precio_unitario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio_unitario() {
		return precio_unitario;
	}
	public void setPrecio_unitario(float precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
}
