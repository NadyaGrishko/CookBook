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

		IODialog dialog = new IODialog(); // ����� � ������������
		double step = dialog.readDouble("Enter step: "); // ���������� �������
															// ������ ����
		int a = dialog.readInt("Enter constant a: "); // ������ ���������

		JFrame frame = new JFrame(); // ��������� ������ ����
		frame.setTitle("Grishko_Nadezhda"); // ����� ��������� ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������� ����
																// ���
																// ��������� ��
																// �������

		// ��������� ������ ��� �����, ������ �����
		XYSeries series = new XYSeries("r^2 = 2 * a^2 * cos(2 * fi)");

		// �� ��������� ����� ������� ���������
		for (double fi = 0; fi < 2 * Math.PI; fi = fi + step) {
			double ro = Math.sqrt(2 * a * a * Math.cos(2 * fi));
			double x = ro * Math.cos(fi);
			double y = ro * Math.sin(fi);

			series.add(x, y); // ������ �����, ��� ��������� ������

		}

		// ������ ������ ��� � ���� �����
		XYSeriesCollection data = new XYSeriesCollection(series);

		// ��������� �������
		final JFreeChart chart = ChartFactory.createXYLineChart("���������", // ���������
																				// �������
				"X", // ����� �� X
				"Y", // ����� �� Y
				data, // ���
				PlotOrientation.VERTICAL, // ��������
				true, // �������� �������
				true, // �������
				false // urls

		);

		XYPlot plot = chart.getXYPlot(); // ������� ������

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(); // ���������
																		// ���������
																		// �����
																		// XYLineAndShapeRenderer

		renderer.setSeriesPaint(0, Color.MAGENTA); // ������ ���� �������
		renderer.setSeriesStroke(0, new BasicStroke(4)); // ������ �������
															// �������

		plot.setRenderer(renderer); // ���������� ������������ ��� ������ �����
									// �� ���������� ��������

		int width = 800; // ������ ����������
		int height = 400; // ������ ����������
		File XYChart = new File("Lemniscate.png"); // ����� �� ������ ����������
		ChartUtilities.saveChartAsJPEG(XYChart, chart, width, height);

	}

}
