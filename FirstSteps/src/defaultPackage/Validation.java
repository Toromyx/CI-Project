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
import java.util.Random;

import encoder.BlosumEncoder;
import encoder.BlosumEncoder.BlosumNum;
import parser.EncodeParser;
import encoder.BlosumInterface;
import encoder.Encoder;
import encoder.NineBitEncoder;
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
 * Class for Cross-Validation and Evaluation of the given ANN
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
	 * Cross-Validation of the ANN with given network parameters and the encoded data set.
	 * The attributes of the data set are real numbers, so their type in Weka is NUMERIC. 
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
		
		printROCforIC50(CV);
	}
	
	/**
	 * Cross-Validation of the ANN with given network parameters and the encoded data set.
	 * The type of attributes in the encoded data set is changed to NOMINAL using Weka filter NumericToNominal. 
	 * Nominal attributes allow creating of ROC-curves and confusion matrixes. 
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
	 * Help function for printing the cross validation results for given network parameters.
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
	    ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();
	    DecimalFormat df = new DecimalFormat("#0.000");
	    vmc.setROCString("(Area under ROC = " + df.format(ThresholdCurve.getROCArea(result))+")");
	    vmc.setName(result.relationName());
	    
	    PlotData2D tempd = new PlotData2D(result);
	    tempd.setPlotName(result.relationName());
	    tempd.addInstanceNumberAttribute();
	    
	    boolean[] cp = new boolean[result.numInstances()];
	    for (int n = 1; n < cp.length; n++)
	    	cp[n] = true;
	    tempd.setConnectPoints(cp);
	    vmc.addPlot(tempd);
	 
	    // display curve
	    String plotName = vmc.getName();
	    final javax.swing.JFrame jf =
	      new javax.swing.JFrame("Weka Classifier Visualize: "+plotName);
	    jf.setSize(600,500);
	    jf.getContentPane().setLayout(new BorderLayout());
	    jf.getContentPane().add(vmc, BorderLayout.CENTER);
	    jf.addWindowListener(new java.awt.event.WindowAdapter() {
	    	public void windowClosing(java.awt.event.WindowEvent e) {
	    		jf.dispose();
	    	}
	    });
	    jf.setVisible(true);
	}
	
	private void printROCforIC50(Evaluation eval) throws Exception{
		
		ArrayList<Prediction> a = eval.predictions();
		
		int tp = 0;
		int tn = 0;
		int fn = 0;
		int fp = 0;
		ArrayList<Double> threshold = gettreshoholds(eval);
		
		double[] falsePositiveRate = new double[threshold.size()];
		double[] truePositiveRate = new double[threshold.size()];
		
		for(int j=0; j<threshold.size(); j++){
			for (int i = 0; i < a.size(); i++) {
//				System.out.println("actual: "+a.get(i).actual());
//				System.out.println("predicted: "+a.get(i).predicted());
				//System.out.println("weight: "+a.get(i).weight());
			
				if(a.get(i).actual()>=threshold.get(j) && a.get(i).predicted()>=threshold.get(j))
					tp++;
				if(a.get(i).actual()<threshold.get(j) && a.get(i).predicted()<threshold.get(j))
					tn++;
				if(a.get(i).actual()>=threshold.get(j) && a.get(i).predicted()<threshold.get(j))
					fn++;
				if(a.get(i).actual()<threshold.get(j) && a.get(i).predicted()>=threshold.get(j))
					fp++;
			}
//			System.out.println("tp: "+tp);
//			System.out.println("tn: "+tn);
//			System.out.println("fp: "+fp);
//			System.out.println("fn: "+fn);
			if((tn+fp)>0) falsePositiveRate[j] = (double)fp/(tn+fp);
			else falsePositiveRate[j] = 0;
			
			if((tp+fn)>0) truePositiveRate[j] = (double)tp/(tp+fn);
			else truePositiveRate[j]=0;
			
			tp=0; tn=0;
			fp=0; fn=0;
		}
		
		//print
		System.out.println("coordinates of all the poits of the ROC-curve: ");
		System.out.println("falsePositiveRate");
		for(int i = 0; i<falsePositiveRate.length; i++){
			System.out.print(" "+falsePositiveRate[i]+" , ");
		}
		System.out.println();
		System.out.println("truePositiveRate");
		for(int i = 0; i<truePositiveRate.length; i++){
			System.out.print(" "+truePositiveRate[i]+" , ");
		}
		System.out.println();
			
	}
	
	private ArrayList<Double> gettreshoholds(Evaluation eval){
		ArrayList<Prediction> a = eval.predictions();
		ArrayList<Double> list = new ArrayList<Double>();
		
		for (Prediction temp : a) {
			if (!inthelist(temp.actual(), list)) list.add(temp.actual());
		}
		
		return list;
	}
	
	private boolean inthelist(double i, ArrayList<Double> list){
		for (double temp : list) {
			if (i ==temp) return true;
		}
		return false;
	}
	
	/**
	 * for Testing
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
//		SixCharEncoder encode = new SixCharEncoder();
//		BlosumEncoder encode = new BlosumEncoder();
//		NineBitEncoder encode = new NineBitEncoder();
//		
//		Instances data = parser.EncodeParser.readTrainingAndEncode("train_mini.txt", false, encode);
//		Instances data = parser.EncodeParser.readTrainingAndEncode("project_training.txt", false, encode);
//		data.setClassIndex(data.numAttributes()-1);
//		
//		//do the Validation
//		Validation val = new Validation(data, encode);
//		val.CrossValidateNumeric(10, 0.9, 0.05, 1000, "5");
//		val.CrossValidateNominal(10, 0.9, 0.05, 100, "5");
		
		for (int bNum=BlosumEncoder.blosumNums.length-1; bNum>=0; bNum--) {
			System.out.println("Validating Blosum"+BlosumEncoder.blosumNums[bNum]+" encoding.");
			BlosumEncoder encode = new BlosumEncoder(BlosumEncoder.blosumNums[bNum]);
			Instances data = EncodeParser.readTrainingAndEncode("project_training.txt", true, encode);
			data.setClassIndex(data.numAttributes()-1);
			Validation val = new Validation(data, encode);
			val.CrossValidateNominal(10, 0.9, 0.05, 1000, "20");
		}
	}

}
