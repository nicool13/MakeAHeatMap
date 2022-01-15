package heatmap;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.tc33.jheatchart.HeatChart;

public class Main extends JFrame {

	public static void main(String[] args) throws IOException {
		//CHANGE FILE LOCATION !!
		File feq = new File("C:\\Users\\730236688\\Downloads\\Experiment 12.eq");
		//File fnums = new File("C:\\Users\\730236688\\Desktop\\dimers.ocx-key");

		//File fseqs = new File("C:\\Users\\730236688\\Desktop\\ex.txt");

		Scanner scanEq = new Scanner(feq);
		//Scanner scanNums = new Scanner(fnums);
		//	Scanner scanSeqs = new Scanner(fseqs);
		ArrayList<Double> eqCons = new ArrayList<Double>();

		//FileWriter fSeqsAndEqs = new FileWriter("C:\\Users\\730236688\\Desktop\\expt.txt");

		/*
		ArrayList<String> seqs = new ArrayList<String>(); 

		while(scanSeqs.hasNextLine()) {
			seqs.add(scanSeqs.nextLine());
		}
		 */
		//skip the header
		String curLine = scanEq.nextLine();
		//System.out.println("curLine:"+curLine);
		while(curLine.startsWith("%")) {	
			curLine = scanEq.nextLine();
			//System.out.println(curLine);
		}


		//System.out.println(scanEq.next());



		//String seqNum = firstLine.next();

		//fSeqsAndEqs.write(seqNum + "\t");
		//System.out.println("seqNum:"+ seqNum);
		/*
		String curSeq = "";
		for (int i = 0; i < seqs.get(0).length()-1; i++) {
			curSeq += firstLine.next();
		}
		System.out.println("curSeq:"+curSeq);

		for (int j = 0; j < seqs.size(); j++) {
			if (curSeq.equals(seqs.get(j))) {
				seqs.remove(j);
				fSeqsAndEqs.write("CORRECT\t");
			}
		}
		 */
		Double conc = 0.1;
		//skip the header
		while(scanEq.hasNextLine() && conc > 0.00000000001) {
			conc = 0.0;
			curLine = scanEq.nextLine();
			Scanner line = new Scanner(curLine);
			while (line.hasNext()) {
				conc = line.nextDouble();
			}
			System.out.println(conc);
			eqCons.add(conc);
		}




		//fSeqsAndEqs.write(firstLine.next()+"\n");
/*
		while(scanEq.hasNext()) {
			int correctness = 0;
			seqNum = scanEq.next();
			fSeqsAndEqs.write(seqNum + "\t");
			System.out.println("seqNum:"+ seqNum);

			curSeq = "";
			for (int i = 0; i < seqs.get(0).length()-1; i++) {
				curSeq += scanEq.next();
			}
			System.out.println("curSeq:"+curSeq);

			for (int j = 0; j < seqs.size(); j++) {
				if (curSeq.equals(seqs.get(j))) {
					seqs.remove(j);
					fSeqsAndEqs.write("CORRECT\t");
				}
			}

			scanEq.next();
			fSeqsAndEqs.write(scanEq.next()+"\n");


		}
		*/
		scanEq.close();

		double[][] zs = new double[1][eqCons.size()];
		for (int i = 0; i < eqCons.size(); i++) {
			zs[0][i] = (double) eqCons.get(i);
		}

		Legend l = new Legend(3.53e-5);

		Double[] xValues = new Double[eqCons.size()];
		for (int i = 0; i < (xValues.length/2); i++) {
			BigDecimal bd = BigDecimal.valueOf(eqCons.get(i));
			bd = bd.setScale(9, RoundingMode.HALF_UP);
			xValues[i] = bd.doubleValue();
		}
		for (int i = (xValues.length/2); i < xValues.length; i++) {
			BigDecimal bd = BigDecimal.valueOf(eqCons.get(i));
			bd = bd.setScale(11, RoundingMode.HALF_UP);
			xValues[i] = bd.doubleValue();
		}

		HeatChart heatmap = new HeatChart(zs);
		heatmap.setColourScale(1.0);
		heatmap.setShowXAxisValues(true);
		//heatmap.setXAxisValuesFrequency(2);
		heatmap.setXValuesHorizontal(false);
		heatmap.setXAxisValuesFrequency(50);
		heatmap.setXValues(xValues);
		heatmap.setShowYAxisValues(false);
		heatmap.setXAxisLabel("Concentration (M)");
		Color dark = new Color(88, 19, 112);
		heatmap.setHighValueColour(dark);
		Color light = new Color(243, 228, 249);
		heatmap.setLowValueColour(light);
		heatmap.setAxisThickness(0);
		Dimension d = new Dimension(30,60);
		heatmap.setCellSize(d);












		/*
		JFrame f = new JFrame();
		Container contentPane = new Container();
		f.setContentPane(contentPane);
		JLabel l = new JLabel(new ImageIcon(heatmap.getChartImage()));
		contentPane.setBounds(0, 0, heatmap.getChartSize().width, heatmap.getChartSize().height);
		f.setBounds(0, 0, heatmap.getChartSize().width, heatmap.getChartSize().height);
		JSeparator s = new JSeparator();
		s.setBounds(240, 0, 2, heatmap.getChartSize().height);
		l.add(s);
		contentPane.add(l);
		 */
		//CHANGE OUTPUT FILE NAME !!
		File fi = new File("C:\\Users\\730236688\\Desktop\\" + "plsworkbby" + ".png");
		/*
		Container content = f.getContentPane();
        BufferedImage img = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        content.printAll(g2d);
        g2d.dispose();

        try {
            ImageIO.write(img, "png", new File("C:\\Users\\730236688\\Desktop\\" + "" + ".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		 */
		heatmap.saveToFile(fi);
		//fSeqsAndEqs.close();

		System.out.println("done");

	}

}