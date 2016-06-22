package neuralNetworks;

import java.io.IOException;

import encoder.BlosumEncoder;
import encoder.Encoder;
import parser.EncodeParser;
/**
 * @author Friederike
 */
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

//TODO: generate all the different ANNs and store them
public class NeuralNetwork implements EncodeParser{

	private MultilayerPerceptron ann;

	/**
	 * SHOULD NOT BE USED BY ANY OTHER CLASS. 
	 * The default constructor is needed to pre-generate all ANNs
	 */
	public NeuralNetwork() {

	}

	/**
	 * Generates a new ANN. Needs the filename of the model. Methods to classify
	 * your data with a given model are then provided.
	 * 
	 * @param filename
	 *            Proper filename such as 9bitIC50 etc
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

	
	
	/**
	 * SHOULD NOT BE USED, UNLESS NEW ANNs ARE GENERATED
	 * @param trainingsdata
	 * @param filename
	 */
	private void generateANN(Instances trainingsdata, String filename) {

		MultilayerPerceptron ann = new MultilayerPerceptron();
		ann.setLearningRate(0.1);
		ann.setMomentum(0.2);
		ann.setTrainingTime(100);
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

	
	/**
	 * Needed to separately generate ANNs. 
	 */
	
	public static void main(String[] args) throws IOException {

		NeuralNetwork generator = new NeuralNetwork();
		Encoder enc = new BlosumEncoder(55);
		Instances trainingsdata = EncodeParser.readTrainingAndEncode("project_training.txt", false, enc);
		generator.generateANN(trainingsdata, "ModelANNs/blosum55IC50");

	}
	
}
