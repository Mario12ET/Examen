package Examen3;

public class Venta {
int id;
String descripcion;
int cantidad;
public Venta(int id, String descripcion, int cantidad, double precio, double importe) {
	super();
	this.id = id;
	this.descripcion = descripcion;
	this.cantidad = cantidad;
	this.precio = precio;
	this.importe = importe;
}
public Venta() {
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public double getPrecio() {
	return precio;
}
public void setPrecio(double precio) {
	this.precio = precio;
}
public double getImporte() {
	return importe;
}
public void setImporte(double importe) {
	this.importe = importe;
}
double precio;
double importe;
}
