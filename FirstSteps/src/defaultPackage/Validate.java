package defaultPackage;

/**
 * 
 * @author Daria
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Random;

import com.sun.xml.internal.bind.v2.runtime.output.Encoded;

import encoder.Encoder;
import encoder.NineBitEncoder;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.core.converters.CSVLoader;

/**
 * Class for Cross-Validation and Evaluation of the given ANN
 *  
 */

public class Validate {
	/**
	 * Temporary  
	 * for testing the ANN after Cross-Validation
	 */
	private static Instances train;
	private static Instances test;
	
	/**
	 * for End-Version 
	 */
	private static Instances data;
	private MultilayerPerceptron ann;
	private Encoder encoder;
	private double momentum;
	private double learningRate; 
	private int learningSteps; 
	private String HiddenLayers;
 	
	
		
	public Validate(Instances encodedData, Encoder encoder, 
			double m, double rate, int steps, String HiddenLayers) throws Exception{
		
		this.data = encodedData;
		data.setClassIndex(data.numAttributes()-1);
		
		/**
		 * Temporary Constructor 
		 */
		// to make Filters work (because of the "Cannot handle numeric
		// class!"-Exception)
		/*NumericToNominal NtoN = new NumericToNominal();
		NtoN.setInputFormat(encodedData);
		encodedData = Filter.useFilter(encodedData, NtoN);

		// filter the Data to create 2 data-sets for the
		// cross-validation 
		Resample res = new Resample();
		res.setNoReplacement(true);
		res.setSampleSizePercent(80);
		res.setInputFormat(encodedData);

		this.train = Filter.useFilter(encodedData, res);

		res.setInvertSelection(true);
		res.setInputFormat(encodedData);
		this.test = Filter.useFilter(encodedData, res);	
		
		train.setClassIndex(train.numAttributes()-1);
		test.setClassIndex(test.numAttributes()-1);*/
	}
	
	private static void CrossValidate(MultilayerPerceptron ann) throws Exception {
		
		System.out.println("Cross-Validatoin Output:");
		// create Evaluation for Cross-Validation
		// jetzt nur f�r train-set, danach f�r ganze Data
		Evaluation CV = new Evaluation(data);
//		Evaluation CV = new Evaluation(train);
		Random rand = new Random(1);
		
//		CV.crossValidateModel(ann, data, 10, rand);
		CV.crossValidateModel(ann, data, 10, rand);
		System.out.println(CV.toSummaryString());
		System.out.println("Confusion Matrix:");
		System.out.println(CV.toMatrixString());
		
	}
	
	public static void testANN(MultilayerPerceptron ann) throws Exception{
		// create Evaluation (now for train-set, just to make sure it works)
		Evaluation eval = new Evaluation(test);
		System.out.println();
		eval.evaluateModel(ann, test);
		System.out.println("Evaluation results:");
		System.out.println(eval.toSummaryString());	
	}
	
	public static void main(String[] args) throws Exception {
		NineBitEncoder encode = new NineBitEncoder();
		Instances data = parser.EncodeParser.readInputAndEncode("project_training.txt", encode);
		
		MultilayerPerceptron ann = new MultilayerPerceptron();
		
//		Validate val = new Validate(data);
//		val.CrossValidate(ann);
	}

}
