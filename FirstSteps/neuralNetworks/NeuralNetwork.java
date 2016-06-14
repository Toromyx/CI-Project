package neuralNetworks;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

public abstract class NeuralNetwork {
	
	public abstract Instances run();
	protected abstract void generateANN(Instances trainingsdata);
}
