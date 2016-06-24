package defaultPackage;

/**
 * 
 * @author Daria
 * 
 */

import java.util.Random;
import encoder.Encoder;
import encoder.NineBitEncoder;
import encoder.SixCharEncoder;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;
import weka.filters.unsupervised.attribute.NumericToNominal;

/**
 * Class for Cross-Validation and Evaluation of the given ANN
 *  
 */

public class Validation {	
	
	private Instances encodedData;
	private MultilayerPerceptron ann;
	private Encoder encoder;
		
	/**
	 * The constructor create an object of the class Validation for a given data set and encoder. 
	 * The data set is encoded by function "readInputAndEncode" and will be saved as class field.
	 * The empty ANN will be created.    
	 * 
	 * @param data : the training data set
	 * @param encoder : chosen encoding type
	 * @throws Exception
	 */
	
	public Validation(Instances data, Encoder encoder) throws Exception{
		
		this.encodedData = data;//encoder.encodeAll(data);
		encodedData.setClassIndex(encodedData.numAttributes()-1);
		this.ann = new MultilayerPerceptron();
		this.encoder=encoder;
	}
	
	/**
	 * Cross-Validation of the ANN with given network parameters to the encoded data set.
	 * 
	 * @param momentum
	 * @param learningRate
	 * @param learningSteps
	 * @param HiddenLayers
	 * @throws Exception
	 */
	
	public void CrossValidate(double momentum, double learningRate, int learningSteps, String HiddenLayers)
			throws Exception {
		
		this.ann.setLearningRate(learningRate);
		this.ann.setMomentum(momentum);
		this.ann.setTrainingTime(learningSteps);
		this.ann.setHiddenLayers(HiddenLayers); 
		
		Evaluation CV = new Evaluation(this.encodedData);
		Random rand = new Random(1);
		CV.crossValidateModel(this.ann, this.encodedData, 10, rand);
		
		printResults(CV);
	}
	
	/**
	 * Help function for printing the cross validation results.
	 * @param eval
	 */
	
	private void printResults(Evaluation eval){
		System.out.println("The given encoder is "+this.encoder.getClass());
		System.out.println("Cross-Validatoin Output for the following parameters:");
		System.out.println("Momentum-term: "+this.ann.getMomentum());
		System.out.println("Learning Rate: "+this.ann.getLearningRate());
		System.out.println("Training Time: "+this.ann.getTrainingTime());
		System.out.println("Number of Hidden Layers: "+this.ann.getHiddenLayers());
		System.out.println();		
		System.out.println(eval.toSummaryString());
		
//		System.out.println("Confusion Matrix:");
//		System.out.println(eval.toMatrixString());	
	}
	
	/**
	 * for Testing
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		SixCharEncoder encode = new SixCharEncoder();
		Instances data = parser.EncodeParser.readInputAndEncode("train_mini.txt", encode);
//		Instances data = parser.EncodeParser.readInputAndEncode("project_training.txt", encode);
		data.setClassIndex(data.numAttributes()-1);
//		System.out.println(data.instance(0));
		
		//do the Validation
		Validation val = new Validation(data, encode);
		val.CrossValidate(0.7, 0.05, 1000, "9");
	}

}
