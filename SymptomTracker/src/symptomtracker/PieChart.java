package symptomtracker;

import java.util.HashMap;
import java.util.Iterator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

public class PieChart {

	/** 
	 * Creates new pie chart with HashMap of 
	 * symptom locations and their
	 * corresponding scores
	 * @param locScores HashMap<String, Integer> of symptom locations to scores
	 * @return the created pie chart (JFreeChart)
	 */
	public JFreeChart PieChart(HashMap<String, Integer> locScores) {
		JFreeChart chart = ChartFactory.createPieChart(      
		         "Today's Stats",   // chart title 
		         createDataSet(locScores),          // data    
		         true,             // include legend   
		         true, 
		         false);
		return chart;
	}
	
	/**
	 * Create data set for a pie chart using HashMap of 
	 * symptom locations and their corresponding scores
	 * @param locScores HashMap<String, Integer> of symptom locations to scores
	 * @return the final dataset (PieDataSet)
	 */
	public PieDataset createDataSet(HashMap<String, Integer> locScores) {
		DefaultPieDataset  dataset = new DefaultPieDataset ( );
		// Iterator to move through HashMap
	      Iterator it = locScores.entrySet().iterator(); 
	      while (it.hasNext()) {
	    	  // HashMap.Entry is a single object containing
	    	  // the HashMap key and value
	    	  HashMap.Entry pair = (HashMap.Entry)it.next();	        
	    	  dataset.setValue((Comparable) pair.getKey(), (int)pair.getValue());
	    	  it.remove(); // Avoids a ConcurrentModificationException
	      }
		  return dataset;
	}
	
}
