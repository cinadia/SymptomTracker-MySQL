package symptomtracker;

import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class LineChart extends ApplicationFrame {

	// LineChart object requires dictionary of the 
	// scores corresponding with each date, 
	// and the location the data corresponds with
	/**
	 * Creates a JFreeChart line chart with HashMap of scores
	 * corresponding with each date for the given location
	 * @param dateScores Hashmap<java.sql.Date, Integer> of dates and scores
	 * @param location location of symptom
	 */
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
	
	// Make dataset for line chart
	/**
	 * Makes a data set for the line chart with HashMap of 
	 * dates and corresponding scores
	 * @param dateScores HashMap<java.sql.Date, Integer> of dates and scores
	 * @return the dataset
	 */
	private DefaultCategoryDataset createDataSet(HashMap<java.sql.Date, Integer> dateScores) {
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      // Iterator to loop through HashMap
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
}
