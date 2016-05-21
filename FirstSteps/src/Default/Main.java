package Default;

import java.io.File;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class Main {

	public static void main(String[] args) throws IOException {
		// load CSV
	    Instances data = loadCSV("project_training.txt");
	    data.setRelationName("TrainingData");
	    System.out.println(data.toSummaryString());
	}
	
	private static Instances loadCSV(String file) throws IOException {
		CSVLoader loader = new CSVLoader();
	    loader.setFieldSeparator("\t");
	    loader.setSource(new File(file));
	    Instances data = loader.getDataSet();
	  	return data;
	}

}
