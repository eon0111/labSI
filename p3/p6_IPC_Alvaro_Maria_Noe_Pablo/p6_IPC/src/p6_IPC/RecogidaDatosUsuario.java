package p6_IPC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecogidaDatosUsuario {

	private JFrame frame;
	private JTextField inputNombreUsuario;

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
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel tituloNombreUsuario = new JLabel("Nombre:");
		frame.getContentPane().add(tituloNombreUsuario, "4, 4, right, default");
		
		inputNombreUsuario = new JTextField();
		frame.getContentPane().add(inputNombreUsuario, "6, 4, left, default");
		inputNombreUsuario.setColumns(10);
		
		JLabel tituloDescripcionTarea = new JLabel("Descripción de la tarea:");
		tituloDescripcionTarea.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(tituloDescripcionTarea, "4, 8");
		
		JLabel textBoxEnunciado = new JLabel("");
		frame.getContentPane().add(textBoxEnunciado, "6, 8, left, top");
		
		JButton botonContinuar = new JButton("Aceptar");
		botonContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(botonContinuar, "6, 10, left, default");
	}

}
