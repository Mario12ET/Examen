package Examen3;

import java.util.ArrayList;

public class Cliente {
	String nombre;
	String telefono;
	String Direccion;
	ArrayList<Cuenta> miscuentas;

	public Cliente() {
		miscuentas = new ArrayList<Cuenta>();
	}

	public void addCuenta(Cuenta c) {
		miscuentas.add(c);
	}

	public ArrayList<Cuenta> getMiscuentas() {
		return miscuentas;
	}

	public Cliente(String nombre, String telefono, String direccion) {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

}
