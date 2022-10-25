package Examen3;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pizzas {

	private JFrame frame;
	private JRadioButton radchica;
	private JRadioButton radMediana;
	private JRadioButton radGrande;
	private JLabel lblCantidad_1;
	private JSlider slCantidad;
	private JLabel lblPrecio;
	private JCheckBox chkalitas;
	private JCheckBox chkpapas;
	private JCheckBox chkrefresco;
	int tamaño = 0;
	int cantidad = 0;
	int precio = 0;
	int extras = 0;

	ButtonGroup grupo = new ButtonGroup();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pizzas window = new Pizzas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Pizzas() {
		initialize();
		grupo.add(radchica);
		grupo.add(radMediana);
		grupo.add(radGrande);

	}

public void Precio() {
lblCantidad_1.setText("" +slCantidad.getValue());
lblPrecio.setText(""+(tamaño*slCantidad.getValue()+extras));

	
}

	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setBounds(100, 100, 458, 365);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
        
		lblPrecio = new JLabel("");
		lblPrecio.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPrecio.setBounds(106, 250, 198, 56);
		frame.getContentPane().add(lblPrecio);

		lblCantidad_1 = new JLabel("");
		lblCantidad_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCantidad_1.setForeground(new Color(0, 0, 0));
		lblCantidad_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCantidad_1.setBounds(368, 30, 60, 48);
		frame.getContentPane().add(lblCantidad_1);

		slCantidad = new JSlider();
		slCantidad.setForeground(new Color(0, 0, 0));
		slCantidad.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Precio();
			}
		});
		slCantidad.setPaintLabels(true);
		slCantidad.setMajorTickSpacing(1);
		slCantidad.setPaintTicks(true);
		slCantidad.setValue(1);
		slCantidad.setMinimum(1);
		slCantidad.setMaximum(10);
		slCantidad.setBounds(47, 42, 311, 50);
		frame.getContentPane().add(slCantidad);

		JLabel lblNewLabel = new JLabel("Tamaño");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(37, 106, 103, 31);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblExtras = new JLabel("Extras");
		lblExtras.setForeground(new Color(0, 0, 0));
		lblExtras.setFont(new Font("Arial", Font.PLAIN, 15));
		lblExtras.setBounds(225, 103, 83, 31);
		frame.getContentPane().add(lblExtras);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setForeground(new Color(0, 0, 0));
		lblCantidad.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCantidad.setBounds(37, 10, 103, 21);
		frame.getContentPane().add(lblCantidad);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(new Color(0, 0, 0));
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTotal.setBounds(37, 250, 49, 48);
		frame.getContentPane().add(lblTotal);

		radchica = new JRadioButton("Chica");
		radchica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radchica.isSelected())
					tamaño = 70;
				Precio();
			}
		});
		radchica.setForeground(new Color(0, 0, 0));
		radchica.setFont(new Font("Arial", Font.PLAIN, 15));
		radchica.setBounds(60, 144, 103, 31);
		frame.getContentPane().add(radchica);

		radMediana = new JRadioButton("Mediana");
		radMediana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radMediana.isSelected())
					tamaño = 95;
				Precio();
			}
		});
		radMediana.setForeground(new Color(0, 0, 0));
		radMediana.setFont(new Font("Arial", Font.PLAIN, 15));
		radMediana.setBounds(60, 178, 103, 31);
		frame.getContentPane().add(radMediana);

		radGrande = new JRadioButton("Grande");
		radGrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radGrande.isSelected())
					tamaño = 120;
				Precio();
			}
		});
		radGrande.setForeground(new Color(0, 0, 0));
		radGrande.setFont(new Font("Arial", Font.PLAIN, 15));
		radGrande.setBounds(60, 212, 103, 31);
		frame.getContentPane().add(radGrande);

		chkpapas = new JCheckBox("Papas");
		chkpapas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkpapas.isSelected())
					extras += 50;
				else {
					extras -= 50;

				}

				Precio();
			}
		});
		chkpapas.setFont(new Font("Arial", Font.PLAIN, 15));
		chkpapas.setBounds(225, 149, 93, 21);
		frame.getContentPane().add(chkpapas);

		chkalitas = new JCheckBox("Alitas");
		chkalitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkalitas.isSelected())
					extras += 70;
				else {
					extras -= 70;

				}
				Precio();
			}
		});
		chkalitas.setFont(new Font("Arial", Font.PLAIN, 15));
		chkalitas.setBounds(225, 183, 93, 21);
		frame.getContentPane().add(chkalitas);

		chkrefresco = new JCheckBox("Refresco");
		chkrefresco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkrefresco.isSelected())
					extras += 30;
				else {
					extras -= 30;

				}
				Precio();
			}
		});
		chkrefresco.setFont(new Font("Arial", Font.PLAIN, 15));
		chkrefresco.setBounds(225, 217, 111, 21);
		frame.getContentPane().add(chkrefresco);

	}
}