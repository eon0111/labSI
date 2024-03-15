package p6_IPC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeleccionIdioma {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionIdioma window = new SeleccionIdioma();
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
	public SeleccionIdioma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 350, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		JButton btnEspanhol = new JButton("");
		btnEspanhol.setToolTipText("Espa\u00F1ol");
		btnEspanhol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RecogidaDatosUsuario ventanaDatosUsuario = new RecogidaDatosUsuario();
				ventanaDatosUsuario.visible();
				
				Editor editor = new Editor("es");
				editor.visible();
			}
		});
		try {
			btnEspanhol.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/espanhol.png"))));
		} catch (IOException e1) { }
		btnEspanhol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JLabel lblTituloIdioma = new JLabel("Idioma / Language");
		lblTituloIdioma.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblTituloIdioma = new GridBagConstraints();
		gbc_lblTituloIdioma.gridwidth = 6;
		gbc_lblTituloIdioma.insets = new Insets(0, 0, 5, 5);
		gbc_lblTituloIdioma.gridx = 2;
		gbc_lblTituloIdioma.gridy = 0;
		frame.getContentPane().add(lblTituloIdioma, gbc_lblTituloIdioma);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagConstraints gbc_btnEspanhol = new GridBagConstraints();
		gbc_btnEspanhol.insets = new Insets(0, 0, 0, 5);
		gbc_btnEspanhol.gridx = 2;
		gbc_btnEspanhol.gridy = 4;
		frame.getContentPane().add(btnEspanhol, gbc_btnEspanhol);

		JButton btnIngles = new JButton("");
		btnIngles.setToolTipText("English");
		btnIngles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Editor editor = new Editor("en");
				editor.visible();
			}
		});
		try {
			btnIngles.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/ingles.png"))));
		} catch (IOException e1) { }
		btnIngles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 4;
		gbc_panel_1.gridy = 4;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		GridBagConstraints gbc_btnIngles = new GridBagConstraints();
		gbc_btnIngles.insets = new Insets(0, 0, 0, 5);
		gbc_btnIngles.gridx = 7;
		gbc_btnIngles.gridy = 4;
		frame.getContentPane().add(btnIngles, gbc_btnIngles);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 8;
		gbc_panel_2.gridy = 4;
		frame.getContentPane().add(panel_2, gbc_panel_2);
	}

}
