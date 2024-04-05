package misc;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Visualiza {

	public static void main(String[] args) {
		final String TAREA1 = "TAREA1";
		final String TAREA2 = "TAREA2";

		// Creamos y rellenamos el modelo de datos
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Datos Tarea 1
		dataset.setValue(21, TAREA1, "Tiempo");
		dataset.setValue(12, TAREA1, "Errores");
		dataset.setValue(83, TAREA1, "Satisfaccion");

		// Datos Tarea 2
		dataset.setValue(21, TAREA2, "Tiempo");
		dataset.setValue(12, TAREA2, "Errores");
		dataset.setValue(83, TAREA2, "Satisfaccion");

		JFreeChart chart = ChartFactory.createBarChart3D("Usabilidad", "Métricas",
				"Valores", dataset, PlotOrientation.VERTICAL, true,
				true, false);

		File imageFile = new File("ejemplo.png");
		int width = 640;
		int height = 480;

		try {
			ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		// TODO: se cambia la métrica "objetivos" por "tasa de efectividad", cuyo
		// valor se proporcionarán en una gráfica a parte, al estar este valor
		// comprendido en el rango [0,1]. Ocurre lo mismo con la velocidad de
		// realización.
		
		// TODO: generar la gráfica de la tasa de efectividad
		
		// TODO: generar la gráfica para la velocidad de realización

	}

}
