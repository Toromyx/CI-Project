package neuralNetworks;
import java.io.File;
import java.io.IOException;

import src.Parser;
/**
 * @author Friederike
 */
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class NeuralNetwork {

	private MultilayerPerceptron ann;

	public NeuralNetwork(){
		
	}
	/**
	 * Generates a new ANN. Needs the filename of the model. Methods to classify your data with a given model are then provided.
	 * @param filename Proper filename such as Ic509bitAnn etc
	 * @throws Exception something went wrong with reading in the file
	 */
	public NeuralNetwork(String filename) throws Exception {
		ann = (MultilayerPerceptron) weka.core.SerializationHelper.read(filename);
	}

	/**
	 * Classifies each instance of a dataset
	 * @param testdata
	 * @return classified testdata set
	 */
	public Instances classifyData(Instances testdata) {
		Instances output = new Instances(testdata, testdata.numInstances());
		try {
			System.out.println(testdata.instance(0));
			System.out.println(testdata.instance(1));
			//System.out.println(testdata.instance(0));
			double label = ann.classifyInstance(testdata.instance(0));
			//System.out.println(label);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*for(int i = 0; i < testdata.numInstances(); i++){
			double label = -1;
			try {
				label = ann.classifyInstance(testdata.instance(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			output.instance(i).setClassValue(label);
		}*/
		return output;		
	}
	
	private void generateANN(Instances trainingsdata, String filename) {

		MultilayerPerceptron ann = new MultilayerPerceptron();
		ann.setLearningRate(0.1);
		ann.setMomentum(0.2);
		ann.setTrainingTime(2000);
		ann.setHiddenLayers("2");

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
	
	
	
	public static void main(String[] args) throws IOException{
		
		//NeuralNetwork generator = new NeuralNetwork();
		Parser loader = new Parser();
		/*
		generator.generateANN(loader.loadCSV(args[0]), "IC50ann");
		generator.generateANN(loader.loadCSV(args[1]), "BinaryAnn");
		generator.generateANN(loader.loadCSV(args[2]), "CombinedAnn");
		*/
		
		Instances testdata = loader.loadCSV("test_input.txt");
		System.out.println(testdata);
		
		try {
			NeuralNetwork test = new NeuralNetwork("IC50ann");
			Instances output = test.classifyData(testdata);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
