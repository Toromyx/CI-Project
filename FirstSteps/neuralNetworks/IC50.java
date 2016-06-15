package neuralNetworks;

import java.io.File;
import java.io.IOException;

import src.Parser;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.misc.SerializedClassifier;
import weka.core.Debug;
import weka.core.Instances;

/**
 * 
 * @author Friederike
 *
 *         initialize IC50 with data that is applied to it 
 *         load-pre generated IC50 ANN 
 *         apply test data
 */
public class IC50 extends NeuralNetwork {

	private Instances testdata;
	private MultilayerPerceptron ann ;

	public IC50(){
		
	}
	public IC50(Instances testdata) {
		this.testdata = testdata;
		try {
			this.ann = (MultilayerPerceptron)weka.core.SerializationHelper.read("/IC50ann");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Instances run() {
		
		//TODO how to continue? Kilian?
		return null;
	}

	@Override
	protected void generateANN(Instances trainingsdata) {
		
		MultilayerPerceptron ann = new MultilayerPerceptron();
		ann.setLearningRate(0.1);
		ann.setMomentum(0.2);
		ann.setTrainingTime(2000);
		ann.setHiddenLayers("2");

		//TODO not sure if this actually sets the attributes right, maybe just generate a test file with one attribute or the other or both?
		trainingsdata.setClassIndex(1);

		try {
			ann.buildClassifier(trainingsdata);
		} catch (Exception e) {
			System.out.println("Classifier couldn't be build");
			e.printStackTrace();
		}
		
		try {
			weka.core.SerializationHelper.write("IC50ann", ann);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		
	public static void main(String[] args) throws IOException {
		Parser parserCSV = null;

		parserCSV = new Parser();
		parserCSV.loadCSV(args[0]);
		
		IC50 test = new IC50();
		test.generateANN(parserCSV.getData());
		
		
		System.out.println("Test completed without runtime errors");
	}
	
}
