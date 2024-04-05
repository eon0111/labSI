package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class VentanaError {

	private JFrame frameError1;
	/**
	 * Create the application.
	 */
	public VentanaError() {
		initialize();
	}

	public void hacerVisible() {
		frameError1.setVisible(true);
	}
	
	private void cerrar() {
		frameError1.dispose();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameError1 = new JFrame();
		frameError1.setBounds(100, 100, 450, 250);
		frameError1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JToolBar toolBarError = new JToolBar();
		toolBarError.setBackground(Color.BLUE);
		frameError1.getContentPane().add(toolBarError, BorderLayout.NORTH);
		
		JLabel lblMensajeError = new JLabel("Mensaje de error");
		lblMensajeError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblMensajeError.setForeground(Color.WHITE);
		toolBarError.add(lblMensajeError);
		
		JPanel panelError1 = new JPanel();
		frameError1.getContentPane().add(panelError1, BorderLayout.CENTER);
		
		JPanel panelError = new JPanel();
		JLabel lblFotoError = new JLabel("");
		panelError.add(lblFotoError );
		try {
			lblFotoError.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/imagenError.png"))));
		} catch (IOException e1) { }
		
		JLabel lblError = new JLabel("ERROR!");
		lblError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblMensajeError1 = new JLabel("Busqueda no encontrada");
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cerrar();
			}
		});
		
		GroupLayout gl_panelError1 = new GroupLayout(panelError1);
		gl_panelError1.setHorizontalGroup(
			gl_panelError1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelError1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelError, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panelError1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelError1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panelError1.createSequentialGroup()
								.addGap(18)
								.addComponent(lblError)
								.addContainerGap(209, Short.MAX_VALUE))
							.addGroup(gl_panelError1.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblMensajeError1, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
						.addGroup(gl_panelError1.createSequentialGroup()
							.addGap(76)
							.addComponent(btnAceptar)
							.addContainerGap())))
		);
		gl_panelError1.setVerticalGroup(
			gl_panelError1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelError1.createSequentialGroup()
					.addGroup(gl_panelError1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelError1.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panelError1.createSequentialGroup()
							.addGap(33)
							.addComponent(lblError)
							.addGap(26)
							.addComponent(lblMensajeError1)
							.addGap(18)
							.addComponent(btnAceptar)))
					.addContainerGap())
		);
		panelError1.setLayout(gl_panelError1);
	}
}
