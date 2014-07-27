package com.bridge.charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.jdbc.JDBCPieDataset;

public class ChartConstructor {
	/**
	 * To drawChart
	 * @param response
	 * @param dataset
	 * @param title
	 */
	public void drawChart(HttpServletResponse response, JDBCPieDataset dataset, String title){			
		JFreeChart chart = ChartFactory.createPieChart(title, dataset, true,
				true, false);
		TextTitle t = chart.getTitle();
		t.setFont(new Font("Arial", Font.BOLD, 10));
		chart.setBorderPaint(Color.black);
		chart.setBorderStroke(new BasicStroke(2.0f));
		chart.setBorderVisible(true);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 8));
		plot.setNoDataMessage("No data available");
		plot.setLabelGap(0.02);
		PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator(
				"{0}({1})", new DecimalFormat("0"), new DecimalFormat("0.00%"));
		plot.setLabelGenerator(generator);
		if (chart != null) {
			int width = 300;
			int height = 300;
			final ChartRenderingInfo info = new ChartRenderingInfo(
					new StandardEntityCollection());
			response.setContentType("image/png");
			OutputStream out = null;
			try {
				out = response.getOutputStream();
			} catch (IOException e) {				
				e.printStackTrace();
			}
			try {
				ChartUtilities.writeChartAsPNG(out, chart, width, height, info);
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
			
	}

}
