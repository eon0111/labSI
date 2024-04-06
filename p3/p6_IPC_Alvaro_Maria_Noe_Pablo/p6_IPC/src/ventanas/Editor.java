package ventanas;

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
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Highlighter.HighlightPainter;

import medidas.NumErrores;
import medidas.TasaEfectividad;
import medidas.TiempoTarea;
import medidas.VelocidadTarea;
import misc.IndiceTarea;
import misc.Interaccion;
import misc.Tarea;

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
	
	private IndiceTarea tareaActual;

	private Tarea tarea1;
	private Tarea tarea2;
	
	private String nombreUsuario;
	
	/* Valores tiempo de realizacion */
	private final static int OPTIMO_TIEMPO_REALIZACION = 1;
	private final static int OBJETIVO_TIEMPO_REALIZACION = 1;
	private final static int PEOR_TIEMPO_REALIZACION = 1;
	
	/* Valores numero de errores */
	private final static int OPTIMO_ERRORES = 0;
	private final static int OBJETIVO_ERRORES = 3;
	private final static int PEOR_ERRORES = 50;
	
	/* Valores tasa de efectividad */
	private final static int OPTIMO_TASA_EFECTIVIDAD = 1;
	private final static int OBJETIVO_TASA_EFECTIVIDAD = 1;
	private final static int PEOR_TASA_EFECTIVIDAD = 1;
	
	/* Valores velocidad de realizacion */
	private final static int OPTIMO_VELOCIDAD_REALIZACION = 1;
	private final static int OBJETIVO_VELOCIDAD_REALIZACION = 1;
	private final static int PEOR_VELOCIDAD_REALIZACION = 1;
	
	private static String ENUNCIADO_TAREA_1 =
			  "TAREA 1:\r\n"
			+ "  1. Escriba el siguiente texto: \"Lorem ipsum\"\r\n"
			+ "  2. Póngalo en negrita\r\n"
			+ "  3. Póngalo en cursiva\r\n"
			+ "  4. Póngalo como texto subrayado";
	
	private static String ENUNCIADO_TAREA_2 = 
			  "TAREA 2:\r\n"
			+ "  1. Escriba el siguiente texto: \"Dolor sit amet\"\r\n"
			+ "  2. Centre el texto en la página\r\n"
			+ "  3. Alinee el texto a la derecha\r\n"
			+ "  4. Recupere la alineación inicial";
	
	/* ID's interacciones tarea 1 */
	private static String ID_NEGRITA = "t1i1";
	private static String ID_CURSIVA = "t1i2";
	private static String ID_SUBRAYADO = "t1i3";
	
	/* ID's interacciones tarea 2 */
	private static String ID_CENTRO = "t2i1";
	private static String ID_DCHA = "t2i2";
	private static String ID_IZDA = "t2i3";
	
	/* Medidas */
	/* -- Tiempo de realizacion -- */
	private TiempoTarea tiempoTareaT1;
	private TiempoTarea tiempoTareaT2;
	
	/* -- Numero de errores -- */
	private ArrayList<NumErrores> listaErrores = new ArrayList<NumErrores>();
	private NumErrores nErroresT1;
	private NumErrores nErroresT2;
	
	/* -- Velocidad de realizacion -- */
	private VelocidadTarea velocidadT1;
	private VelocidadTarea velocidadT2;
	
	/* -- Tasa de efectividad -- */
	private TasaEfectividad tasaEfectividadT1;
	private TasaEfectividad tasaEfectividadT2;
	
	/* Eltos. de la interfaz */
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
	private JToolBar toolBar_1;
	private JButton btnTarea1;
	private JButton btnTarea2;
	private JPanel panel_6;
	private JButton btnFin;
	
	public void visible() {
		frmMicroissantW.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Editor(String idioma, String nombreUsuario) {
		
		/* Creacion de las tareas */
		tarea1 = new Tarea("t1", nombreUsuario, ENUNCIADO_TAREA_1);
		tarea1.anhadeObjetivo(new Interaccion(ID_NEGRITA, "Texto en negrita", "", -1));
		tarea1.anhadeObjetivo(new Interaccion(ID_CURSIVA, "Texto en cursiva", "", -1));
		tarea1.anhadeObjetivo(new Interaccion(ID_SUBRAYADO, "Texto subrayado", "", -1));
		
		tarea2 = new Tarea("t2", nombreUsuario, ENUNCIADO_TAREA_2);
		tarea2.anhadeObjetivo(new Interaccion(ID_CENTRO, "Texto centrado", "", -1));
		tarea2.anhadeObjetivo(new Interaccion(ID_DCHA, "Alineacion de texto a la dcha.", "", -1));
		tarea2.anhadeObjetivo(new Interaccion(ID_IZDA, "Alineacion de texto a la izda.", "", -1));
		
		/* Creacion de las medidas */
		/* -- Tiempo de realizacion -- */
		this.tiempoTareaT1 =
				new TiempoTarea("tiempoTarea1",
								"Tiempo de realización de la tarea 1",
								tarea1,
								OPTIMO_TIEMPO_REALIZACION,
								OBJETIVO_TIEMPO_REALIZACION,
								PEOR_TIEMPO_REALIZACION);
		this.tiempoTareaT2 =
				new TiempoTarea("tiempoTarea2",
								"Tiempo de realización de la tarea 2",
								tarea2,
								OPTIMO_TIEMPO_REALIZACION,
								OBJETIVO_TIEMPO_REALIZACION,
								PEOR_TIEMPO_REALIZACION);
		
		/* -- Numero de errores -- */
		this.listaErrores = new ArrayList<NumErrores>();
		this.nErroresT1 = new NumErrores("erroresTarea1",
									  "Numero de errores en la tarea 1",
 									  tarea1,
									  OPTIMO_ERRORES,
									  OBJETIVO_ERRORES,
									  PEOR_ERRORES);
		this.nErroresT2 = new NumErrores("erroresTarea2",
									  "Numero de errores en la tarea 2",
									  tarea2,
									  OPTIMO_ERRORES,
									  OBJETIVO_ERRORES,
									  PEOR_ERRORES);
		
		/* -- Velocidad de realizacion -- */
		this.velocidadT1 =
				new VelocidadTarea("velocidadTarea1",
								   "Velocidad de realización en la tarea 1",
								   tarea1,
								   OPTIMO_VELOCIDAD_REALIZACION,
								   OBJETIVO_VELOCIDAD_REALIZACION,
								   PEOR_VELOCIDAD_REALIZACION);
		this.velocidadT2 =
				new VelocidadTarea("velocidadTarea2",
								   "Velocidad de realización en la tarea 2",
								   tarea2,
								   OPTIMO_VELOCIDAD_REALIZACION,
								   OBJETIVO_VELOCIDAD_REALIZACION,
								   PEOR_VELOCIDAD_REALIZACION);
		
		/* -- Tasa de efectividad -- */
		this.tasaEfectividadT1 =
				new TasaEfectividad("efectividadTarea1",
									"Tasa de efectividad en la tarea 1",
									tarea1,
									OPTIMO_TASA_EFECTIVIDAD,
									OBJETIVO_TASA_EFECTIVIDAD,
									PEOR_TASA_EFECTIVIDAD);
		this.tasaEfectividadT2 =
				new TasaEfectividad("efectividadTarea2",
									"Tasa de efectividad en la tarea 2",
									tarea2,
									OPTIMO_TASA_EFECTIVIDAD,
									OBJETIVO_TASA_EFECTIVIDAD,
									PEOR_TASA_EFECTIVIDAD);
		
		listaErrores.add(nErroresT1);
		listaErrores.add(nErroresT2);
		
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
		mensajes = ResourceBundle.getBundle("misc.mensajes",localizacion);

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
				Editor nuevo = new Editor(idioma, nombreUsuario);
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
				Editor nuevo = new Editor(idioma, nombreUsuario);
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
				procesaInteraccion(IndiceTarea.TAREA_1, tarea1, ID_NEGRITA);
				
				doc = textArea.getStyledDocument();
				SimpleAttributeSet neg = new SimpleAttributeSet();
				if(! StyleConstants.isBold(textArea.getCharacterAttributes())) {
					doc = textArea.getStyledDocument();
					//Determinar el texto que se ha seleccionado para darle formato
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
				procesaInteraccion(IndiceTarea.TAREA_1, tarea1, ID_CURSIVA);
				
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
				procesaInteraccion(IndiceTarea.TAREA_1, tarea1, ID_SUBRAYADO);
				
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
				procesaInteraccion(IndiceTarea.TAREA_2, tarea2, ID_IZDA);
				
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
				procesaInteraccion(IndiceTarea.TAREA_2, tarea2, ID_CENTRO);
				
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
				procesaInteraccion(IndiceTarea.TAREA_2, tarea2, ID_DCHA);
				
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
		
		/***********************************************************************
		 * 						   BARRA MENU TAREAS						   *
		 **********************************************************************/
		toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		panel.add(toolBar_1, BorderLayout.NORTH);
		
		/************************** Boton Tarea 1 *****************************/
		btnTarea1 = new JButton("Tarea 1");
		btnTarea1.setToolTipText(ENUNCIADO_TAREA_1);
		toolBar_1.add(btnTarea1);
		btnTarea1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tareaActual = IndiceTarea.TAREA_1;
				tarea1.setTInicio();
			}
		});
		btnTarea1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		/************************** Boton Tarea 2 *****************************/
		btnTarea2 = new JButton("Tarea 2");
		btnTarea2.setToolTipText(ENUNCIADO_TAREA_2);
		toolBar_1.add(btnTarea2);
		btnTarea2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tareaActual = IndiceTarea.TAREA_2;
				tarea2.setTInicio();
				tarea1.setTFin();
			}
		});
		btnTarea2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		/************************* Boton Finalizar ****************************/
		panel_6 = new JPanel();
		toolBar_1.add(panel_6);
		btnFin = new JButton("Finalizar");
		toolBar_1.add(btnFin);
		btnFin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tarea2.setTFin();
				
				VentanaSatisfaccion ventanaSatisfaccion = new VentanaSatisfaccion();
				
				// TODO (quitar)
				
				/* Calculo de metricas de usabilidad y generacion de graficas */
				/******************* TIEMPO DE REALIZACION *********************/
				tiempoTareaT1.calculaMedida();
				ventanaSatisfaccion.addMedida(tiempoTareaT1.getId(), tiempoTareaT1, IndiceTarea.TAREA_1);
				tiempoTareaT2.calculaMedida();
				ventanaSatisfaccion.addMedida(tiempoTareaT2.getId(), tiempoTareaT2, IndiceTarea.TAREA_2);
				
				// TODO (quitar)
				System.out.println("[*] tiempoT1 = " + tiempoTareaT1.getMedida() + "\ttiempoT2 = " + tiempoTareaT2.getMedida() + "\n");
				
				/********************** No. DE ERRORES ************************/
				ventanaSatisfaccion.addMedida(nErroresT1.getId(), nErroresT1, IndiceTarea.TAREA_1);
				ventanaSatisfaccion.addMedida(nErroresT2.getId(), nErroresT2, IndiceTarea.TAREA_2);
				
				// TODO (quitar)
				System.out.println("[*] erroresT1 = " + nErroresT1.getMedida() + "\terroresT2 = " + nErroresT2.getMedida() + "\n");
				
				/******************** TASA DE EFECTIVIDAD *********************/
				tasaEfectividadT1.calculaMedida();
				ventanaSatisfaccion.addMedida(tasaEfectividadT1.getId(), tasaEfectividadT1, IndiceTarea.TAREA_1);
				tasaEfectividadT2.calculaMedida();
				ventanaSatisfaccion.addMedida(tasaEfectividadT2.getId(), tasaEfectividadT2, IndiceTarea.TAREA_2);
				
				// TODO (quitar)
				System.out.println("[*] efectividadT1 = " + tasaEfectividadT1.getMedida() + "\tefectividadT2 = " + tasaEfectividadT2.getMedida() + "\n");
				
				/****************** VELOCIDAD DE REALIZACION ******************/
				velocidadT1.calculaMedida();
				ventanaSatisfaccion.addMedida(velocidadT1.getId(), velocidadT1, IndiceTarea.TAREA_1);
				velocidadT2.calculaMedida();
				ventanaSatisfaccion.addMedida(velocidadT2.getId(), velocidadT2, IndiceTarea.TAREA_2);
				
				// TODO (quitar)
				System.out.println("[*] velocidadT1 = " + velocidadT1.getMedida() + "\tvelocidadT2 = " + velocidadT2.getMedida() + "\n");
				
				ventanaSatisfaccion.visible();
				frmMicroissantW.dispose();
			}
		});
		btnTarea2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
			nuevo = new Editor(idioma, nombreUsuario);
			nuevo.hazVisible();
		} else if (s.equals("Abrir") || s.equals("Open")) {
			nuevo = new Editor(idioma, nombreUsuario);
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
	
	/**
	 * Registra el instante en que se produce una interaccion concreta de una
	 * tarea concreta, y el texto sobre el que la interaccion se lleva a cabo.
	 */
	private void procesaInteraccion(IndiceTarea i, Tarea t, String idInt) {
		if (tareaActual != null) {
			if (tareaActual == i) {
				doc = textArea.getStyledDocument();
				inicioSeleccion = textArea.getSelectionStart();
				finSeleccion = textArea.getSelectionEnd();
									
				try {
					t.completaObjetivo(
							new Interaccion(idInt, nombreUsuario,
									doc.getText(inicioSeleccion, finSeleccion),
									System.currentTimeMillis()));
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			} else {
				/* Si se lleva a cabo una interaccion no objetivo al realizar una
				 * tarea se contabiliza el error */
				listaErrores.get(tareaActual.ordinal()).addError();
			}
		}
	}
	
}
