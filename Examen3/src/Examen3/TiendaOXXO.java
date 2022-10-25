package Examen3;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class TiendaOXXO {

	private JFrame frmTiendaOxxo;
	private JLabel lblLogo;
	private JButton btnAgregar;
	@SuppressWarnings("rawtypes")
	private JComboBox cboProducto;
	private JSpinner spnCantidad;
	private JLabel lblImporte;
	private JLabel lblPrecio;
	private JTable tblProductos;
	String productos[] = { "Sabritas", "Coca Cola", "Magnum", "Cafe", "Sopa", "Galletas", "Chicle", "Peñafiel",
			"Cigarro", "Tequila" };
	double precios[] = { 12.5, 14.5, 35, 30, 20, 17.5, 2, 17, 60, 200 };
	double precio = 0;
	int cantidad = 0;
	double subtotal = 0;
	ArrayList<Venta> listaVentas = new ArrayList<Venta>();
	private JScrollPane scrollPane;
	DefaultTableModel modelo = new DefaultTableModel();
	private JLabel lblTotal;
	private JLabel lblIVA;
	private JLabel lblSubtotal;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TiendaOXXO window = new TiendaOXXO();
					window.frmTiendaOxxo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean buscarVenta(Venta nueva) {
		for (Venta v : listaVentas) {
			if (v.getId() == nueva.getId()) {
				int nuevaCantidad = v.getCantidad() + nueva.getCantidad();
				v.setImporte(v.getPrecio() * nuevaCantidad);
				return true;
			}
		}

		return false;

	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		for (Venta v : listaVentas) {
			Object x[] = new Object[4];
			x[0] = v.getDescripcion();
			x[1] = aMoneda(v.getPrecio());
			x[2] = v.getCantidad();
			x[3] = aMoneda(v.getImporte());
			modelo.addRow(x);
			subtotal += v.getImporte();
		}

		double iva = subtotal * 0.16;
		double total = subtotal + iva;
		lblSubtotal.setText(aMoneda(subtotal));
		lblIVA.setText(aMoneda(iva));
		lblTotal.setText(aMoneda(total));
		tblProductos.setModel(modelo);
	}

	public TiendaOXXO() {
		initialize();
		modelo.addColumn("DESCRIPCION");
		modelo.addColumn("PRECIO U");
		modelo.addColumn("CANTIDAD");
		modelo.addColumn("IMPORTE");
		calcularPrecio();
	}

	public void calcularPrecio() {
		precio = precios[cboProducto.getSelectedIndex()];
		cantidad = Integer.parseInt(spnCantidad.getValue().toString());
		lblPrecio.setText((aMoneda(precio)));
		lblImporte.setText(aMoneda(precio * cantidad));
	}

	public String aMoneda(double precio) {
		return "$" + Math.round(precio * 100.0) / 100.0 + " MXN";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {

		frmTiendaOxxo = new JFrame();
		frmTiendaOxxo.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\karin\\Downloads\\oxxo.png"));
		frmTiendaOxxo.setTitle("Tienda OXXO");
		frmTiendaOxxo.setBounds(100, 100, 937, 523);
		frmTiendaOxxo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTiendaOxxo.getContentPane().setLayout(null);
		frmTiendaOxxo.setLocationRelativeTo(null);

		cboProducto = new JComboBox();
		cboProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularPrecio();
			}
		});
		cboProducto.setModel(new DefaultComboBoxModel(new String[] { "SABRITAS", "COCA COLA", "MAGNUM", "CAFE", "SOPA",
				"GALLETAS", "CHICLE", "PEÑAFIEL", "CIGARRO", "TEQUILA" }));
		cboProducto.setBounds(119, 113, 84, 22);
		frmTiendaOxxo.getContentPane().add(cboProducto);

		lblLogo = new JLabel("");
		lblLogo.setOpaque(true);
		lblLogo.setBounds(245, 0, 280, 95);
		frmTiendaOxxo.getContentPane().add(lblLogo);
		frmTiendaOxxo.setSize(895, 537);
		Image icono = Toolkit.getDefaultToolkit().getImage("C:\\Users\\karin\\Downloads\\oxxo.png");
		lblLogo.setIcon(
		new ImageIcon(icono.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH)));

		JLabel lblNewLabel = new JLabel("Precio");
		lblNewLabel.setBounds(238, 117, 46, 14);
		frmTiendaOxxo.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Producto");
		lblNewLabel_1.setBounds(33, 117, 76, 14);
		frmTiendaOxxo.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Cantidad");
		lblNewLabel_2.setBounds(33, 171, 76, 14);
		frmTiendaOxxo.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Importe");
		lblNewLabel_3.setBounds(238, 171, 46, 14);
		frmTiendaOxxo.getContentPane().add(lblNewLabel_3);

		lblPrecio = new JLabel("$ 0.00 MXN");
		lblPrecio.setBounds(322, 117, 95, 14);
		frmTiendaOxxo.getContentPane().add(lblPrecio);

		lblImporte = new JLabel("$ 0.00 MXN");
		lblImporte.setBounds(322, 171, 84, 14);
		frmTiendaOxxo.getContentPane().add(lblImporte);

		lblSubtotal = new JLabel("$ 0.00 MXN");
		lblSubtotal.setBounds(570, 404, 84, 14);
		frmTiendaOxxo.getContentPane().add(lblSubtotal);

		lblIVA = new JLabel("$ 0.00 MXN");
		lblIVA.setBounds(570, 429, 84, 14);
		frmTiendaOxxo.getContentPane().add(lblIVA);

		lblTotal = new JLabel("$ 0.00 MXN");
		lblTotal.setBounds(570, 454, 84, 14);
		frmTiendaOxxo.getContentPane().add(lblTotal);

		JLabel lblNewLabel_5 = new JLabel("SUBTOTAL");
		lblNewLabel_5.setBounds(460, 404, 65, 14);
		frmTiendaOxxo.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("IVA");
		lblNewLabel_6.setBounds(460, 429, 65, 14);
		frmTiendaOxxo.getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("TOTAL");
		lblNewLabel_7.setBounds(460, 454, 46, 14);
		frmTiendaOxxo.getContentPane().add(lblNewLabel_7);

		btnAgregar = new JButton("");
		Image Agregar = Toolkit.getDefaultToolkit().getImage("C:\\Users\\karin\\Downloads\\agregar.png");
		btnAgregar.setIcon(new ImageIcon(Agregar.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Venta venta = new Venta();
				venta.setId(cboProducto.getSelectedIndex());
				venta.setDescripcion(cboProducto.getSelectedItem().toString());
				venta.setPrecio(precio);
				venta.setCantidad(cantidad);
				venta.setImporte(precio * cantidad);
				if (!buscarVenta(venta)) {
					listaVentas.add(venta);
				}
				borrarVenta();
				actualizarTabla();
			}
		});
		btnAgregar.setBounds(427, 113, 65, 59);
		frmTiendaOxxo.getContentPane().add(btnAgregar);

		spnCantidad = new JSpinner();
		spnCantidad.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				calcularPrecio();

			}
		});
		spnCantidad.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spnCantidad.setBounds(119, 168, 84, 20);
		frmTiendaOxxo.getContentPane().add(spnCantidad);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 228, 772, 135);
		frmTiendaOxxo.getContentPane().add(scrollPane);

		tblProductos = new JTable();
		tblProductos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "DESCRIPCION", "PRECIO U", "CANTIDAD", "IMPORTE" }));
		scrollPane.setViewportView(tblProductos);
	}

	public void borrarVenta() {
		precio = 0;
		cantidad = 1;
		cboProducto.setSelectedIndex(0);
		spnCantidad.setValue(1);
		calcularPrecio();		  
	}
}