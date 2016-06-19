package parser;
import java.io.File;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

public final class EncodeParser {

	private EncodeParser() {}
	
	public static Instances loadCSV(String filename) throws IOException {
		// CSV loader can also load tab separated data if specified
		CSVLoader loader = new CSVLoader();
		loader.setFieldSeparator("\t");
		loader.setNoHeaderRowPresent(false);
		loader.setSource(new File(filename));
		Instances data = loader.getDataSet();
		data.setRelationName("TrainingData");
		return data;
	}
	
	private static void createARFF(String filename, Instances data) throws IOException {
		ArffSaver saver = new ArffSaver();
		saver.setFile(new File(filename));
		saver.setInstances(data);
		saver.writeBatch();

	}
	
	public static void main(String[] args) throws IOException {
		Instances data = loadCSV("project_training_binary.txt");
		System.out.println(data.attribute(0).value(0));
		createARFF("encode_text.arff", data);
	}

}
