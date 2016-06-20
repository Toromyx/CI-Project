package defaultPackage;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import encoder.NineBitEncoder;
import encoder.BlosumEncoder;
import encoder.SixCharEncoder;
import neuralNetworks.NeuralNetwork;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;
import weka.filters.unsupervised.attribute.NumericToNominal;

public class Main {

	public static void main(String[] args) throws Exception {
		// load CSV
	    Instances data = loadCSV("project_training.txt");
	    data.setClassIndex(data.numAttributes()-1);
	    data.setRelationName("TrainingData");
	    
	    System.out.println("Data Info:");
	    System.out.println(data.toSummaryString());
	    
	    //create an Encoder with user interface 
	    System.out.println("Please choose the encoding type: ");
	    System.out.println("press 1 for 9-bit-encoding ");
	    System.out.println("press 2 for 6-bit-encoding ");
	    System.out.println("press 3 for BLOSUM-encoding ");
	    Scanner scanner = new Scanner(System.in);
        String encodingType = scanner.nextLine();
        
        Instances encodedData = null;
        
        switch(encodingType){
        case "1": 
        	NineBitEncoder encoder1 = new NineBitEncoder();
        	encodedData = encoder1.encodeAll(data);
        	break;
        case "2": 
        	SixCharEncoder encoder2 = new SixCharEncoder();
        	encodedData=encoder2.encodeAll(data);
        	break;
        case "3": 
        	System.out.println("Please choose the Number of the Blosum Matrix");
            String blosumType = scanner.nextLine();           
        	BlosumEncoder encoder3 = new BlosumEncoder(Integer.parseInt(blosumType));
        	encodedData=encoder3.encodeAll(data);	
        	break;
        default:
        	System.out.println("Invalid encoder type!");
        	break;
        }
        scanner.close();
        System.out.println();
        System.out.println("The first instance of the Data after encoding");
        System.out.println(encodedData.instance(0));
        System.out.println();
        
        //to make Filters work (because of the "Cannot handle numeric class!"-Exception)
        NumericToNominal NtoN = new NumericToNominal();
        NtoN.setInputFormat(encodedData);
        encodedData = Filter.useFilter(encodedData, NtoN);
        
        //filter the Data to create 2 data-sets for the training and the cross-validation
        Resample res = new Resample();
		res.setNoReplacement(true);
		res.setSampleSizePercent(80);
		res.setInputFormat(encodedData);
		
		Instances train = Filter.useFilter(encodedData, res);
		
		res.setInvertSelection(true);
		res.setInputFormat(encodedData);
		Instances crossV = Filter.useFilter(encodedData, res);
        
        // create ann with train
        MultilayerPerceptron ann = new MultilayerPerceptron();
		ann.setLearningRate(0.1);
		ann.setMomentum(0.9);
		ann.setTrainingTime(10); // sonst dauert ewig 
//		ann.setGUI(true);
		ann.setHiddenLayers("80,1");
		ann.buildClassifier(train);
		System.out.println();
		System.out.println("ANN is created");
		System.out.println();
		//System.out.println(ann.toString());
		
		System.out.println("Cross-Validatoin Output:");
		//create Evaluation for Cross-Validation
		Evaluation CV = new Evaluation(crossV);
		Random rand = new Random(1);
		CV.crossValidateModel(ann, crossV, 10, rand);
		System.out.println(CV.toSummaryString());
		
		//create Evaluation (now for train-set, just to make sure it works)
		Evaluation eval = new Evaluation(train);
		System.out.println();
		eval.evaluateModel(ann, train);
		System.out.println("Evaluation results (now for train-set, just to make sure it works):");
		System.out.println(eval.toSummaryString());
        
	}
	
	private static Instances loadCSV(String file) throws IOException {
		CSVLoader loader = new CSVLoader();
	    loader.setFieldSeparator("\t");
	    loader.setSource(new File(file));
	    Instances data = loader.getDataSet();
	  	return data;
	}

}
