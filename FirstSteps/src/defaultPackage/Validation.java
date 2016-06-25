package defaultPackage;

import java.awt.BorderLayout;
import java.text.DecimalFormat;

/**
 * 
 * @author Daria
 * 
 */

import java.util.*;
import java.util.Random;
import encoder.Encoder;
import encoder.NineBitEncoder;
import encoder.SixCharEncoder;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;

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
	
	public void CrossValidateNumeric(int foldsNumber, 
			double momentum, double learningRate, int learningSteps, String HiddenLayers)
			throws Exception {
		
		this.ann.setLearningRate(learningRate);
		this.ann.setMomentum(momentum);
		this.ann.setTrainingTime(learningSteps);
		this.ann.setHiddenLayers(HiddenLayers); 
		
		Evaluation CV = new Evaluation(this.encodedData);
		Random rand = new Random(1);
		CV.crossValidateModel(this.ann, this.encodedData, foldsNumber, rand);
		
		System.out.println("For numeric Attributes: ");
		printResults(CV, foldsNumber);
	}
	
	public void CrossValidateNominal(int foldsNumber, 
			double momentum, double learningRate, int learningSteps, String HiddenLayers)
			throws Exception {
		
		NumericToNominal NtoN = new NumericToNominal();
        NtoN.setInputFormat(this.encodedData);
        Instances nominalData = Filter.useFilter(this.encodedData, NtoN);
		
		this.ann.setLearningRate(learningRate);
		this.ann.setMomentum(momentum);
		this.ann.setTrainingTime(learningSteps);
		this.ann.setHiddenLayers(HiddenLayers); 
        
        Evaluation CV = new Evaluation(nominalData);
		Random rand = new Random(1);
		CV.crossValidateModel(this.ann, nominalData, foldsNumber, rand);
		
		System.out.println("For nominal Attributes: ");
		printResults(CV, foldsNumber);
		System.out.println(CV.toMatrixString());
		printROC(CV);
	}
	
	/**
	 * Help function for printing the cross validation results.
	 * @param eval
	 * @throws Exception 
	 */
	
	private void printResults(Evaluation eval, int numOfFolds) throws Exception{
		System.out.println("The given encoder is "+this.encoder.getClass());
		System.out.println("Cross-Validatoin Output for the following parameters:");
		System.out.println();
		System.out.println("Number of Folds: "+numOfFolds);
		System.out.println("Momentum-term: "+this.ann.getMomentum());
		System.out.println("Learning Rate: "+this.ann.getLearningRate());
		System.out.println("Training Time: "+this.ann.getTrainingTime());
		System.out.println("Number of Hidden Layers: "+this.ann.getHiddenLayers());
		System.out.println();		
		System.out.println(eval.toSummaryString());
	}
	
	private void printROC(Evaluation eval) throws Exception{
		
		// generate curve
	    ThresholdCurve tc = new ThresholdCurve();
	    int classIndex = 0;//this.encodedData.numAttributes()-1; //THE LAST INDEX 
	    Instances result = tc.getCurve(eval.predictions(), classIndex);
	    System.out.println("Area under the ROC-Curve: "+tc.getROCArea(result));
	    
	     // plot curve
	    ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();
	    DecimalFormat f = new DecimalFormat("#0.000");
	    vmc.setROCString("(Area under ROC = " + f.format(tc.getROCArea(result))+")");
	    vmc.setName(result.relationName());
	    PlotData2D tempd = new PlotData2D(result);
	    tempd.setPlotName(result.relationName());
	    tempd.addInstanceNumberAttribute();
	    // specify which points are connected
	    boolean[] cp = new boolean[result.numInstances()];
	    for (int n = 1; n < cp.length; n++)
	    	cp[n] = true;
	    tempd.setConnectPoints(cp);
	    // add plot
	    vmc.addPlot(tempd);
	 
	    // display curve
	    String plotName = vmc.getName();
	    final javax.swing.JFrame jf =
	      new javax.swing.JFrame("Weka Classifier Visualize: "+plotName);
	    jf.setSize(500,400);
	    jf.getContentPane().setLayout(new BorderLayout());
	    jf.getContentPane().add(vmc, BorderLayout.CENTER);
	    jf.addWindowListener(new java.awt.event.WindowAdapter() {
	    	public void windowClosing(java.awt.event.WindowEvent e) {
	    		jf.dispose();
	    	}
	    });
	    jf.setVisible(true);
	}
	
	/**
	 * for Testing
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
//		SixCharEncoder encode = new SixCharEncoder();
		NineBitEncoder encode = new NineBitEncoder();
//		Instances data = parser.EncodeParser.readTrainingAndEncode("train_mini.txt", true, encode);
		Instances data = parser.EncodeParser.readTrainingAndEncode("project_training.txt", true, encode);
		data.setClassIndex(data.numAttributes()-1);
		
		//do the Validation
		Validation val = new Validation(data, encode);
//		val.CrossValidateNumeric(10, 0.9, 0.05, 1000, "9");
		val.CrossValidateNominal(10, 0.9, 0.05, 1000, "9");
	}

}
