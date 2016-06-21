package defaultPackage;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;
import weka.filters.unsupervised.attribute.NumericToNominal;

public class Validate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static void validate(Instances encodedData) throws Exception {
		// to make Filters work (because of the "Cannot handle numeric
		// class!"-Exception)
		NumericToNominal NtoN = new NumericToNominal();
		NtoN.setInputFormat(encodedData);
		encodedData = Filter.useFilter(encodedData, NtoN);

		// filter the Data to create 2 data-sets for the training and the
		// cross-validation
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
		// ann.setGUI(true);
		ann.setHiddenLayers("80,1");
		ann.buildClassifier(train);
		System.out.println();
		System.out.println("ANN is created");
		System.out.println();
		// System.out.println(ann.toString());

		System.out.println("Cross-Validatoin Output:");
		// create Evaluation for Cross-Validation
		Evaluation CV = new Evaluation(crossV);
		Random rand = new Random(1);
		CV.crossValidateModel(ann, crossV, 10, rand);
		System.out.println(CV.toSummaryString());

		// create Evaluation (now for train-set, just to make sure it works)
		Evaluation eval = new Evaluation(train);
		System.out.println();
		eval.evaluateModel(ann, train);
		System.out.println("Evaluation results (now for train-set, just to make sure it works):");
		System.out.println(eval.toSummaryString());
	}

}
