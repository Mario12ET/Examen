package Examen3;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Banco_Cecytem {

	private JFrame frmBanco;
	private JTable tblMovimientos;

	ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	ArrayList<String> listaTipoCuenta = new ArrayList<String>();
	DefaultTableModel modelMovs = new DefaultTableModel();
	Cuenta cuenta;
	private JTextField txtNombre;
	private JTextField txtTele;
	private JTextField txtDirec;
	@SuppressWarnings("rawtypes")
	private JComboBox cboClien;
	@SuppressWarnings("rawtypes")
	private JComboBox cboCuenta;
	private JTextField txtMonto;
	@SuppressWarnings("rawtypes")
	private JComboBox cboClientes;
	@SuppressWarnings("rawtypes")
	private JComboBox cboTipo;
	@SuppressWarnings("rawtypes")
	private JComboBox cboMov;
	private JTextField txtmon;
	protected Cliente cliente;
	private JLabel lblNombreCliente;
	private JLabel lblCelCliente;
	private JLabel lblDirecClien;
	private JLabel lblTipoClien;
	private JLabel lblMonClien;
	private JLabel lblSaldos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Banco_Cecytem window = new Banco_Cecytem();
					window.frmBanco.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Banco_Cecytem() {
		initialize();
		modelMovs.addColumn("CUENTA");
		modelMovs.addColumn("FECHA");
		modelMovs.addColumn("TIPO");
		modelMovs.addColumn("MONTO");
		tblMovimientos.setModel(modelMovs);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public String aMoneda(double cantidad) {
		cantidad = Math.round(cantidad * 100.0) / 100.0;
		DecimalFormat formato = new DecimalFormat("$ #,###.## MXN");
		return formato.format(cantidad);
	}

	public void verDatos() {
		cliente = listaClientes.get(cboTipo.getSelectedIndex());
		lblNombreCliente.setText(cliente.getNombre());
		lblCelCliente.setText(cliente.getTelefono());
		lblDirecClien.setText(cliente.getDireccion());

		if (cliente.getMiscuentas().size() > 0) {
			cuenta = cliente.getMiscuentas().get(cboTipo.getSelectedIndex());
			lblTipoClien.setText(cuenta.getTipocuenta());
			lblMonClien.setText(aMoneda(cuenta.getMontoinicial()));
		} else {
			lblTipoClien.setText("NO HAY CUENTA");
			lblMonClien.setText("NO HAY CUENTA");
		}
	}

	public void verMovimientos() {
		cliente = listaClientes.get(cboClien.getSelectedIndex());
		cuenta = cliente.getMiscuentas().get(cboCuenta.getSelectedIndex());
		double saldo = 0;
		while (modelMovs.getRowCount() > 0) {
			modelMovs.removeRow(0);
		}
		for (Movimiento m : cuenta.getListaMovimientos()) {
			Object mov[] = new Object[4];
			mov[0] = cuenta.getTipocuenta();
			mov[1] = m.getFechaMovimiento();
			mov[2] = m.getTipoMovimiento();
			mov[3] = aMoneda(m.getMonto());
			saldo += m.getMonto();
			modelMovs.addRow(mov);
		}
		tblMovimientos.setModel(modelMovs);
		lblSaldos.setText(aMoneda(saldo));
	}

	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public void refrescarCuentas() {
		cliente = listaClientes.get(cboTipo.getSelectedIndex());
		int i = 0;
		ArrayList<String> cuentas = new ArrayList<String>();
		for (Cuenta c : cliente.getMiscuentas()) {
			cuentas.add(c.getTipocuenta());
		}
		cboTipo.setModel(new DefaultComboBoxModel(cuentas.toArray()));

	}

	public void borrarFormCuenta() {
		cboClien.setSelectedIndex(0);
		cboTipo.setSelectedIndex(0);
		txtMonto.setText("");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void llenarCombosTipoCuenta() {
		Object tipos[] = new Object[listaTipoCuenta.size()];
		int i = 0;
		for (String tipo : listaTipoCuenta) {
			tipos[i] = tipo;
			i++;
		}
		cboCuenta.setModel(new DefaultComboBoxModel(tipos));
		cboTipo.setModel(new DefaultComboBoxModel(tipos));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void llenarCombosCliente() {
		Object clientes[] = new Object[listaClientes.size()];
		int i = 0;
		for (Cliente c : listaClientes) {
			clientes[i] = c.getNombre();
			i++;
		}
		cboClien.setModel(new DefaultComboBoxModel(clientes));
		cboClientes.setModel(new DefaultComboBoxModel(clientes));
	}

	public void borrarFormCliente() {
		txtNombre.setText("");
		txtTele.setText("");
		txtDirec.setText("");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initialize() {
		frmBanco = new JFrame();
		frmBanco.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\karin\\Downloads\\CECYTEM.png"));
		frmBanco.setTitle("Banco");
		frmBanco.setBounds(100, 100, 659, 470);
		frmBanco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBanco.getContentPane().setLayout(null);
		frmBanco.setLocationRelativeTo(null);

		JLabel lblC = new JLabel("Banco");
		lblC.setBounds(28, 11, 91, 14);
		frmBanco.getContentPane().add(lblC);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("/img/CECYTEM.png"));
		lblLogo.setOpaque(true);
		lblLogo.setBounds(10, 32, 123, 118);
		frmBanco.getContentPane().add(lblLogo);
		Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/CECYTEM.png"));
		lblLogo.setIcon(
				new ImageIcon(img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH)));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "NUEVO CLIENTE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(143, 11, 223, 153);
		frmBanco.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setBounds(10, 29, 76, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("TELEFONO");
		lblNewLabel_1.setBounds(10, 54, 71, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("DIRECCION");
		lblNewLabel_2.setBounds(10, 79, 71, 14);
		panel.add(lblNewLabel_2);

		txtNombre = new JTextField();
		txtNombre.setBounds(82, 26, 131, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTele = new JTextField();
		txtTele.setColumns(10);
		txtTele.setBounds(82, 51, 131, 20);
		panel.add(txtTele);

		txtDirec = new JTextField();
		txtDirec.setColumns(10);
		txtDirec.setBounds(82, 79, 131, 20);
		panel.add(txtDirec);

		JButton btnAgregarCliente = new JButton("Agregar Cliente");
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente c = new Cliente();
				c.setNombre(txtNombre.getText());
				c.setTelefono(txtTele.getText());
				c.setDireccion(txtDirec.getText());
				listaClientes.add(c);
				borrarFormCliente();
				llenarCombosCliente();

			}
		});
		btnAgregarCliente.setBounds(46, 104, 149, 33);
		panel.add(btnAgregarCliente);

		JPanel Panel = new JPanel();
		Panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"NUEVA CUENTA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Panel.setBounds(369, 11, 277, 153);
		frmBanco.getContentPane().add(Panel);
		Panel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Cliente");
		lblNewLabel_3.setBounds(10, 27, 73, 14);
		Panel.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Tipo de Cuenta");
		lblNewLabel_3_1.setBounds(10, 65, 73, 14);
		Panel.add(lblNewLabel_3_1);

		cboClien = new JComboBox();
		cboClien.setBounds(95, 23, 128, 22);
		Panel.add(cboClien);

		cboCuenta = new JComboBox();
		cboCuenta.setBounds(93, 61, 130, 22);
		Panel.add(cboCuenta);

		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipoCuenta = JOptionPane.showInputDialog("TIPO CUENTA");
				listaTipoCuenta.add(tipoCuenta);
				llenarCombosTipoCuenta();
			}
		});
		btnNewButton_1.setBounds(226, 61, 41, 23);
		Panel.add(btnNewButton_1);

		JLabel lblNewLabel_4 = new JLabel("Monto");
		lblNewLabel_4.setBounds(10, 96, 46, 14);
		Panel.add(lblNewLabel_4);

		txtMonto = new JTextField();
		txtMonto.setBounds(95, 94, 128, 20);
		Panel.add(txtMonto);
		txtMonto.setColumns(10);

		JButton btnNewButton_2 = new JButton("Agregar Cuenta");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente = listaClientes.get(cboClien.getSelectedIndex());
				Cuenta cuenta = new Cuenta();
				cuenta.setTipocuenta(listaTipoCuenta.get(cboCuenta.getSelectedIndex()));
				cuenta.setMontoinicial(Double.parseDouble(txtMonto.getText()));
				cliente.addCuenta(cuenta);
				Movimiento m = new Movimiento();
				m.setFechaMovimiento(new SimpleDateFormat("dd/MM/yyyy").format(new Date(0)));
				m.setTipoMovimiento("Apertura");
				m.setMonto(Double.parseDouble(txtMonto.getText()));
				cuenta.addMovimiento(m);
				borrarFormCuenta();
				refrescarCuentas();
				verDatos();
				verMovimientos();

			}
		});
		btnNewButton_2.setBounds(48, 119, 190, 23);
		Panel.add(btnNewButton_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "MOVIMIENTOS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 175, 592, 87);
		frmBanco.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("CLIENTES");
		lblNewLabel_5.setBounds(10, 22, 94, 14);
		panel_1.add(lblNewLabel_5);

		cboClientes = new JComboBox();
		cboClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente = listaClientes.get(cboTipo.getSelectedIndex());
				refrescarCuentas();
				verMovimientos();
				verDatos();
			}
		});
		cboClientes.setBounds(10, 47, 94, 22);
		panel_1.add(cboClientes);

		JLabel lblNewLabel_5_1 = new JLabel("TIPO DE CUENTA");
		lblNewLabel_5_1.setBounds(114, 22, 94, 14);
		panel_1.add(lblNewLabel_5_1);

		cboTipo = new JComboBox();
		cboTipo.setBounds(114, 47, 94, 22);
		panel_1.add(cboTipo);

		JLabel lblNewLabel_5_2 = new JLabel("TIPO DE MOVIMIENTO");
		lblNewLabel_5_2.setBounds(218, 22, 117, 14);
		panel_1.add(lblNewLabel_5_2);

		cboMov = new JComboBox();
		cboMov.setModel(new DefaultComboBoxModel(new String[] {"DEPOSITO", "RETIRO"}));
		cboMov.setBounds(218, 47, 117, 22);
		panel_1.add(cboMov);

		JLabel lblNewLabel_4_1 = new JLabel("Monto");
		lblNewLabel_4_1.setBounds(350, 24, 46, 14);
		panel_1.add(lblNewLabel_4_1);

		txtmon = new JTextField();
		txtmon.setColumns(10);
		txtmon.setBounds(345, 48, 86, 20);
		panel_1.add(txtmon);

		JButton btnAgregarMov = new JButton("Agregar Movimiento");
		btnAgregarMov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            cliente = listaClientes.get(cboTipo.getSelectedIndex());
			cuenta =  cliente.getMiscuentas().get(cboTipo.getSelectedIndex());
			Movimiento m = new Movimiento();
			m.setFechaMovimiento(new SimpleDateFormat("25/10/2022").format(0));
			m.setTipoMovimiento(cboMov.getSelectedItem().toString());
			double monto=Double.parseDouble(txtmon.getText().toString());
			monto=m.getTipoMovimiento().endsWith("DEPOSITO")?monto:(monto*-1);
			m.setMonto(monto);
			cuenta.addMovimiento(m);
			verMovimientos();
			
			}
		});
		btnAgregarMov.setBounds(437, 47, 129, 23);
		panel_1.add(btnAgregarMov);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "DATOS DE CUENTA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 273, 193, 131);
		frmBanco.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 23, 76, 14);
		panel_2.add(lblNombre);

		lblNombreCliente = new JLabel("");
		lblNombreCliente.setBounds(96, 23, 87, 14);
		panel_2.add(lblNombreCliente);

		JLabel lblNewLabel_6_1 = new JLabel("Direccion");
		lblNewLabel_6_1.setBounds(10, 68, 76, 14);
		panel_2.add(lblNewLabel_6_1);

		lblCelCliente = new JLabel("");
		lblCelCliente.setBounds(96, 43, 87, 14);
		panel_2.add(lblCelCliente);

		JLabel lblNewLabel_6_2 = new JLabel("Telefono");
		lblNewLabel_6_2.setBounds(10, 48, 76, 14);
		panel_2.add(lblNewLabel_6_2);

		lblDirecClien = new JLabel("");
		lblDirecClien.setBounds(96, 68, 87, 14);
		panel_2.add(lblDirecClien);

		JLabel lblNewLabel_6_3 = new JLabel("Tipo de Cuenta");
		lblNewLabel_6_3.setBounds(10, 87, 76, 14);
		panel_2.add(lblNewLabel_6_3);

		lblTipoClien = new JLabel("");
		lblTipoClien.setBounds(96, 93, 87, 14);
		panel_2.add(lblTipoClien);

		JLabel lblNewLabel_6_4 = new JLabel("Monto Inicial");
		lblNewLabel_6_4.setBounds(10, 103, 76, 17);
		panel_2.add(lblNewLabel_6_4);

		JLabel lblTipoCliente = new JLabel("");
		lblTipoCliente.setBounds(96, 87, 87, 14);
		panel_2.add(lblTipoCliente);

		lblMonClien = new JLabel("");
		lblMonClien.setBounds(96, 104, 76, 14);
		panel_2.add(lblMonClien);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(248, 275, 354, 118);
		frmBanco.getContentPane().add(scrollPane);

		tblMovimientos = new JTable();
		tblMovimientos.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(tblMovimientos);

		lblSaldos = new JLabel("10,000,00 MXN");
		lblSaldos.setBounds(507, 404, 91, 16);
		frmBanco.getContentPane().add(lblSaldos);

		JLabel lblSaldo = new JLabel("SALDO");
		lblSaldo.setBounds(359, 404, 100, 16);
		frmBanco.getContentPane().add(lblSaldo);
	}
}
