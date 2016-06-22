package defaultPackage;

import java.io.IOException;

import encoder.BlosumEncoder;
import encoder.Encoder;
import encoder.NineBitEncoder;
import encoder.SixCharEncoder;
import neuralNetworks.NeuralNetwork;
import parser.EncodeParser;
import weka.core.Instances;

/**
 * 
 * @author Friederike
 *This class handles the user input. It initializes all instances: encoder, data, loads proper ANN, applies it
 * and stores predicted data in a file
 */
public class UserInputProcessor implements EncodeParser {

	private String inputFilename;
	private String encoding;
	private String annType;
	private String outputFilename;
	private Encoder encoder;
	private Instances predictData;

	/**
	 * 
	 * @param inputArgs 
	 */
	public UserInputProcessor(String inputFilename, String encoding, String annType, String outputFilename) {
		this.inputFilename = inputFilename;
		this.encoding = encoding;
		this.annType = annType;
		this.outputFilename = outputFilename;
	}

	/**
	 * Initializes Encoder according to provided encoding type 6Char, 9Bit, blosumX
	 */
	private void initializeEncoder() {
		if (encoding.equals("6Char")) {
			encoder = new SixCharEncoder();
		} else if (encoding.equals("9Bit")) {
			encoder = new NineBitEncoder();
		} else if (encoding.equals("blosum30")) {
			encoder = new BlosumEncoder(30);
		} else if (encoding.equals("blosum35")) {
			encoder = new BlosumEncoder(35);
		} else if (encoding.equals("blosum40")) {
			encoder = new BlosumEncoder(40);
		} else if (encoding.equals("blosum45")) {
			encoder = new BlosumEncoder(45);
		} else if (encoding.equals("blosum50")) {
			encoder = new BlosumEncoder(50);
		} else if (encoding.equals("blosum55")) {
			encoder = new BlosumEncoder(55);
		} else if (encoding.equals("blosum60")) {
			encoder = new BlosumEncoder(60);
		} else if (encoding.equals("blosum62")) {
			encoder = new BlosumEncoder(62);
		} else if (encoding.equals("blosum65")) {
			encoder = new BlosumEncoder(65);
		} else if (encoding.equals("blosum70")) {
			encoder = new BlosumEncoder(70);
		} else if (encoding.equals("blosum75")) {
			encoder = new BlosumEncoder(75);
		} else if (encoding.equals("blosum80")) {
			encoder = new BlosumEncoder(80);
		} else if (encoding.equals("blosum85")) {
			encoder = new BlosumEncoder(85);
		} else if (encoding.equals("blosum90")) {
			encoder = new BlosumEncoder(90);
		} else if (encoding.equals("blosum30")) {
			encoder = new BlosumEncoder(30);
		} else if (encoding.equals("blosum30")) {
			encoder = new BlosumEncoder(30);
		} else if (encoding.equals("blosum100")) {
			encoder = new BlosumEncoder(100);
		}
	}
	
	/**
	 * @return encoded data as Instances 
	 * @throws IOException
	 */
	private Instances initializeData() throws IOException {
		return predictData = EncodeParser.readInputAndEncode(inputFilename, encoder);
	}

