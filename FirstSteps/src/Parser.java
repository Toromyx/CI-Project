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

	private Instances loadCSV(String filename) throws IOException {
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

	public static void main(String[] args) throws IOException {
		Parser parserCSV = null;
		
		// maybe read in more then one file?
		if (args.length < 1) {
			System.err.println("You should specify at least one file as input!");
			System.exit(1);
		} else {

			parserCSV = new Parser();
			parserCSV.loadCSV(args[0]);
			//required output
			parserCSV.createCSV("output.txt");
						
			try {
				MultilayerPerceptron mlp = new MultilayerPerceptron();
				System.out.println("Mlp initialized");
				// Setting Parameters
				mlp.setLearningRate(0.1);
				mlp.setMomentum(0.2);
				mlp.setTrainingTime(2000);
				mlp.setHiddenLayers("3");
				
				//necessary, not sure why though
				parserCSV.data.setClassIndex(data.numAttributes() - 1);
				
				mlp.buildClassifier(parserCSV.data);
				System.out.println("Classifier built");
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		
		
	}
}
