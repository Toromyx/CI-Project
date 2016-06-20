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
	public static Instances readAndEncode(String sourcefile, boolean trainingValueIsClassIndex, Encoder enc) throws IOException {
		// TODO truncate file
		Instances data = readTrainingCSV(sourcefile, trainingValueIsClassIndex);		
		return enc.encodeAll(data);	 // includes class labels (tarbet values)
	}
	
	/**
	 * reads the txt given by the tutors and decides if to use the class index or ic50 value for training
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
	
	public static void main(String[] args) throws IOException {
		System.out.println(readAndEncode("output.txt", true, new BlosumEncoder()));
	}

}
