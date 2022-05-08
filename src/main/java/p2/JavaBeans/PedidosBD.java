package p2.JavaBeans;

import java.sql.Date;
import java.util.ArrayList;

public class PedidosBD {
	int id;
	int id_user;
	Date date;
	float importe;
	int estado;
	ArrayList<DetallesBD> detalles;
}
