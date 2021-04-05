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

	// Pie chart requires a dictionary of today's 
	// locations and their corresponding total scores
	public static JFreeChart PieChart(HashMap<String, Integer> locScores) {
		JFreeChart chart = ChartFactory.createPieChart(      
		         "Today's Stats",   // chart title 
		         createDataSet(locScores),          // data    
		         true,             // include legend   
		         true, 
		         false);
		return chart;
	}
	
	public static PieDataset createDataSet(HashMap<String, Integer> locScores) {
		DefaultPieDataset  dataset = new DefaultPieDataset ( );
	      Iterator it = locScores.entrySet().iterator();
	      while (it.hasNext()) {
	    	  HashMap.Entry pair = (HashMap.Entry)it.next();	        
	    	  dataset.setValue((Comparable) pair.getKey(), (int)pair.getValue());
	    	  it.remove(); // avoids a ConcurrentModificationException
	      }
		  return dataset;
	}
	
}
