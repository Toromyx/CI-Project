package defaultPackage;

/**
 * 
 * @author Daria
 * 
 */

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;


import encoder.BlosumEncoder;
import encoder.BlosumEncoder.BlosumNum;
import parser.EncodeParser;
import encoder.BlosumInterface;
import encoder.Encoder;
import encoder.SixCharEncoder;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;

/**
 * Class for Cross-Validation and Evaluation of the given ANN and encoding
 *  
 */

public class Validation {	
	
	private Instances encodedData;
	private MultilayerPerceptron ann;
	private Encoder encoder;
		
	/**
	 * The constructor creates an object of the class Validation for a given data set and encoder. 
	 * The data set is already encoded by the function "parser.readInputAndEncode" and can be saved as a class field.
	 * An empty ANN will be created.    
	 * 
	 * @param data the training data set
	 * @param encoder chosen encoding type
	 * @throws Exception
	 */
	
	public Validation(Instances data, Encoder encoder) throws Exception{
		
		this.encodedData = data;
		encodedData.setClassIndex(encodedData.numAttributes()-1);
		this.ann = new MultilayerPerceptron();
		this.encoder=encoder;
	}
	
	/**
	 * Cross-Validation of the ANN with given backpropagation parameters for network generation and the encoded data set.
	 * The attributes of the validated data set are real numbers, so their type in Weka is NUMERIC. 
	 * The function is used for IC50 based prediction. 
	 * 
	 * @param foldsNumber
	 * @param momentum
	 * @param learningRate
	 * @param learningSteps
	 * @param HiddenLayers
	 * @throws Exception
	 */
	
	public void CrossValidateNumeric(int foldsNumber, 
			double momentum, double learningRate, int learningSteps, String HiddenLayers)
			throws Exception {
		
		//set network parameters 
		this.ann.setLearningRate(learningRate);
		this.ann.setMomentum(momentum);
		this.ann.setTrainingTime(learningSteps);
		this.ann.setHiddenLayers(HiddenLayers); 
		
		//do validation 
		Evaluation CV = new Evaluation(this.encodedData);
		Random rand = new Random(1);
		CV.crossValidateModel(this.ann, this.encodedData, foldsNumber, rand);
		
		//print results
		System.out.println("For numeric Attributes: ");
		printResults(CV, foldsNumber);
		
		calculateROCvaluesForIC50(CV);
	}
	
	/**
	 * Cross-Validation of the ANN with given backpropagation parameters for network generation and the encoded data set.
	 * The type of attributes in the encoded data set is changed to NOMINAL using Weka filter NumericToNominal. 
	 * Validation with nominal attributes allows creating of ROC-curves and confusion matrixes. 
	 * The function is used for binary prediction.
	 * 
	 * @param foldsNumber
	 * @param momentum
	 * @param learningRate
	 * @param learningSteps
	 * @param HiddenLayers
	 * @throws Exception
	 */
	
	public void CrossValidateNominal(int foldsNumber, 
			double momentum, double learningRate, int learningSteps, String HiddenLayers)
			throws Exception {
		
		//make attributes nominal
		NumericToNominal NtoN = new NumericToNominal();
        NtoN.setInputFormat(this.encodedData);
        Instances nominalData = Filter.useFilter(this.encodedData, NtoN);
		
        //set network parameters
		this.ann.setLearningRate(learningRate);
		this.ann.setMomentum(momentum);
		this.ann.setTrainingTime(learningSteps);
		this.ann.setHiddenLayers(HiddenLayers); 
        
		//do validation
        Evaluation CV = new Evaluation(nominalData);
		Random rand = new Random(1);
		CV.crossValidateModel(this.ann, nominalData, foldsNumber, rand);
		
		//print results
		System.out.println("For nominal Attributes: ");
		printResults(CV, foldsNumber);
		System.out.println(CV.toMatrixString());
		printROC(CV);
	}
	
	/**
	 * Help function for printing the cross validation results and given network parameters.
	 * 
	 * @param eval Cross-Validation of the encoded data and given ANN
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
	
	/**
	 * The function for creating and plotting the ROC-curve using the Weka-GUI and JavaFX.
	 * Can be used only in the function CrossValidateNominal.
	 *  
	 * @param eval Cross-Validation for encoded data and given ANN
	 * @throws Exception
	 */
	
	private void printROC(Evaluation eval) throws Exception{
		
		// generate curve
	    ThresholdCurve tc = new ThresholdCurve();
	    int classIndex = 0;
	    Instances result = tc.getCurve(eval.predictions(), classIndex);
	    System.out.println(result);
	    System.out.println("Area under the ROC-Curve: "+ThresholdCurve.getROCArea(result));
	    
	    // plot curve
	    ThresholdVisualizePanel tvp = new ThresholdVisualizePanel();
	    DecimalFormat df = new DecimalFormat("#0.000");
	    tvp.setROCString("(Area under ROC = " + df.format(ThresholdCurve.getROCArea(result))+")");
	    tvp.setName(result.relationName());
	    
	    PlotData2D tempd = new PlotData2D(result);
	    tempd.setPlotName(result.relationName());
	    tempd.addInstanceNumberAttribute();
	    
	    boolean[] cp = new boolean[result.numInstances()];
	    for (int n = 1; n < cp.length; n++)
	    	cp[n] = true;
	    tempd.setConnectPoints(cp);
	    tvp.addPlot(tempd);
	 
	    // display curve
	    String plotName = tvp.getName();
	    final javax.swing.JFrame jf =
	      new javax.swing.JFrame("Weka Classifier Visualize: "+plotName);
	    jf.setSize(600,500);
	    jf.getContentPane().setLayout(new BorderLayout());
	    jf.getContentPane().add(tvp, BorderLayout.CENTER);
	    jf.addWindowListener(new java.awt.event.WindowAdapter() {
	    	public void windowClosing(java.awt.event.WindowEvent e) {
	    		jf.dispose();
	    	}
	    });
	    jf.setVisible(true);
	}
	