	/**
	 * According to the encoding and the ann name the method returns the
	 * filename of the ANN that should be used
	 * 
	 * @param encoding
	 * @param annName
	 * @return
	 */
	private String chooseANN() {

		if (encoding.equals("6Char")) {
			if (annType.equals("IC50")) {
				return "6CharIC50";
			}else if (annType.equals("Binary")) {
				return "6CharBinary";
			}else if (annType.equals("Combined")) {
				return "6CharCombined";
			}
		}else if(encoding.equals("9Bit")) {
			if (annType.equals("IC50")) {
				return "9BitIC50";
			}else if (annType.equals("Binary")) {
				return "9BitBinary";
			}else if(annType.equals("Combined")) {
				return "9BitCombined";
			}
		}else if (encoding.equals("blosum30")) {
			if (annType.equals("IC50")) {
				return "blosum30IC50";
			}else if (annType.equals("Binary")) {
				return "blosum30Binary";
			}else if (annType.equals("Combined")) {
				return "blosum30Combined";
			}
		} else if (encoding.equals("blosum35")) {
			if (annType.equals("IC50")) {
				return "blosum35IC50";
			}else if (annType.equals("Binary")) {
				return "blosum35Binary";
			}else if (annType.equals("Combined")) {
				return "blosum35Combined";
			}
		}else if (encoding.equals("blosum40")) {
			if (annType.equals("IC50")) {
				return "blosum40IC50";
			}else if (annType.equals("Binary")) {
				return "blosum40Binary";
			}else if (annType.equals("Combined")) {
				return "blosum40Combined";
			}
		}else if (encoding.equals("blosum45")) {
			if (annType.equals("IC50")) {
				return "blosum45IC50";
			}else if (annType.equals("Binary")) {
				return "blosum45Binary";
			}else if (annType.equals("Combined")) {
				return "blosum45Combined";
			}
		}else if (encoding.equals("blosum50")) {
			if (annType.equals("IC50")) {
				return "blosum50IC50";
			}else if (annType.equals("Binary")) {
				return "blosum50Binary";
			}else if (annType.equals("Combined")) {
				return "blosum50Combined";
			}
		}else if (encoding.equals("blosum55")) {
			if (annType.equals("IC50")) {
				return "blosum55IC50";
			}else if (annType.equals("Binary")) {
				return "blosum55Binary";
			}else if (annType.equals("Combined")) {
				return "blosum55Combined";
			}
		}else if (encoding.equals("blosum60")) {
			if (annType.equals("IC50")) {
				return "blosum60IC50";
			}else if (annType.equals("Binary")) {
				return "blosum60Binary";
			}else if (annType.equals("Combined")) {
				return "blosum60Combined";
			}
		}else if (encoding.equals("blosum62")) {
			if (annType.equals("IC50")) {
				return "blosum62IC50";
			}else if (annType.equals("Binary")) {
				return "blosum62Binary";
			}else if (annType.equals("Combined")) {
				return "blosum62Combined";
			}
		}else if (encoding.equals("blosum65")) {
			if (annType.equals("IC50")) {
				return "blosum65IC50";
			}else if (annType.equals("Binary")) {
				return "blosum65Binary";
			}else if (annType.equals("Combined")) {
				return "blosum65Combined";
			}
		}else if (encoding.equals("blosum70")) {
			if (annType.equals("IC50")) {
				return "blosum70IC50";
			}else if (annType.equals("Binary")) {
				return "blosum70Binary";
			}else if (annType.equals("Combined")) {
				return "blosum70Combined";
			}
		}else if (encoding.equals("blosum75")) {
			if (annType.equals("IC50")) {
				return "blosum75IC50";
			}else if (annType.equals("Binary")) {
				return "blosum75Binary";
			}else if (annType.equals("Combined")) {
				return "blosum75Combined";
			}
		}else if (encoding.equals("blosum80")) {
			if (annType.equals("IC50")) {
				return "blosum80IC50";
			}else if (annType.equals("Binary")) {
				return "blosum80Binary";
			}else if (annType.equals("Combined")) {
				return "blosum80Combined";
			}
		}else if (encoding.equals("blosum85")) {
			if (annType.equals("IC50")) {
				return "blosum85IC50";
			}else if (annType.equals("Binary")) {
				return "blosum85Binary";
			}else if (annType.equals("Combined")) {
				return "blosum85Combined";
			}
		}else if (encoding.equals("blosum90")) {
			if (annType.equals("IC50")) {
				return "blosum90IC50";
			}else if (annType.equals("Binary")) {
				return "blosum90Binary";
			}else if (annType.equals("Combined")) {
				return "blosum90Combined";
			}
		}else if (encoding.equals("blosum100")) {
			if (annType.equals("IC50")) {
				return "blosum100IC50";
			}else if (annType.equals("Binary")) {
				return "blosum100Binary";
			}else if (annType.equals("Combined")) {
				return "blosum100Combined";
			}
		}

		return null;
	}

	/**
	 * 
	 * @param chosenAnn
	 * @return data as predicted by the ANN
	 * @throws Exception
	 */
	private Instances applyANN(String chosenAnn) throws Exception {
		String annName = "ModelANNs/" + chosenAnn;
		System.out.println(annName);
		NeuralNetwork ann = new NeuralNetwork(annName);
		return ann.classifyData(predictData);
	}
	
	/**
	 * @param output
	 * @throws IOException
	 */
	private void storeOutput(Instances output) throws IOException{
		EncodeParser.writeOutput(outputFilename, inputFilename, output);
	}
	
	/**
	 * Runs class, should be called to execute all methods.
	 * @throws Exception
	 */
	public void run() throws Exception{
		initializeEncoder();
		initializeData();
		storeOutput(applyANN(chooseANN()));
	}
	
	//TEST: executed as expected
	/*
	public static void main(String[] args) {
		String[] blub = new String[4];
		blub[0] = "test_input.txt";
		blub[1] = "6Char";
		blub[2] = "Binary";
		blub[3] = "blubblub.txt";
		UserInputProcessor test = new UserInputProcessor(blub[0],blub[1],blub[2],blub[3]);
		test.initializeEncoder();
		try {
			test.initializeData();
		} catch (IOException e1) {
			System.out.println("Error in initializeData");
			e1.printStackTrace();
		}
		String chosenAnn = test.chooseANN();
		try {
			Instances output = test.applyANN(chosenAnn);
			test.storeOutput(output);
		} catch (Exception e) {
			System.out.println("Error in applyANN or storeOutput");

			e.printStackTrace();
		}
		
	}*/

}
