package lemniscate;

/**
 * created by Nadezhda Grishko 
 */

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import acm.io.IODialog;

public class Lemniscate_picture {
	public static void main(String[] args) throws Exception {

		IODialog dialog = new IODialog(); // діалог з користувачем
		double step = dialog.readDouble("Enter step: "); // просимо користувача
															// ввести крок
		int a = dialog.readInt("Enter constant a: "); // ввести константу

		JFrame frame = new JFrame(); // створюємо каркас вікна
		frame.setTitle("Grishko_Nadezhda"); // назва заголовку вікна
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрити вікно
																// та припинити
																// виконання
																// програми при
																// натисканні
																// кнопки
																// закриття

		// створюємо перший ряд даних, задаємо назву
		XYSeries series = new XYSeries("r^2 = 2 * a^2 * cos(2 * fi)");

		// за допомогою циклу малюємо лемніскату
		for (double fi = 0; fi < 2 * Math.PI; fi = fi + step) {
			double ro = Math.sqrt(2 * a * a * Math.cos(2 * fi));
			double x = ro * Math.cos(fi);
			double y = ro * Math.sin(fi);

			series.add(x, y); // додаємо лемніскату на полотно

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

		renderer.setSeriesPaint(0, Color.GREEN); // задаємо колір графіка
		renderer.setSeriesStroke(0, new BasicStroke(4)); // задаємо товщину
															// графіка

		plot.setRenderer(renderer); // встановлює визуализатор для набору даних
									// із зазначеним індексом

		// створюємо панель для графіка
		final ChartPanel chartPanel = new ChartPanel(chart);

		// встановлюємо розмір діаграми
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));

		// додаємо панель на створений нами фрейм
		frame.setContentPane(chartPanel);

		// підганяємо розміри фрейму
		frame.pack();

		// робимо усе видимим
		frame.setVisible(true);

	}

}
