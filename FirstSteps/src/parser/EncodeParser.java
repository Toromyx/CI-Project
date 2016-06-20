package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import encoder.BlosumEncoder;
import encoder.Encoder;
import encoder.NineBitEncoder;
import encoder.SixCharEncoder;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;

public interface EncodeParser {

	/**
	 * This method reads a file in the csv format given by the tutors and returns the data encoded inside an instance
	 * which can be used on a neural network. This means the Instances include class labels.
	 * @param sourcefile the filename of the sourcefile in the given format
	 * @param enc the Encoder used to encode the data
	 * @return the encoded data in form of an Instances object
	 * @throws IOException 
	 */
	public static Instances readTrainingAndEncode(String filename, boolean trainingValueIsClassIndex, Encoder enc) throws IOException {
		Instances data = readTrainingCSV(filename, trainingValueIsClassIndex);		
		return enc.encodeAll(data);	 // includes class labels (tarbet values)
	}
	
	/**
	 * SHOULDN'T BE USED BY OTHER CLASSES
	 * reads the txt given by the tutors and decides if to use the class index or ic50 value for training. No encoding takes place!
	 * @param filename the location an filename of the training data in form  a txt
	 * @param trainingValueColIndex true = the binary class index should be used for training, false = the numeric ic50 value should be used
	 * @return the data in form of an instances object with the class index (training value) as last attribute
	 * @throws IOException
	 */
	public static Instances readTrainingCSV(String filename, boolean trainingValueIsClassIndex) throws IOException {
		CSVLoader loader = new CSVLoader();
		loader.setFieldSeparator("\t");
		loader.setNoHeaderRowPresent(false);
		loader.setSource(new File(filename));
		Instances data = loader.getDataSet();
		if(trainingValueIsClassIndex) {
			data.deleteAttributeAt(1);
		} else {
			data.deleteAttributeAt(2);
		}
		data.setRelationName("TrainingData");
		data.setClassIndex(data.numAttributes() - 1);
		return data;
	}
	
	/**
	 * ready the input file, in the format given by the tutors, adds class labels (target values)
	 * and encodes the input with the desired Encoder
	 * @param filename the location and filename of the source input file
	 * @param enc the desired encoder
	 * @return the input data as instances object with class labels (target values), which have missing values (not determined yet)
	 * @throws IOException
	 */
	public static Instances readInputAndEncode(String filename, Encoder enc) throws IOException {
		Instances data = readInputCSV(filename);		
		return enc.encodeAll(data);
	}
	
	/**
	 * SHOULDN'T BE USED BY OTHER CLASSES
	 * just reads the input file and transforms it into an Instances object with missing (?) class labels
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Instances readInputCSV(String filename) throws IOException {
		CSVLoader loader = new CSVLoader();
		loader.setNoHeaderRowPresent(true);
		loader.setSource(new File(filename));
		Instances data = loader.getDataSet();
		data.setRelationName("InputData");
		
		// add class index
		data.insertAttributeAt(new Attribute("targetValue"), data.numAttributes());
		
		data.setClassIndex(data.numAttributes() - 1);
		return data;	
	}
	
	/**
	 * writes the output of an ANN (being an instances object) into the format given by the tutors.
	 * Needs the original input (same format) because there is no decoding
	 * @param outputfile the location and filename to write the output file to
	 * @param inputfile the location and filename to read the original sequences from, needs to have same length!
	 * @param output the output of the ANN
	 * @throws IOException
	 */
	public static void writeOutput(String outputfile, String inputfile, Instances output) throws IOException {
		CSVSaver saver = new CSVSaver();
		saver.setFile(new File(outputfile));
		
		Instances inputInst = readInputCSV(inputfile);
		
		Instances mergedInst = new Instances(inputInst, 0, inputInst.size());
		for(int currInst=0; currInst<mergedInst.size(); currInst++) {
			mergedInst.instance(currInst).setClassValue(output.instance(currInst).classValue());
		}
		
		saver.setInstances(mergedInst);
		saver.setFieldSeparator("\t");
		saver.writeBatch();
	}
	
	public static void main(String[] args) throws IOException {
		
	}

}
