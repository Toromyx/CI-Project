package neuralNetworks;
/**
 * @author Friederike
 */
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

public class NeuralNetwork {

	private MultilayerPerceptron ann;

	/**
	 * Generates a new ANN. Needs the filename of the model. Methods to classify your data with a given model are then provided.
	 * @param filename Proper filename such as Ic509bitAnn etc
	 * @throws Exception something went wrong with reading in the file
	 */
	public NeuralNetwork(String filename) throws Exception {
		ann = (MultilayerPerceptron) weka.core.SerializationHelper.read("filename");
	}

	/**
	 * Classifies each instance of a dataset
	 * @param testdata
	 * @return classified testdata set
	 */
	public Instances applyData(Instances testdata) {
		Instances output = new Instances(testdata, testdata.numInstances());
		
		for(int i = 0; i < testdata.numInstances(); i++){
			double label = -1;
			try {
				label = ann.classifyInstance(testdata.instance(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			output.instance(i).setClassValue(label);
		}
		return output;		
	}
	
	public void generateANN(Instances trainingsdata, String filename) {

		MultilayerPerceptron ann = new MultilayerPerceptron();
		ann.setLearningRate(0.1);
		ann.setMomentum(0.2);
		ann.setTrainingTime(2000);
		ann.setHiddenLayers("2");

		trainingsdata.setClassIndex(trainingsdata.numAttributes() - 1);

		try {
			ann.buildClassifier(trainingsdata);
		} catch (Exception e) {
			System.err.println("Classifier couldn't be build");
			e.printStackTrace();
		}

		try {
			weka.core.SerializationHelper.write(filename, ann);
		} catch (Exception e) {
			System.err.println("Classifier couldn't be stored");
			e.printStackTrace();
		}
	}
}
