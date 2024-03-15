package p6_IPC;

import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.JTextPane;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Editor implements ActionListener {
	
	private ArrayList<Tarea> listaTareas;

	private JMenuItem mntmNuevo;
	private JMenuItem mntmAbrir;
	private JMenuItem mntmGuardar;
	private JMenuItem mntmCerrar;
	private JMenuItem mntmCortar;
	private JMenuItem mntmCopiar;
	private JMenuItem mntmPegar;
	private JMenuItem mntmNegrita;
	private JMenuItem mntmCursiva;
	private JMenuItem mntmSub;
	private JMenuItem mntmCent;
	private JMenuItem mntmLeft;
	private JMenuItem mntmRight;
	private String clipboard;
	private String busqueda;

	private JFrame frmMicroissantW;

	private StyledDocument doc;
	private JTextPane textArea;

	private static final String nombreFichero = "receta";
	private static final String extension = ".txt";
	private File fich;
	private int numFich = 1;
	private JButton btnNegrita;
	private JButton btnCursiva;
	private JButton btnSubrayar;
	private int finSeleccion;
	private int inicioSeleccion;
	private int longitudSeleccion;
	private JButton btnIzquierda;
	private JButton btnCentrar;
	private JButton btnDerecha;
	private JButton btnNuevo;
	private JButton btnAbrir;
	private JButton btnGuardar;
	private JButton btnGuardarComo;
	private JButton btnCopiar;
	private JButton btnCortar;
	private JButton btnPegar;
	private JButton btnJustificado;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JButton btnColor;
	private JColorChooser ventanaDeColores;

	private static Locale localizacion = null;

	private JMenu mnEstilo;
	private JMenuItem mntmGuardarComo;
	private static String idioma = "";
	private JMenuItem mntmJustificar;
	private JSeparator separator;
	private JLabel lblEstilosMenu;
	private JLabel lblAlineacionMenu;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTamanho;
	@SuppressWarnings("rawtypes")
	private JComboBox cbFuente;
	private JTextField textFieldBuscar;
	private JPanel panel_5;
	private JLabel lblBusqueda;
	private JTextField textFieldReemplazar;

	private ResourceBundle mensajes = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor window = new Editor(idioma);
					window.frmMicroissantW.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void visible() {
		frmMicroissantW.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Editor(String idioma) {
		this.listaTareas = new ArrayList<Tarea>();
		
		// TODO: mostrar banner al usuario para que meta datos
		
//		listaTareas.add(new Tarea())
		
		Editor.idioma = idioma;
		initialize();
		mntmNuevo.addActionListener(this);
		mntmAbrir.addActionListener(this);
		mntmGuardar.addActionListener(this);
		mntmCerrar.addActionListener(this);
		mntmCortar.addActionListener(this);
		mntmCopiar.addActionListener(this);
		mntmPegar.addActionListener(this);
		mntmNegrita.addActionListener(this);
		mntmCursiva.addActionListener(this);
		mntmSub.addActionListener(this);
		mntmCent.addActionListener(this);
		mntmLeft.addActionListener(this);
		mntmRight.addActionListener(this);
		fich = new File(nombreFichero+"_"+numFich+extension);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	private void initialize() {

		switch(idioma) {
		case "en":
			localizacion = new Locale(idioma,"EN");
			break;
		case "es":
			localizacion = new Locale(idioma,"ES");
			break;
		}
		mensajes = ResourceBundle.getBundle("p6_IPC.mensajes",localizacion);

		frmMicroissantW = new JFrame();
		frmMicroissantW.setTitle("Microissant Wort 2021 - Unlicensed");
		frmMicroissantW.setBounds(100, 100, 777, 450);
		frmMicroissantW.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		frmMicroissantW.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		/***********************************************************************
		 * 							BARRA DE MENU 2							   *
		 **********************************************************************/
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar);

		/*--------------------------------------------------------------------*
		 * 							NUEVO FICHERO							  *
		 *--------------------------------------------------------------------*/
		btnNuevo = new JButton("");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editor nuevo = new Editor(idioma);
				nuevo.hazVisible();
			}
		});
		btnNuevo.setToolTipText(mensajes.getString("tt_nuevo"));
		try {
			btnNuevo.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/nuevo.png"))));
		} catch (IOException e1) { }
		toolBar.add(btnNuevo);

		/*--------------------------------------------------------------------*
		 * 							ABRIR FICHERO							  *
		 *--------------------------------------------------------------------*/
		btnAbrir = new JButton("");
		btnAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Editor nuevo = new Editor(idioma);
				abrir(nuevo);
				nuevo.hazVisible();
			}
		});
		btnAbrir.setToolTipText(mensajes.getString("tt_abrir"));
		try {
			btnAbrir.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/abrir.png"))));
		} catch (IOException e1) { }
		toolBar.add(btnAbrir);

		/*--------------------------------------------------------------------*
		 * 							GUARDAR FICHERO							  *
		 *--------------------------------------------------------------------*/
		btnGuardar = new JButton("");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnGuardar.setToolTipText(mensajes.getString("tt_guardar"));
		try {
			btnGuardar.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/guardar.png"))));
		} catch (IOException e1) { }
		toolBar.add(btnGuardar);

		/*--------------------------------------------------------------------*
		 * 						GUARDAR FICHERO COMO...					 	  *
		 *--------------------------------------------------------------------*/
		btnGuardarComo = new JButton("");
		btnGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Guardar como...");   
				if (fc.showSaveDialog(frmMicroissantW) == JFileChooser.APPROVE_OPTION) {
					PrintWriter fout;
					try {
						fich = new File(fc.getCurrentDirectory(), fc.getName(fc.getSelectedFile()));
						fout = new PrintWriter(fich);
						fout.print(textArea.getText());
						fout.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnGuardarComo.setToolTipText(mensajes.getString("tt_guardar_como"));
		try {
			btnGuardarComo.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/guardar_como.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		toolBar.add(btnGuardarComo);

		/*--------------------------------------------------------------------*
		 * 								COPIAR							 	  *
		 *--------------------------------------------------------------------*/
		btnCopiar = new JButton("");
		btnCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clipboard = textArea.getSelectedText();
			}
		});
		btnCopiar.setToolTipText(mensajes.getString("tt_copiar"));
		try {
			btnCopiar.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/copiar.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		panel_2 = new JPanel();
		toolBar.add(panel_2);
		toolBar.add(btnCopiar);

		/*--------------------------------------------------------------------*
		 * 								CORTAR							 	  *
		 *--------------------------------------------------------------------*/
		btnCortar = new JButton("");
		btnCortar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clipboard = textArea.getSelectedText();
				textArea.replaceSelection("");
			}
		});
		btnCortar.setToolTipText(mensajes.getString("tt_cortar"));
		try {
			btnCortar.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/cortar.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		toolBar.add(btnCortar);

		/*--------------------------------------------------------------------*
		 * 								PEGAR							 	  *
		 *--------------------------------------------------------------------*/
		btnPegar = new JButton("");
		btnPegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.replaceSelection(clipboard);
			}
		});
		btnPegar.setToolTipText(mensajes.getString("tt_pegar"));
		try {
			btnPegar.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/pegar.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		toolBar.add(btnPegar);

		/*--------------------------------------------------------------------*
		 * 								NEGRITA							 	  *
		 *--------------------------------------------------------------------*/
		btnNegrita = new JButton("");
		btnNegrita.setToolTipText(mensajes.getString("tt_negrita"));
		try {
			btnNegrita.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/negrita.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		panel_3 = new JPanel();
		toolBar.add(panel_3);
		toolBar.add(btnNegrita);
		btnNegrita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: registrar interaccion
				
				doc = textArea.getStyledDocument();
				SimpleAttributeSet neg = new SimpleAttributeSet();
				if(! StyleConstants.isBold(textArea.getCharacterAttributes())) {
					doc = textArea.getStyledDocument();
					//Determinar el texto que es ha seleccionado para darle formato
					inicioSeleccion = textArea.getSelectionStart();
					finSeleccion = textArea.getSelectionEnd();
					longitudSeleccion = finSeleccion - inicioSeleccion;

					//Atributo Negrita
					neg = new SimpleAttributeSet();
					StyleConstants.setBold(neg, true);

					//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
					doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, neg, false);
				}
				else {
					//Determinar el texto que es ha seleccionado para darle formato
					int inicioSeleccion = textArea.getSelectionStart();
					int finSeleccion = textArea.getSelectionEnd();
					int longitudSeleccion = finSeleccion - inicioSeleccion;

					//Atributo Negrita
					neg = new SimpleAttributeSet();
					StyleConstants.setBold(neg, false);

					//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
					doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, neg, false);
				}
			}
		});
		btnNegrita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		/*--------------------------------------------------------------------*
		 * 								CURSIVA							 	  *
		 *--------------------------------------------------------------------*/
		btnCursiva = new JButton("");
		btnCursiva.setToolTipText(mensajes.getString("tt_cursiva"));
		try {
			btnCursiva.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/cursiva.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		toolBar.add(btnCursiva);
		btnCursiva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doc = textArea.getStyledDocument();
				SimpleAttributeSet cur = new SimpleAttributeSet();
				if(! StyleConstants.isItalic(textArea.getCharacterAttributes())) {
					doc = textArea.getStyledDocument();
					//Determinar el texto que es ha seleccionado para darle formato
					inicioSeleccion = textArea.getSelectionStart();
					finSeleccion = textArea.getSelectionEnd();
					longitudSeleccion = finSeleccion - inicioSeleccion;

					//Atributo Negrita
					cur = new SimpleAttributeSet();
					StyleConstants.setItalic(cur, true);

					//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
					doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, cur, false);
				} else {
					//Determinar el texto que es ha seleccionado para darle formato
					inicioSeleccion = textArea.getSelectionStart();
					finSeleccion = textArea.getSelectionEnd();
					longitudSeleccion = finSeleccion - inicioSeleccion;

					//Atributo Negrita
					cur = new SimpleAttributeSet();
					StyleConstants.setItalic(cur, false);
					//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
					doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, cur, false);
				}

			}
		});
		btnCursiva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		/*--------------------------------------------------------------------*
		 * 								SUBRAYADO						 	  *
		 *--------------------------------------------------------------------*/
		btnSubrayar = new JButton("");
		btnSubrayar.setToolTipText(mensajes.getString("tt_subrayado"));
		try {
			btnSubrayar.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/subrayado.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnSubrayar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doc = textArea.getStyledDocument();
				SimpleAttributeSet sub = new SimpleAttributeSet();
				if(! StyleConstants.isUnderline(textArea.getCharacterAttributes())) {
					doc = textArea.getStyledDocument();
					//Determinar el texto que es ha seleccionado para darle formato
					inicioSeleccion = textArea.getSelectionStart();
					finSeleccion = textArea.getSelectionEnd();
					longitudSeleccion = finSeleccion - inicioSeleccion;

					//Atributo Negrita
					sub = new SimpleAttributeSet();
					StyleConstants.setUnderline(sub, true);

					//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
					doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, sub, false);
				} else {
					//Determinar el texto que es ha seleccionado para darle formato
					inicioSeleccion = textArea.getSelectionStart();
					finSeleccion = textArea.getSelectionEnd();
					longitudSeleccion = finSeleccion - inicioSeleccion;

					//Atributo Negrita
					sub = new SimpleAttributeSet();
					StyleConstants.setUnderline(sub, false);
					//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
					doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, sub, false);
				}
			}
		});
		toolBar.add(btnSubrayar);

		/*--------------------------------------------------------------------*
		 * 								AL. IZDA.						 	  *
		 *--------------------------------------------------------------------*/
		btnIzquierda = new JButton("");
		btnIzquierda.setToolTipText(mensajes.getString("tt_al_izda"));
		try {
			btnIzquierda.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/al_izda.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnIzquierda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Documento asociado al editor
				doc = textArea.getStyledDocument();
				//Determinar el texto que es ha seleccionado para darle formato
				int inicioSeleccion = textArea.getSelectionStart();
				int finSeleccion = textArea.getSelectionEnd();
				int longitudSeleccion = finSeleccion - inicioSeleccion;

				//Atributo Centrar
				SimpleAttributeSet cent = new SimpleAttributeSet();
				StyleConstants.setAlignment(cent, StyleConstants.ALIGN_LEFT);

				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setParagraphAttributes(inicioSeleccion, longitudSeleccion, cent, true);
			}
		});
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		/*--------------------------------------------------------------------*
		 * 								COLOR							 	  *
		 *--------------------------------------------------------------------*/
		btnColor = new JButton("");
		btnColor.setToolTipText(mensajes.getString("tt_color"));
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventanaDeColores=new JColorChooser();
				Color color=JColorChooser.showDialog(ventanaDeColores, "Elige un color", Color.BLACK);
				doc = textArea.getStyledDocument();
				//Determinar el texto que es ha seleccionado para darle formato
				int inicioSeleccion = textArea.getSelectionStart();
				int finSeleccion = textArea.getSelectionEnd();
				int longitudSeleccion = finSeleccion - inicioSeleccion;
				SimpleAttributeSet clr = new SimpleAttributeSet();
				StyleConstants.setForeground(clr, color);			
				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, clr, false);
				//ventanaDeColores.getSelectionModel().addChangeListener(new ColorSelection());
			}
		});
		try {
			btnColor.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/color.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		toolBar.add(btnColor);

		cbFuente = new JComboBox();
		cbFuente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doc = textArea.getStyledDocument();
				//Determinar el texto que es ha seleccionado para darle formato
				int inicioSeleccion = textArea.getSelectionStart();
				int finSeleccion = textArea.getSelectionEnd();
				int longitudSeleccion = finSeleccion - inicioSeleccion;

				SimpleAttributeSet fnt = new SimpleAttributeSet();
				StyleConstants.setFontFamily(fnt, (String)cbFuente.getSelectedItem());

				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, fnt, false);
			}
		});
		cbFuente.setModel(new DefaultComboBoxModel(new String[] {"Serif", "Sans serif", "Monospaced", "Dialog"}));
		toolBar.add(cbFuente);

		cbTamanho = new JComboBox();
		cbTamanho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doc = textArea.getStyledDocument();
				//Determinar el texto que es ha seleccionado para darle formato
				int	inicioSeleccion = textArea.getSelectionStart();
				int	finSeleccion = textArea.getSelectionEnd();
				int	longitudSeleccion = finSeleccion - inicioSeleccion;

				//Atributo Negrita
				SimpleAttributeSet tam = new SimpleAttributeSet();
				StyleConstants.setFontSize(tam, Integer.parseInt(cbTamanho.getSelectedItem().toString()));

				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, tam, false);
			}
		});
		cbTamanho.setModel(new DefaultComboBoxModel(new String[] {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "72"}));
		toolBar.add(cbTamanho);

		panel_4 = new JPanel();
		toolBar.add(panel_4);
		toolBar.add(btnIzquierda);

		/*--------------------------------------------------------------------*
		 * 								CENTRAR							 	  *
		 *--------------------------------------------------------------------*/
		btnCentrar = new JButton("");
		btnCentrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCentrar.setToolTipText(mensajes.getString("tt_al_centro"));
		try {
			btnCentrar.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/al_centro.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnCentrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Documento asociado al editor
				doc = textArea.getStyledDocument();
				//Determinar el texto que es ha seleccionado para darle formato
				int inicioSeleccion = textArea.getSelectionStart();
				int finSeleccion = textArea.getSelectionEnd();
				int longitudSeleccion = finSeleccion - inicioSeleccion;

				//Atributo Centrar
				SimpleAttributeSet cent = new SimpleAttributeSet();
				StyleConstants.setAlignment(cent, StyleConstants.ALIGN_CENTER);

				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setParagraphAttributes(inicioSeleccion, longitudSeleccion, cent, true);
			}
		});
		toolBar.add(btnCentrar);

		/*--------------------------------------------------------------------*
		 * 								AL. DCHA.						 	  *
		 *--------------------------------------------------------------------*/
		btnDerecha = new JButton("");
		btnDerecha.setToolTipText(mensajes.getString("tt_al_dcha"));
		try {
			btnDerecha.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/al_dcha.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnDerecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Documento asociado al editor
				doc = textArea.getStyledDocument();
				//Determinar el texto que es ha seleccionado para darle formato
				int inicioSeleccion = textArea.getSelectionStart();
				int finSeleccion = textArea.getSelectionEnd();
				int longitudSeleccion = finSeleccion - inicioSeleccion;

				//Atributo Centrar
				SimpleAttributeSet cent = new SimpleAttributeSet();
				StyleConstants.setAlignment(cent, StyleConstants.ALIGN_RIGHT);

				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setParagraphAttributes(inicioSeleccion, longitudSeleccion, cent, true);
			}
		});
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(btnDerecha);

		/*--------------------------------------------------------------------*
		 * 								JUSTIFICAR						 	  *
		 *--------------------------------------------------------------------*/
		btnJustificado = new JButton("");
		btnJustificado.setToolTipText(mensajes.getString("tt_al_just"));
		try {
			btnJustificado.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/al_just.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnJustificado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Documento asociado al editor
				doc = textArea.getStyledDocument();
				//Determinar el texto que es ha seleccionado para darle formato
				int inicioSeleccion = textArea.getSelectionStart();
				int finSeleccion = textArea.getSelectionEnd();
				int longitudSeleccion = finSeleccion - inicioSeleccion;

				//Atributo Centrar
				SimpleAttributeSet just = new SimpleAttributeSet();
				StyleConstants.setAlignment(just, StyleConstants.ALIGN_JUSTIFIED);

				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setParagraphAttributes(inicioSeleccion, longitudSeleccion, just, true);
			}
		});
		toolBar.add(btnJustificado);

		JPanel panel_1 = new JPanel();
		frmMicroissantW.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));


		textArea = new JTextPane();
		panel_1.add(textArea, BorderLayout.CENTER);

		/***********************************************************************
		 * 							BARRA DE MENU 1							   *
		 **********************************************************************/
		JMenuBar menuBar = new JMenuBar();
		frmMicroissantW.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu(mensajes.getString("mt_archivo"));
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);

		mntmNuevo = new JMenuItem(mensajes.getString("mi_nuevo"));
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		try {
			mntmNuevo.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/nuevo - peque.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mnArchivo.add(mntmNuevo);

		mntmAbrir = new JMenuItem(mensajes.getString("mi_abrir"));
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		try {
			mntmAbrir.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/abrir - peque.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mnArchivo.add(mntmAbrir);

		mntmGuardar = new JMenuItem(mensajes.getString("mi_guardar"));
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		try {
			mntmGuardar.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/guardar - peque.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mnArchivo.add(mntmGuardar);

		mntmGuardarComo = new JMenuItem(mensajes.getString("mi_guardar_como"));
		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
		try {
			mntmGuardarComo.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/guardar_como - peque.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mnArchivo.add(mntmGuardarComo);

		mntmCerrar = new JMenuItem(mensajes.getString("mi_cerrar"));
		mntmCerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnArchivo.add(mntmCerrar);

		JMenu mnEdicion = new JMenu(mensajes.getString("mt_edicion"));
		mnEdicion.setMnemonic('E');
		menuBar.add(mnEdicion);

		mntmCortar = new JMenuItem(mensajes.getString("mi_cortar"));
		mntmCortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		try {
			mntmCortar.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/cortar - peque.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mnEdicion.add(mntmCortar);

		mntmCopiar = new JMenuItem(mensajes.getString("mi_copiar"));
		mntmCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		try {
			mntmCopiar.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/copiar - peque.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mnEdicion.add(mntmCopiar);

		mntmPegar = new JMenuItem(mensajes.getString("mi_pegar"));
		mntmPegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		try {
			mntmPegar.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/pegar - peque.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mnEdicion.add(mntmPegar);

		mnEstilo = new JMenu(mensajes.getString("mt_estilo"));
		menuBar.add(mnEstilo);

		lblEstilosMenu = new JLabel(mensajes.getString("mit_estilo"));
		lblEstilosMenu.setEnabled(false);
		mnEstilo.add(lblEstilosMenu);

		mntmNegrita = new JMenuItem(mensajes.getString("mi_negrita"));
		mntmNegrita.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mnEstilo.add(mntmNegrita);

		mntmCursiva = new JMenuItem(mensajes.getString("mi_cursiva"));
		mntmCursiva.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnEstilo.add(mntmCursiva);

		mntmSub = new JMenuItem(mensajes.getString("mi_subrayado"));
		mntmSub.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
		mnEstilo.add(mntmSub);

		separator = new JSeparator();
		mnEstilo.add(separator);

		lblAlineacionMenu = new JLabel(mensajes.getString("mit_alineacion"));
		lblAlineacionMenu.setEnabled(false);
		mnEstilo.add(lblAlineacionMenu);

		mntmLeft = new JMenuItem(mensajes.getString("mi_al_izda"));
		mntmLeft.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mnEstilo.add(mntmLeft);

		mntmCent = new JMenuItem(mensajes.getString("mi_al_centro"));
		mntmCent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		mnEstilo.add(mntmCent);

		mntmRight = new JMenuItem(mensajes.getString("mi_al_dcha"));
		mntmRight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnEstilo.add(mntmRight);

		mntmJustificar = new JMenuItem(mensajes.getString("mi_al_just"));
		mntmJustificar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_DOWN_MASK));
		mnEstilo.add(mntmJustificar);

		panel_5 = new JPanel();
		menuBar.add(panel_5);

		lblBusqueda = new JLabel(mensajes.getString("mt_busqueda"));
		menuBar.add(lblBusqueda);

		textFieldBuscar = new JTextField();
		textFieldBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//busqueda
				busqueda = textFieldBuscar.getText().toLowerCase(); 

				//tecla enter
				int tecla = e.getKeyCode();
				if (tecla == KeyEvent.VK_ENTER) {	
					Highlighter highlighter = textArea.getHighlighter();
					HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow); 
					int pInicio = textArea.getText().toLowerCase().indexOf(busqueda);
					int pFin = pInicio + busqueda.length();

					try {
						highlighter.removeAllHighlights();
						highlighter.addHighlight(pInicio, pFin, painter);
					} catch (BadLocationException e1) {
						VentanaError vent = new VentanaError(); 
						vent.hacerVisible();
					}
				}
			}
		});
		menuBar.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);

		textFieldReemplazar = new JTextField();
		textFieldReemplazar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//tecla enter
				int tecla = arg0.getKeyCode();
				if (tecla == KeyEvent.VK_ENTER) {	
					String todoTexto = textArea.getText(); 
					String textoReemplazo = textFieldReemplazar.getText();
					todoTexto = todoTexto.replaceAll(busqueda, textoReemplazo);
					textArea.setText(todoTexto);
				}
			}
		});
		menuBar.add(textFieldReemplazar);
		textFieldReemplazar.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String s = e.getActionCommand();
		Editor nuevo;

		if (s.equals("Centrar") || s.equals("Center")) {
			//Documento asociado al editor
			doc = textArea.getStyledDocument();
			//Determinar el texto que es ha seleccionado para darle formato
			int inicioSeleccion = textArea.getSelectionStart();
			int finSeleccion = textArea.getSelectionEnd();
			int longitudSeleccion = finSeleccion - inicioSeleccion;

			//Atributo Centrar
			SimpleAttributeSet cent = new SimpleAttributeSet();
			StyleConstants.setAlignment(cent, StyleConstants.ALIGN_CENTER);

			//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
			doc.setParagraphAttributes(inicioSeleccion, longitudSeleccion, cent, true);
		} else if (s.equals("Alineacion izda.") || s.equals("Left align")) {
			//Documento asociado al editor
			doc = textArea.getStyledDocument();
			//Determinar el texto que es ha seleccionado para darle formato
			inicioSeleccion = textArea.getSelectionStart();
			finSeleccion = textArea.getSelectionEnd();
			longitudSeleccion = finSeleccion - inicioSeleccion;

			//Atributo Izquierda
			SimpleAttributeSet left = new SimpleAttributeSet();
			StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);

			//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
			doc.setParagraphAttributes(inicioSeleccion, longitudSeleccion, left, true);
		} else if (s.equals("Alineacion dcha.") || s.equals("Right align")) {
			//Documento asociado al editor
			doc = textArea.getStyledDocument();
			//Determinar el texto que es ha seleccionado para darle formato
			inicioSeleccion = textArea.getSelectionStart();
			finSeleccion = textArea.getSelectionEnd();
			longitudSeleccion = finSeleccion - inicioSeleccion;

			//Atributo Derecha
			SimpleAttributeSet right = new SimpleAttributeSet();
			StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);

			//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
			doc.setParagraphAttributes(inicioSeleccion, longitudSeleccion, right, true);
		} else if (s.equals("Justificar") || s.equals("Justify")) {
			//Documento asociado al editor
			doc = textArea.getStyledDocument();
			//Determinar el texto que es ha seleccionado para darle formato
			int inicioSeleccion = textArea.getSelectionStart();
			int finSeleccion = textArea.getSelectionEnd();
			int longitudSeleccion = finSeleccion - inicioSeleccion;

			//Atributo Centrar
			SimpleAttributeSet just = new SimpleAttributeSet();
			StyleConstants.setAlignment(just, StyleConstants.ALIGN_JUSTIFIED);

			//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
			doc.setParagraphAttributes(inicioSeleccion, longitudSeleccion, just, true);
		} else if (s.equals("Negrita") || s.equals("Bold")) {
			doc = textArea.getStyledDocument();
			SimpleAttributeSet neg = new SimpleAttributeSet();
			if(! StyleConstants.isBold(textArea.getCharacterAttributes())) {
				doc = textArea.getStyledDocument();
				//Determinar el texto que es ha seleccionado para darle formato
				inicioSeleccion = textArea.getSelectionStart();
				finSeleccion = textArea.getSelectionEnd();
				longitudSeleccion = finSeleccion - inicioSeleccion;

				//Atributo Negrita
				neg = new SimpleAttributeSet();
				StyleConstants.setBold(neg, true);

				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, neg, false);
			} else {
				//Determinar el texto que es ha seleccionado para darle formato
				inicioSeleccion = textArea.getSelectionStart();
				finSeleccion = textArea.getSelectionEnd();
				longitudSeleccion = finSeleccion - inicioSeleccion;

				//Atributo Negrita
				neg = new SimpleAttributeSet();
				StyleConstants.setBold(neg, false);
				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, neg, false);
			}
		} else if (s.equals("Cursiva") || s.equals("Italic")) {
			doc = textArea.getStyledDocument();
			SimpleAttributeSet cur = new SimpleAttributeSet();
			if(! StyleConstants.isItalic(textArea.getCharacterAttributes())) {
				doc = textArea.getStyledDocument();
				//Determinar el texto que es ha seleccionado para darle formato
				inicioSeleccion = textArea.getSelectionStart();
				finSeleccion = textArea.getSelectionEnd();
				longitudSeleccion = finSeleccion - inicioSeleccion;

				//Atributo Negrita
				cur = new SimpleAttributeSet();
				StyleConstants.setItalic(cur, true);

				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, cur, false);
			} else {
				//Determinar el texto que es ha seleccionado para darle formato
				inicioSeleccion = textArea.getSelectionStart();
				finSeleccion = textArea.getSelectionEnd();
				longitudSeleccion = finSeleccion - inicioSeleccion;

				//Atributo Negrita
				cur = new SimpleAttributeSet();
				StyleConstants.setItalic(cur, false);
				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, cur, false);
			}
		} else if (s.equals("Subrayado") || s.equals("Underlined")) {
			doc = textArea.getStyledDocument();
			SimpleAttributeSet sub = new SimpleAttributeSet();
			if(! StyleConstants.isUnderline(textArea.getCharacterAttributes())) {
				doc = textArea.getStyledDocument();
				//Determinar el texto que es ha seleccionado para darle formato
				inicioSeleccion = textArea.getSelectionStart();
				finSeleccion = textArea.getSelectionEnd();
				longitudSeleccion = finSeleccion - inicioSeleccion;

				//Atributo Negrita
				sub = new SimpleAttributeSet();
				StyleConstants.setUnderline(sub, true);

				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, sub, false);
			} else {
				//Determinar el texto que es ha seleccionado para darle formato
				inicioSeleccion = textArea.getSelectionStart();
				finSeleccion = textArea.getSelectionEnd();
				longitudSeleccion = finSeleccion - inicioSeleccion;

				//Atributo Negrita
				sub = new SimpleAttributeSet();
				StyleConstants.setUnderline(sub, false);
				//Asociar el conjunto de atributos al fragmento de texto seleccionado en el documento
				doc.setCharacterAttributes(inicioSeleccion, longitudSeleccion, sub, false);
			}
		} else if (s.equals("Copiar") || s.equals("Copy")) {
			clipboard = textArea.getSelectedText();
		} else if (s.equals("Cortar") || s.equals("Cut")) {
			clipboard = textArea.getSelectedText();
			textArea.replaceSelection("");
		} else if (s.equals("Pegar") || s.equals("Paste")) {
			textArea.replaceSelection(clipboard);
		} else if (s.equals("Nuevo") || s.equals("New")) {
			nuevo = new Editor(idioma);
			nuevo.hazVisible();
		} else if (s.equals("Abrir") || s.equals("Open")) {
			nuevo = new Editor(idioma);
			abrir(nuevo);
			nuevo.hazVisible();
		} else if (s.equals("Guardar") || s.equals("Save")) {
			guardar();
		} else if (s.equals("Guardar como...") || s.equals("Save as...")) {
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Guardar como...");   
			if (fc.showSaveDialog(frmMicroissantW) == JFileChooser.APPROVE_OPTION) {
				PrintWriter fout;
				try {
					fich = new File(fc.getCurrentDirectory(), fc.getName(fc.getSelectedFile()));
					fout = new PrintWriter(fich);
					fout.print(textArea.getText());
					fout.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		} else if (s.equals("Cerrar") || s.equals("Close")) {
			frmMicroissantW.dispose();
		}

	}

	private void guardar() {
		try {
			PrintWriter fout = new PrintWriter(fich);
			fout.print(textArea.getText());
			fout.close();
		} catch (FileNotFoundException ex) { }
	}

	private void abrir(Editor e) {
		JFileChooser fcAbrir = new JFileChooser();
		File file = null;
		int valorDevuelto = fcAbrir.showOpenDialog (null);

		if (valorDevuelto == JFileChooser. APPROVE_OPTION ) {
			file = fcAbrir. getSelectedFile();
		}
		e.setTextArea(file);
	}

	public void setTextArea(File fich) { 
		try {
			textArea.setText(Files.readString(fich.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) { }
	}

	public void hazVisible() {
		frmMicroissantW.setVisible(true);
	}

}

