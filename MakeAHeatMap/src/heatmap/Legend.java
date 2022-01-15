package heatmap;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import org.tc33.jheatchart.HeatChart;

public class Legend {

	public Legend(double maxConc) throws IOException {
				
		double[][] gradient = new double[1][4];
	
		
		int k = 4;
		for (int j = 0; j < gradient[0].length; j++) {
			gradient[0][j] = ((k*maxConc)/4);
			k--;
		}
		
		
		Double[] xs = new Double[gradient[0].length];
		for (int i = 0; i < gradient[0].length; i++) {
			Double d = gradient[0][i];
			xs[i] = d;
		}
		

		HeatChart leg = new HeatChart(gradient);
		leg.setColourScale(1.0);
		Color dark = new Color(88, 19, 112);
		leg.setHighValueColour(dark);
		Color light = new Color(243, 228, 249);
		leg.setLowValueColour(light);
		leg.setAxisThickness(0);
		Dimension d = new Dimension(10,20);
		leg.setCellSize(d);		
		leg.setXValues(xs);
		leg.setXAxisValuesFrequency(gradient[0].length/3);
		leg.setShowYAxisValues(false);
		leg.setAxisThickness(0);
		
		File fi = new File("C:\\Users\\730236688\\Desktop\\" + "expt12Legend2" + ".png");
		leg.saveToFile(fi);
		
		System.out.println("done");
		
		
	}
	
	
}