package p6_IPC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Satisfaccion {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Satisfaccion window = new Satisfaccion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Satisfaccion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Satisfaccion del usuario - Microissant Wort 2021");
		frame.getContentPane().setLayout(null);
		
		JSlider slider = new JSlider();
		slider.setValue(1);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setMinimum(1);
		slider.setMaximum(5);
		slider.setBounds(10, 61, 214, 26);
		frame.getContentPane().add(slider);
		
		JLabel lbl1 = new JLabel("1");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl1.setBounds(14, 91, 7, 14);
		frame.getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("2");
		lbl2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl2.setBounds(64, 91, 7, 14);
		frame.getContentPane().add(lbl2);
		
		JLabel lbl3 = new JLabel("3");
		lbl3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl3.setBounds(114, 91, 7, 14);
		frame.getContentPane().add(lbl3);
		
		JLabel lbl4 = new JLabel("4");
		lbl4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl4.setBounds(164, 91, 7, 14);
		frame.getContentPane().add(lbl4);
		
		JLabel lbl5 = new JLabel("5");
		lbl5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl5.setBounds(214, 91, 7, 14);
		frame.getContentPane().add(lbl5);
		
		JTextArea tituloSatisfaccion = new JTextArea();
		tituloSatisfaccion.setEditable(false);
		tituloSatisfaccion.setBackground(SystemColor.menu);
		tituloSatisfaccion.setFont(new Font("Arial", Font.BOLD, 13));
		tituloSatisfaccion.setText("Indique su satisfacción:");
		tituloSatisfaccion.setBounds(42, 28, 182, 22);
		frame.getContentPane().add(tituloSatisfaccion);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(73, 127, 89, 23);
		frame.getContentPane().add(btnAceptar);
	}
	
	public void visible() {
		frame.setVisible(true);
	}
}
