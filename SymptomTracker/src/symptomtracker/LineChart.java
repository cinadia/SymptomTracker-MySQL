package symptomtracker;

import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class LineChart extends ApplicationFrame {

	public LineChart(HashMap<java.sql.Date, Integer> dateScores, String location) {
		super(location + " Stats");
		JFreeChart lineChart = ChartFactory.createLineChart(
				location + " Symptoms",
				"Time", 
				"Pain Score", 
		        createDataSet(dateScores));
		         
	    ChartPanel chartPanel = new ChartPanel(lineChart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
	    setContentPane(chartPanel);
	}
	
	private DefaultCategoryDataset createDataSet(HashMap<java.sql.Date, Integer> dateScores) {
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      Iterator it = dateScores.entrySet().iterator();
	      while (it.hasNext()) {
	    	  HashMap.Entry pair = (HashMap.Entry)it.next();	        
	    	  dataset.addValue((int) pair.getValue(), (Comparable) "score", (Comparable) pair.getKey());
	    	  it.remove(); // avoids a ConcurrentModificationException
	      }
		  return dataset;
	}
	
	public void windowClosing(WindowEvent e) {
		this.dispose();
	}
	public static void main( String[ ] args ) {
		/*
		String str ="2021-01-01";  
		Date start = Date.valueOf(str);  
		 
		String str2 ="2021-03-25";  
		Date end = Date.valueOf(str2); 
		    
		LineChart chart = new LineChart(Stats.getScoresDates(start, end, "jaw"), "jaw");
		
		chart.pack( );
		chart.setVisible( true ); */
	}
}
