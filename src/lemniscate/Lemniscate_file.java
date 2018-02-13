package lemniscate;

/**
 * created by Nadezhda Grishko 
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import acm.io.IODialog;

public class Lemniscate_file {
	public static void main(String[] args) throws Exception {

		IODialog dialog = new IODialog(); // діалог з користувачем
		double step = dialog.readDouble("Enter step: "); // користувач повинен
															// задати крок
		int a = dialog.readInt("Enter constant a: "); // задати константу

		JFrame frame = new JFrame(); // створюємо каркас вікна
		frame.setTitle("Grishko_Nadezhda"); // назва заголовку вікна
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрити вікно
																// при
																// натисканні на
																// хрестик

		// створюємо перший ряд даних, задаємо назву
		XYSeries series = new XYSeries("r^2 = 2 * a^2 * cos(2 * fi)");

		// за допомогою циклу малюємо лемніскату
		for (double fi = 0; fi < 2 * Math.PI; fi = fi + step) {
			double ro = Math.sqrt(2 * a * a * Math.cos(2 * fi));
			double x = ro * Math.cos(fi);
			double y = ro * Math.sin(fi);

			series.add(x, y); // додаємо точки, щоб утворився графік

		}

		// одразу додаємо ряд в набір даних
		XYSeriesCollection data = new XYSeriesCollection(series);

		// створюємо діаграму
		final JFreeChart chart = ChartFactory.createXYLineChart("Лемніската", // заголовок
																				// діаграми
				"X", // назва осі X
				"Y", // назва осі Y
				data, // дані
				PlotOrientation.VERTICAL, // орієнтація
				true, // включити легенду
				true, // підказки
				false // urls

		);

		XYPlot plot = chart.getXYPlot(); // повертає графік

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(); // створюємо
																		// екземпляр
																		// класу
																		// XYLineAndShapeRenderer

		renderer.setSeriesPaint(0, Color.MAGENTA); // задаємо колір графіка
		renderer.setSeriesStroke(0, new BasicStroke(4)); // задаємо товщину
															// графіка

		plot.setRenderer(renderer); // встановлює визуализатор для набору даних
									// із зазначеним індексом

		int width = 800; // ширина зображення
		int height = 400; // висота зображення
		File XYChart = new File("Lemniscate.png"); // назва та формат зображення
		ChartUtilities.saveChartAsJPEG(XYChart, chart, width, height);

	}

}
