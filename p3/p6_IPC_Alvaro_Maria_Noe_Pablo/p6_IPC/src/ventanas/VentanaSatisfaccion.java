package ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import medidas.Medida;
import misc.IndiceTarea;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaSatisfaccion {

	private HashMap<String, Medida> medidasT1;
	private HashMap<String, Medida> medidasT2;
	
	private int satisfaccionT1;
	private int satisfaccionT2;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSatisfaccion window = new VentanaSatisfaccion();
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
	public VentanaSatisfaccion() {
		medidasT1 = new HashMap<String, Medida>();
		medidasT2 = new HashMap<String, Medida>();
		
		this.satisfaccionT1 = 0;
		this.satisfaccionT2 = 0;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Satisfaccion del usuario - Microissant Wort 2021");
		frame.getContentPane().setLayout(null);
		
		JSlider sliderT1 = new JSlider();
		sliderT1.setValue(1);
		sliderT1.setPaintTicks(true);
		sliderT1.setMinorTickSpacing(1);
		sliderT1.setMinimum(1);
		sliderT1.setMaximum(10);
		sliderT1.setBounds(10, 44, 464, 26);
		frame.getContentPane().add(sliderT1);
		
		JSlider sliderT2 = new JSlider();
		sliderT2.setValue(1);
		sliderT2.setPaintTicks(true);
		sliderT2.setMinorTickSpacing(1);
		sliderT2.setMinimum(1);
		sliderT2.setMaximum(10);
		sliderT2.setBounds(10, 148, 464, 26);
		frame.getContentPane().add(sliderT2);
		
		JLabel lbl1 = new JLabel("1");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl1.setBounds(14, 74, 7, 14);
		frame.getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("2");
		lbl2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl2.setBounds(64, 74, 7, 14);
		frame.getContentPane().add(lbl2);
		
		JLabel lbl3 = new JLabel("3");
		lbl3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl3.setBounds(114, 74, 7, 14);
		frame.getContentPane().add(lbl3);
		
		JLabel lbl4 = new JLabel("4");
		lbl4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl4.setBounds(164, 74, 7, 14);
		frame.getContentPane().add(lbl4);
		
		JLabel lbl5 = new JLabel("5");
		lbl5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl5.setBounds(214, 74, 7, 14);
		frame.getContentPane().add(lbl5);
		
		JTextArea tituloSatisfaccionT1 = new JTextArea();
		tituloSatisfaccionT1.setEditable(false);
		tituloSatisfaccionT1.setBackground(SystemColor.menu);
		tituloSatisfaccionT1.setFont(new Font("Arial", Font.BOLD, 13));
		tituloSatisfaccionT1.setText("Indique su satisfacción con la tarea 1:");
		tituloSatisfaccionT1.setBounds(10, 11, 244, 22);
		frame.getContentPane().add(tituloSatisfaccionT1);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 227, 89, 23);
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				satisfaccionT1 = sliderT1.getValue();
				satisfaccionT2 = sliderT2.getValue();
				
				generaGraficas();
				frame.dispose();
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnAceptar);
		
		JLabel lbl1_1 = new JLabel("1");
		lbl1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl1_1.setBounds(14, 178, 7, 14);
		frame.getContentPane().add(lbl1_1);
		
		JLabel lbl2_1 = new JLabel("2");
		lbl2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl2_1.setBounds(64, 178, 7, 14);
		frame.getContentPane().add(lbl2_1);
		
		JLabel lbl3_1 = new JLabel("3");
		lbl3_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl3_1.setBounds(114, 178, 7, 14);
		frame.getContentPane().add(lbl3_1);
		
		JLabel lbl4_1 = new JLabel("4");
		lbl4_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl4_1.setBounds(164, 178, 7, 14);
		frame.getContentPane().add(lbl4_1);
		
		JLabel lbl5_1 = new JLabel("5");
		lbl5_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl5_1.setBounds(214, 178, 7, 14);
		frame.getContentPane().add(lbl5_1);
		
		JTextArea tituloSatisfaccionT2 = new JTextArea();
		tituloSatisfaccionT2.setText("Indique su satisfacción con la tarea 2:");
		tituloSatisfaccionT2.setFont(new Font("Arial", Font.BOLD, 13));
		tituloSatisfaccionT2.setEditable(false);
		tituloSatisfaccionT2.setBackground(SystemColor.menu);
		tituloSatisfaccionT2.setBounds(10, 115, 244, 22);
		frame.getContentPane().add(tituloSatisfaccionT2);
		
		JLabel lbl5_6_1 = new JLabel("6");
		lbl5_6_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl5_6_1.setBounds(264, 178, 7, 14);
		frame.getContentPane().add(lbl5_6_1);
		
		JLabel lbl5_7_2 = new JLabel("7");
		lbl5_7_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl5_7_2.setBounds(314, 178, 7, 14);
		frame.getContentPane().add(lbl5_7_2);
		
		JLabel lbl8_1 = new JLabel("8");
		lbl8_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl8_1.setBounds(364, 178, 7, 14);
		frame.getContentPane().add(lbl8_1);
		
		JLabel lbl9_1 = new JLabel("9");
		lbl9_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl9_1.setBounds(414, 178, 7, 14);
		frame.getContentPane().add(lbl9_1);
		
		JLabel lbl10_1 = new JLabel("10");
		lbl10_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl10_1.setBounds(460, 178, 14, 14);
		frame.getContentPane().add(lbl10_1);
		
		JLabel lbl5_6_1_1 = new JLabel("6");
		lbl5_6_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl5_6_1_1.setBounds(264, 74, 7, 14);
		frame.getContentPane().add(lbl5_6_1_1);
		
		JLabel lbl5_7_2_1 = new JLabel("7");
		lbl5_7_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl5_7_2_1.setBounds(314, 74, 7, 14);
		frame.getContentPane().add(lbl5_7_2_1);
		
		JLabel lbl8_1_1 = new JLabel("8");
		lbl8_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl8_1_1.setBounds(364, 74, 7, 14);
		frame.getContentPane().add(lbl8_1_1);
		
		JLabel lbl9_1_1 = new JLabel("9");
		lbl9_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl9_1_1.setBounds(414, 74, 7, 14);
		frame.getContentPane().add(lbl9_1_1);
		
		JLabel lbl10_1_1 = new JLabel("10");
		lbl10_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl10_1_1.setBounds(460, 74, 14, 14);
		frame.getContentPane().add(lbl10_1_1);
	}
	
	public void visible () {
		frame.setVisible(true);
	}
	
	public void addMedida (String idMedida, Medida m, IndiceTarea i) {
		switch(i) {
		case TAREA_1:
			medidasT1.put(idMedida, m);
			break;
		case TAREA_2:
			medidasT2.put(idMedida, m);
			break;
		}
	}
	
	public HashMap<String, Medida> getMedidas (IndiceTarea i) {
		switch(i) {
		case TAREA_1:
			return(medidasT1);
		case TAREA_2:
			return(medidasT1);
		}
		return null;
	}
	
	private void generaGraficas() {
		final String TAREA1 = "TAREA1";
		final String TAREA2 = "TAREA2";

		// Se generará una gráfica por cada medida realizada
		DefaultCategoryDataset datasetTiempo = new DefaultCategoryDataset();
		DefaultCategoryDataset datasetErrores = new DefaultCategoryDataset();
		DefaultCategoryDataset datasetSatisfaccion = new DefaultCategoryDataset();
		DefaultCategoryDataset datasetVelocidad = new DefaultCategoryDataset();
		DefaultCategoryDataset datasetEfectividad = new DefaultCategoryDataset();

		// Datos Tarea 1
		datasetTiempo.setValue(medidasT1.get("tiempoTarea1").getMedida(), TAREA1, "Tiempo");
		datasetErrores.setValue(medidasT1.get("erroresTarea1").getMedida(), TAREA1, "Errores");
		datasetSatisfaccion.setValue(satisfaccionT1, TAREA1, "Satisfaccion");
		datasetVelocidad.setValue(medidasT1.get("velocidadTarea1").getMedida(), TAREA1, "Velocidad (interacciones/s");
		datasetEfectividad.setValue(medidasT1.get("efectividadTarea1").getMedida(), TAREA1, "Tasa de efectividad");

		// Datos Tarea 2
		datasetTiempo.setValue(medidasT2.get("tiempoTarea2").getMedida(), TAREA2, "Tiempo");
		datasetErrores.setValue(medidasT2.get("erroresTarea2").getMedida(), TAREA2, "Errores");
		datasetSatisfaccion.setValue(satisfaccionT2, TAREA2, "Satisfaccion");
		datasetVelocidad.setValue(medidasT2.get("velocidadTarea2").getMedida(), TAREA2, "Velocidad (interacciones/s");
		datasetEfectividad.setValue(medidasT2.get("efectividadTarea2").getMedida(), TAREA2, "Tasa de efectividad");
		
		// Creación de las gráficas
		generaGrafica(datasetTiempo, "Tiempo de compleción", "Tiempo (ms)", "tiempoComplecion.png");
		generaGrafica(datasetErrores, "Número de errores", "Errores", "errores.png");
		generaGrafica(datasetSatisfaccion, "Satisfacción", "Valor", "satisfaccion.png");
		generaGrafica(datasetVelocidad, "Velocidad de interacción", "Velocidad (interacciones/s)", "velocidad.png");
		generaGrafica(datasetEfectividad, "Tasa de efectividad", "Valor", "efectividad.png");
		
	}
	
	private void generaGrafica(DefaultCategoryDataset dataset, String titulo,
							   String lblEjeY, String nomFichPNG) {
		JFreeChart chart = ChartFactory.createBarChart3D(titulo, "", lblEjeY,
														 dataset, PlotOrientation.VERTICAL,
														 true, true, false);

		File imageFile = new File(nomFichPNG);
		int width = 640;
		int height = 480;

		try {
			ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
	
}
