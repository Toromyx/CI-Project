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
import encoder.SixCharEncoder;
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

public class Validation {	
	
	private Instances encodedData;
	private MultilayerPerceptron ann;
	private Encoder encoder;
		
	public Validation(Instances data, Encoder encoder) throws Exception{
		
		this.encodedData = encoder.encodeAll(data);
		encodedData.setClassIndex(encodedData.numAttributes()-1);
		
		this.ann = new MultilayerPerceptron();
		this.encoder=encoder;
	}
	
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
	
	
//	public static void main(String[] args) throws Exception {
	
//	}

}