	/**
	 * The function calculates false positive rate and true positive rate for every threshold value 
	 * for generating of the ROC curve.
	 * 
	 * @param eval
	 * @throws Exception
	 */
	
	private void calculateROCvaluesForIC50(Evaluation eval) throws Exception{
		
		ArrayList<Prediction> predictions = eval.predictions();
		ArrayList<Double> threshold = getThresholds(eval);
		
		int tp = 0; // true positive
		int tn = 0; // true negative
		int fn = 0; // false negative
		int fp = 0; // false positive
		
		double[] falsePositiveRate = new double[threshold.size()];
		double[] truePositiveRate = new double[threshold.size()];
		
		for(int j=0; j<threshold.size(); j++){
			for (int i = 0; i < predictions.size(); i++) {
			
				if(predictions.get(i).actual()<threshold.get(j) && predictions.get(i).predicted()<threshold.get(j))
					tp++;
				if(predictions.get(i).actual()>=threshold.get(j) && predictions.get(i).predicted()>=threshold.get(j))
					tn++;
				if(predictions.get(i).actual()<threshold.get(j) && predictions.get(i).predicted()>=threshold.get(j))
					fn++;
				if(predictions.get(i).actual()>=threshold.get(j) && predictions.get(i).predicted()<threshold.get(j))
					fp++;
			}
			
			// to avoid devision by zero 
			if((tn+fp)==0 || (tp+fn)==0 ){
				falsePositiveRate[j] = 0;
				truePositiveRate[j] = 0;
			}else{
				falsePositiveRate[j] = (double)fp/(tn+fp);
				truePositiveRate[j] = (double)tp/(tp+fn);
			}
			
			// reset for the next threshold
			tp=0; tn=0;
			fp=0; fn=0;
		}
		
		// print the results
		System.out.println("coordinates of all the poits of the ROC-curve: ");
		String s;	// for output format
				
		System.out.println("falsePositiveRate");
		for(int i = 0; i<falsePositiveRate.length; i++){
			s = String.format(Locale.US, "%.3f", falsePositiveRate[i]);
			System.out.print(" "+s+" , ");
		}
		System.out.println();
		
		System.out.println("truePositiveRate");
		for(int i = 0; i<truePositiveRate.length; i++){
			s = String.format(Locale.US, "%.3f", truePositiveRate[i]);
			System.out.print(" "+s+" , ");
		}
		System.out.println();
		
	}

	/**
	 * Creating a list of all possible thresholds for a given evaluation.
	 * The thresholds are all possible IC50 values. 
	 * 
	 * @param eval
	 * @return
	 */
	
	private ArrayList<Double> getThresholds(Evaluation eval){
		
		ArrayList<Prediction> predictions = eval.predictions();
		ArrayList<Double> list = new ArrayList<Double>();
		
		for (Prediction i : predictions) {
			if (!inthelist(i.actual(), list)) list.add(i.actual());
			if (!inthelist(i.predicted(), list)) list.add(i.predicted());
		}
		
		return list;
	}
	
	/**
	 * The function checks if the number x presents in the list   
	 * 
	 * @param x double number 
	 * @param list ArrayList of Doubles
	 * @return
	 */
	
	private boolean inthelist(double x, ArrayList<Double> list){
		for (double i : list) {
			if (x == i) return true;
		}
		return false;
	}
	
	/**
	 * for testing of the validation functions 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// choose encoder 
		SixCharEncoder encode = new SixCharEncoder();
//		NineBitEncoder encode = new NineBitEncoder();
		// for binary prediction
		Instances data1 = parser.EncodeParser.readTrainingAndEncode("project_training.txt", true, encode);
		data1.setClassIndex(data1.numAttributes()-1);
		
		Validation val1 = new Validation(data1, encode);
		val1.CrossValidateNominal(10, 0.9, 0.05, 1000, "5");
		
		// for IC50 prediction
		Instances data2 = parser.EncodeParser.readTrainingAndEncode("project_training.txt", false, encode);
		data2.setClassIndex(data2.numAttributes()-1);
		
		Validation val2 = new Validation(data2, encode);
		val2.CrossValidateNumeric(10, 0.9, 0.05, 1000, "5");
		
		// validation for every BLOSUM matrix
		for (int bNum=BlosumEncoder.blosumNums.length-1; bNum>=0; bNum--) {
			System.out.println("Validating Blosum"+BlosumEncoder.blosumNums[bNum]+" encoding.");
			BlosumEncoder encoder = new BlosumEncoder(BlosumEncoder.blosumNums[bNum]);
			Instances data = EncodeParser.readTrainingAndEncode("project_training.txt", false, encoder);
			data.setClassIndex(data.numAttributes()-1);
			Validation val = new Validation(data, encoder);
			val.CrossValidateNumeric(10, 0.9, 0.05, 1000, "20");
		}
	}

}
