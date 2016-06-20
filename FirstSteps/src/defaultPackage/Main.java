package defaultPackage;

import java.io.IOException;

import neuralNetworks.NeuralNetwork;
import parser.EncodeParser;
import weka.core.Instances;
/**
 * 
 * @author Friederike
 *
 */
public class Main {


	private static EncodeParser parser;
	private static Instances predictData;

	// Arguments are given in the following order: testdata, Encoding, ANN,
	// output filename
	public static void main(String[] args) throws IOException {
		// testdata need to be read in
		// encoded
		// add '?' from arff maybe
		// load proper ANN and execute it with the testdata
		// write results to outputfilename
		if (args[0].equals("help")) {
			printHelp();
		} else {
			try {
				//TODO???????
				parser = new EncodeParser(){};
				
				//process input
				predictData = initializeData(args[0], args[1]);
				
				//generate ANN + write the return stuff to file,when know how parser works
				applyANN(args[2],args[3],predictData);
				
			} catch (Exception e) {
				System.err.println("Your input could not be processed.");
			}
		}

	}


	private static void printHelp() {
		System.out.println("Please give the input in the following order: testdata encoding ann output-filename");
		System.out.println(
				"encoding: 6bit \n" + "	  9bit \n" + " 	  blosumX: X=[30,35,...,60,62,65,70,75,...,90,100]");
		System.out.println("ann: IC50 \n" + "     Binary \n" + "     Combined");
	}

	private static Instances initializeData(String predictData, String encoding) throws IOException {
//			Instances data = 
			
			return null;
	}
	
	private static Instances applyANN(String encoding, String annVariable, Instances data) throws Exception{
		String chosenAnn = chooseANN(encoding, annVariable);
		NeuralNetwork ann = new NeuralNetwork(chosenAnn);
		return ann.classifyData(data);
	}
	
	private static String chooseANN(String encoding, String annName){
		if(encoding.equals("6bit")){
			if(annName.equals("IC50")){
				return "6bitIC50";
			}
			if(annName.equals("Binary")){
				return "6bitBinary";
			}
			if(annName.equals("Combined")){
				return "6bitCombined";
			}
			
		}
		if(encoding.equals("9bit")){
			if(annName.equals("IC50")){
				return "9bitIC50";
			}
			if(annName.equals("Binary")){
				return "9bitBinary";
			}
			if(annName.equals("Combined")){
				return "9bitCombined";
			}
			
		}
		if(encoding.equals("blosum30")){
			if(annName.equals("IC50")){
				return "blosum30IC50";
			}
			if(annName.equals("Binary")){
				return "blosum30Binary";
			}
			if(annName.equals("Combined")){
				return "blosum30Combined";
			}
			
		}
		if(encoding.equals("blosum35")){
			if(annName.equals("IC50")){
				return "blosum35IC50";
			}
			if(annName.equals("Binary")){
				return "blosum35Binary";
			}
			if(annName.equals("Combined")){
				return "blosum35Combined";
			}
			
		}
		if(encoding.equals("blosum40")){
			if(annName.equals("IC50")){
				return "blosum40IC50";
			}
			if(annName.equals("Binary")){
				return "blosum40Binary";
			}
			if(annName.equals("Combined")){
				return "blosum40Combined";
			}
			
		}
		if(encoding.equals("blosum45")){
			if(annName.equals("IC50")){
				return "blosum45IC50";
			}
			if(annName.equals("Binary")){
				return "blosum45Binary";
			}
			if(annName.equals("Combined")){
				return "blosum45Combined";
			}
			
		}
		if(encoding.equals("blosum50")){
			if(annName.equals("IC50")){
				return "blosum50IC50";
			}
			if(annName.equals("Binary")){
				return "blosum50Binary";
			}
			if(annName.equals("Combined")){
				return "blosum50Combined";
			}
			
		}
		if(encoding.equals("blosum55")){
			if(annName.equals("IC50")){
				return "blosum55IC50";
			}
			if(annName.equals("Binary")){
				return "blosum55Binary";
			}
			if(annName.equals("Combined")){
				return "blosum55Combined";
			}
			
		}
		if(encoding.equals("blosum60")){
			if(annName.equals("IC50")){
				return "blosum60IC50";
			}
			if(annName.equals("Binary")){
				return "blosum60Binary";
			}
			if(annName.equals("Combined")){
				return "blosum60Combined";
			}
			
		}
		if(encoding.equals("blosum62")){
			if(annName.equals("IC50")){
				return "blosum62IC50";
			}
			if(annName.equals("Binary")){
				return "blosum62Binary";
			}
			if(annName.equals("Combined")){
				return "blosum62Combined";
			}
			
		}
		if(encoding.equals("blosum65")){
			if(annName.equals("IC50")){
				return "blosum65IC50";
			}
			if(annName.equals("Binary")){
				return "blosum65Binary";
			}
			if(annName.equals("Combined")){
				return "blosum65Combined";
			}
			
		}
		if(encoding.equals("blosum70")){
			if(annName.equals("IC50")){
				return "blosum70IC50";
			}
			if(annName.equals("Binary")){
				return "blosum70Binary";
			}
			if(annName.equals("Combined")){
				return "blosum70Combined";
			}
			
		}
		if(encoding.equals("blosum75")){
			if(annName.equals("IC50")){
				return "blosum75IC50";
			}
			if(annName.equals("Binary")){
				return "blosum75Binary";
			}
			if(annName.equals("Combined")){
				return "blosum75Combined";
			}
			
		}
		if(encoding.equals("blosum80")){
			if(annName.equals("IC50")){
				return "blosum80IC50";
			}
			if(annName.equals("Binary")){
				return "blosum80Binary";
			}
			if(annName.equals("Combined")){
				return "blosum80Combined";
			}
			
		}
		if(encoding.equals("blosum85")){
			if(annName.equals("IC50")){
				return "blosum85IC50";
			}
			if(annName.equals("Binary")){
				return "blosum85Binary";
			}
			if(annName.equals("Combined")){
				return "blosum85Combined";
			}
			
		}
		if(encoding.equals("blosum90")){
			if(annName.equals("IC50")){
				return "blosum90IC50";
			}
			if(annName.equals("Binary")){
				return "blosum90Binary";
			}
			if(annName.equals("Combined")){
				return "blosum90Combined";
			}
			
		}
		if(encoding.equals("blosum100")){
			if(annName.equals("IC50")){
				return "blosum100IC50";
			}
			if(annName.equals("Binary")){
				return "blosum100Binary";
			}
			if(annName.equals("Combined")){
				return "blosum100Combined";
			}
			
		}
		
		return null;
	}
	
	private static void validate(){
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
//			ann.setGUI(true);
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
}
