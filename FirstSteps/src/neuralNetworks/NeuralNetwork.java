package neuralNetworks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Friederike
 */
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

public class NeuralNetwork {

	private MultilayerPerceptron ann;

	public NeuralNetwork() {

	}

	/**
	 * Generates a new ANN. Needs the filename of the model. Methods to classify
	 * your data with a given model are then provided.
	 * 
	 * @param filename
	 *            Proper filename such as Ic509bitAnn etc
	 * @throws Exception
	 *             something went wrong with reading in the file
	 */
	public NeuralNetwork(String filename) throws Exception {
		ann = (MultilayerPerceptron) weka.core.SerializationHelper.read(filename);
	}

	/**
	 * Classifies each instance of a dataset
	 * 
	 * @param testdata
	 * @return classified testdata set
	 */
	public Instances classifyData(Instances testdata) {
		Instances output = new Instances(testdata);
		for (int i = 0; i < testdata.numInstances(); i++) {
			double label = -1;
			try {
				label = ann.classifyInstance(testdata.instance(i));
				output.instance(i).setClassValue(label);
			} catch (Exception e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return output;
	}

	private void generateANN(Instances trainingsdata, String filename) {

		MultilayerPerceptron ann = new MultilayerPerceptron();
		ann.setLearningRate(0.1);
		ann.setMomentum(0.2);
		ann.setTrainingTime(2000);
		ann.setHiddenLayers("80,1"); // sets number nodes in the hidden layer: 2
										// hidden layers one with 80 one with 1
										// -> output layer

		trainingsdata.setClassIndex(trainingsdata.numAttributes() - 1);

		try {
			ann.buildClassifier(trainingsdata);
		} catch (Exception e) {
			System.err.println("Classifier couldn't be build");
			e.printStackTrace();
		}

		try {
			weka.core.SerializationHelper.write(filename, ann);
		} catch (Exception e) {
			System.err.println("Classifier couldn't be stored");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		// NeuralNetwork generator = new NeuralNetwork();
		// Parser loader = new Parser();

		// generator.generateANN(loader.loadCSV(args[0]), "IC50ann");
		// generator.generateANN(loader.loadCSV(args[1]), "BinaryAnn");
		// generator.generateANN(loader.loadCSV(args[2]), "CombinedAnn");

		Instances datapredict = new Instances(new BufferedReader(new FileReader("test_input.arff")));
		datapredict.setClassIndex(datapredict.numAttributes()-1);
		Instances predicteddata = new Instances(datapredict);

		System.out.println(datapredict);
		
		try {
			NeuralNetwork test = new NeuralNetwork("IC50ann"); // Instances
			predicteddata = test.classifyData(datapredict);
			BufferedWriter writer = new BufferedWriter(	new FileWriter("output.arff"));
					writer.write(predicteddata.toString());
					writer.newLine();
					writer.flush();
					writer.close();
		} catch (Exception e) { // TODO
			e.printStackTrace();
		}
		 

	}

}
