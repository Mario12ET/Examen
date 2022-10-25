package Examen3;

import java.util.ArrayList;

public class Cuenta {
	String tipocuenta;
	double montoinicial;
	ArrayList<Movimiento> listaMovimientos;

	public Cuenta(String tipocuenta, double montoinicial) {
		super();
	}

	public Cuenta() {
		listaMovimientos = new ArrayList<Movimiento>();

	}

	public String getTipocuenta() {
		return tipocuenta;
	}

	public void setTipocuenta(String tipocuenta) {
		this.tipocuenta = tipocuenta;
	}

	public double getMontoinicial() {
		return montoinicial;
	}

	public void setMontoinicial(double montoinicial) {
		this.montoinicial = montoinicial;
	}

	public void addMovimiento(Movimiento m) {
		listaMovimientos.add(m);
	}

	public ArrayList<Movimiento> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(ArrayList<Movimiento> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}

}