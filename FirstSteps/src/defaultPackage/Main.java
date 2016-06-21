package defaultPackage;

/**
 * 
 * @author Friederike
 * 
 */
import java.io.IOException;

import encoder.BlosumEncoder;
import encoder.Encoder;
import encoder.SixCharEncoder;
import neuralNetworks.NeuralNetwork;
import parser.EncodeParser;
import weka.core.Instances;

/**
 * Class deals with the user input. The user can either enter help and get all
 * the options displayed. Otherwise the correct input parameters are:
 * DATAFILENAME ENCODING ANN OUTPUTFILENAME
 * 
 */
public class Main implements EncodeParser{

	private static Instances predictData;

	/**
	 * Prints out all the options the user has
	 */
	private static void printHelp() {
		System.out.println("Please give the input in the following order: testdata encoding ann output-filename");
		System.out.println(
				"encoding: 6bit \n" + "	  9bit \n" + " 	  blosumX: X=[30,35,...,60,62,65,70,75,...,90,100]");
		System.out.println("ann: IC50 \n" + "     Binary \n" + "     Combined");
	}

	// TODO not sure how to apply the encoderParser interface here
	private static Instances initializeData(String inputFile, String encoding) throws IOException {
		//auslagern 
		Encoder enc = new BlosumEncoder();
		predictData = EncodeParser.readInputAndEncode(inputFile, enc);
		return predictData;
	}

	/**
	 * Method applies the user data to an ANN, determined through the annNAme
	 * and the encoding
	 * 
	 * @param encoding
	 * @param annVariable
	 * @param data
	 * @return
	 * @throws Exception
	 */
	private static Instances applyANN(String encoding, String annName, Instances data) throws Exception {
		String chosenAnn = chooseANN(encoding, annName);
		System.out.println(chosenAnn);
		NeuralNetwork ann = new NeuralNetwork(chosenAnn);
		return ann.classifyData(data);
	}

	//TODO 6bit -> 6char
	/**
	 * According to the encoding and the ann name the method returns the
	 * filename of the ANN that should be used
	 * 
	 * @param encoding
	 * @param annName
	 * @return
	 */
	private static String chooseANN(String encoding, String annName) {
		return "Blosum50Binary";
		/*
		if (encoding.equals("6Char")) {
			if (annName.equals("IC50")) {
				return "6CharIC50";
			}
			if (annName.equals("Binary")) {
				return "6bitBinary";
			}
			if (annName.equals("Combined")) {
				return "6bitCombined";
			}

		}
		if (encoding.equals("9bit")) {
			if (annName.equals("IC50")) {
				return "9bitIC50";
			}
			if (annName.equals("Binary")) {
				return "9bitBinary";
			}
			if (annName.equals("Combined")) {
				return "9bitCombined";
			}

		}
		if (encoding.equals("blosum30")) {
			if (annName.equals("IC50")) {
				return "blosum30IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum30Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum30Combined";
			}

		}
		if (encoding.equals("blosum35")) {
			if (annName.equals("IC50")) {
				return "blosum35IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum35Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum35Combined";
			}

		}
		if (encoding.equals("blosum40")) {
			if (annName.equals("IC50")) {
				return "blosum40IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum40Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum40Combined";
			}

		}
		if (encoding.equals("blosum45")) {
			if (annName.equals("IC50")) {
				return "blosum45IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum45Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum45Combined";
			}

		}
		if (encoding.equals("blosum50")) {
			if (annName.equals("IC50")) {
				return "blosum50IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum50Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum50Combined";
			}

		}
		if (encoding.equals("blosum55")) {
			if (annName.equals("IC50")) {
				return "blosum55IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum55Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum55Combined";
			}

		}
		if (encoding.equals("blosum60")) {
			if (annName.equals("IC50")) {
				return "blosum60IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum60Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum60Combined";
			}

		}
		if (encoding.equals("blosum62")) {
			if (annName.equals("IC50")) {
				return "blosum62IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum62Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum62Combined";
			}

		}
		if (encoding.equals("blosum65")) {
			if (annName.equals("IC50")) {
				return "blosum65IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum65Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum65Combined";
			}

		}
		if (encoding.equals("blosum70")) {
			if (annName.equals("IC50")) {
				return "blosum70IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum70Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum70Combined";
			}

		}
		if (encoding.equals("blosum75")) {
			if (annName.equals("IC50")) {
				return "blosum75IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum75Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum75Combined";
			}

		}
		if (encoding.equals("blosum80")) {
			if (annName.equals("IC50")) {
				return "blosum80IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum80Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum80Combined";
			}

		}
		if (encoding.equals("blosum85")) {
			if (annName.equals("IC50")) {
				return "blosum85IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum85Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum85Combined";
			}

		}
		if (encoding.equals("blosum90")) {
			if (annName.equals("IC50")) {
				return "blosum90IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum90Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum90Combined";
			}

		}
		if (encoding.equals("blosum100")) {
			if (annName.equals("IC50")) {
				return "blosum100IC50";
			}
			if (annName.equals("Binary")) {
				return "blosum100Binary";
			}
			if (annName.equals("Combined")) {
				return "blosum100Combined";
			}

		}

		return null;*/
	}

	public static void main(String[] args) throws IOException {

		if (args[0].equals("help")) {
			printHelp();
		} else {
			try {
				predictData = initializeData(args[0], args[1]);
				Instances predictedData = applyANN(args[2], args[3], predictData);
				
				
				EncodeParser.writeOutput(args[3], args[0], predictedData);

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Your input could not be processed.");
			}
		}

	}
 }
