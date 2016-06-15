

import java.io.File;
import java.io.IOException;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;


/*
 * Friederike Hanssen, Thomas Gauges
 */
//to read in file: run configurations > (x)Arguments: "project_training.txt", press 'apply'
public class Parser {

	private static Instances data;

	public Parser() throws IOException {
		Instances data = null;
		
	}

	public static Instances getData() {
		return data;
	}

	public Instances loadCSV(String filename) throws IOException {
		// CSV loader can also load tab separated data if specified
		CSVLoader loader = new CSVLoader();
		loader.setFieldSeparator("\t");
		loader.setSource(new File(filename));
		data = loader.getDataSet();
		data.setRelationName("TrainingData");
		return data;
	}
	
	/*private Instances loadARFF(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		ArffReader loader = new ArffReader(reader);
		data = loader.getData();
		data.setRelationName("TrainingData");
		return data;
	}*/

	/*// Arff is wekas prefered file system
	private void createArff(String filename) throws IOException {
		ArffSaver saver = new ArffSaver();
		saver.setFile(new File(filename));
		saver.setInstances(data);
		saver.writeBatch();

	}
*/

	// Creates a CSV output with tab separated values, as specified in output
	// example
	private void createCSV(String filename) throws IOException {
		CSVSaver saver = new CSVSaver();
		saver.setFile(new File(filename));
		saver.setInstances(data);
		saver.setFieldSeparator("\t");
		saver.writeBatch();

	}


}
