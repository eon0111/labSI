package p6_IPC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;

public class RecogidaDatosUsuario {

	private JFrame frame;
	private JTextField txtTareaTodo;
	private JTextField tfNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecogidaDatosUsuario window = new RecogidaDatosUsuario();
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
	public RecogidaDatosUsuario() {
		initialize();
	}
	
	/**
	 * Hace la ventana visible
	 */
	public void visible() {
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea tituloTarea = new JTextArea();
		tituloTarea.setBackground(new Color(240, 240, 240));
		tituloTarea.setText("Descripción de las tareas");
		tituloTarea.setFont(new Font("Tahoma", Font.BOLD, 18));
		tituloTarea.setEditable(false);
		tituloTarea.setBounds(103, 11, 230, 26);
		frame.getContentPane().add(tituloTarea);
		
		txtTareaTodo = new JTextField();
		txtTareaTodo.setText("TODO");
		txtTareaTodo.setEditable(false);
		txtTareaTodo.setBounds(10, 48, 414, 141);
		frame.getContentPane().add(txtTareaTodo);
		txtTareaTodo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(66, 220, 47, 14);
		frame.getContentPane().add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(123, 217, 155, 20);
		frame.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(292, 216, 89, 23);
		frame.getContentPane().add(btnContinuar);
		btnContinuar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Editor editor = new Editor("es", tfNombre.getText());
				editor.visible();
			}
		});
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
