
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import encoder.NineBitEncoder;
import encoder.BlosumEncoder;
import encoder.SixBitEncoder;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class Main {

	public static void main(String[] args) throws IOException {
		// load CSV
	    Instances data = loadCSV("project_training.txt");
	    data.setRelationName("TrainingData");
	    
	  //create an Encoder with user interface 
	    System.out.println("Please choose the encouding type: ");
	    System.out.println("press 1 for 9-bit-encoding ");
	    System.out.println("press 2 for 6-bit-encoding ");
	    System.out.println("press 3 for BLOSUM-encoding ");
	    Scanner scanner = new Scanner(System.in);
        String encodingType = scanner.nextLine();
        
        switch(encodingType){
        case "1": 
        	NineBitEncoder encoder1 = new NineBitEncoder();
        	encoder1.encodeAll(data);
        	break;
        case "2": 
        	SixBitEncoder encoder2 = new SixBitEncoder();
        	encoder2.encodeAll(data);
        	break;
        case "3": 
        	System.out.println("Please choose the Number of the Blosum Matrix");
            String blosumType = scanner.nextLine();           
        	BlosumEncoder encoder3 = new BlosumEncoder(Integer.parseInt(blosumType));
        	encoder3.encodeAll(data);	
        	break;
        default:
        	System.out.println("Invalid encoder type!");
        	break;
        }
        scanner.close();
        System.out.println();
	    
	    System.out.println(data.toSummaryString());
	}
	
	private static Instances loadCSV(String file) throws IOException {
		CSVLoader loader = new CSVLoader();
	    loader.setFieldSeparator("\t");
	    loader.setSource(new File(file));
	    Instances data = loader.getDataSet();
	  	return data;
	}

}
