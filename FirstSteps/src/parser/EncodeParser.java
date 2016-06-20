package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import encoder.BlosumEncoder;
import encoder.Encoder;
import encoder.NineBitEncoder;
import encoder.SixCharEncoder;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

public interface EncodeParser {

	/**
	 * This method reads a file in the csv format given by the tutors and returns the data encoded inside an instance
	 * which can be used on a neural network. This means the Instances include class labels.
	 * @param sourcefile the filename of the sourcefile in the given format
	 * @param enc the Encoder used to encode the data
	 * @return the encoded data in form of an Instances object
	 * @throws IOException 
	 */
	public static Instances readAndEncode(String sourcefile, Encoder enc) throws IOException {
		// TODO truncate file
		Instances data = loadCSV(sourcefile);		
		return enc.encodeAll(data);	 // includes class labels (tarbet values)
	}
	
	public static Instances loadCSV(String filename) throws IOException {
		CSVLoader loader = new CSVLoader();
		loader.setFieldSeparator("\t");
		loader.setNoHeaderRowPresent(false);
		loader.setSource(new File(filename));
		Instances data = loader.getDataSet();
		data.setRelationName("TrainingData");
		return data;
	}
	
	public static void createARFF(String filename, Instances data) throws IOException {
		ArffSaver saver = new ArffSaver();
		saver.setFile(new File(filename));
		saver.setInstances(data);
		saver.writeBatch();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(readAndEncode("project_training_ic50.txt", new BlosumEncoder()));
	}

}
