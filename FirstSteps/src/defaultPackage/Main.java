package defaultPackage;

import java.io.IOException;

/**
  * 
  * @author Friederike
  *
  *Class runs the program. Userinput needs to be read in via console in the following order:
  * DATAFILENAME ENCODING ANN OUTPUTFILENAME
  * 
  * Else a help() function is displayed
  */
 
public class Main{


	/**
	 * Prints out all the options the user has
	 */
	private static void printHelp() {
		System.out.println("Please give the input in the following order: testdata-filename output-filename");
	}

	public static void main(String[] args) throws IOException {
		
		if (args[0].equals("help") || args.length != 2) {
			printHelp();
		} else {
			try {
				UserInputProcessor processor = new UserInputProcessor(args[0],"6Char","Binary",args[1]);
				processor.run();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Your input could not be processed.");
				printHelp();
			}
		}

	}
}