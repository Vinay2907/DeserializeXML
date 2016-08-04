package objComp.driver;

import objComp.fileOperations.FileProcessorImpl;
import objComp.fileOperations.FileProcessorSupport;
import objComp.fileOperations.LoggerDebug;
import objComp.util.PopulateObjectImp;
import objComp.util.PopulateObjectSupport;

/**
 * Descritpion - Holds the driver code</h1> The following should be read from command
 * line: input file name, output file name, and the value of NUM_ITERATIONS.,
 * The starting function of the program
 * <p>
 * 
 * @author Vinayak Subhash Pingale
 * @version 1.0
 * @since 04-02-2015
 */


public class Driver {

		/**
		 * The program will start from here.The command line argument will help the
		 * user to determine the input file entered and the output achieved , in
		 * addition to the debug value
		 * 
		 * @return Nothing.
		 */

		public static void main(String[] args) {

			String fileNameInput = null;
			String fileNameOutput = null;
			if (args.length != 3) {
				System.err
						.println("Please provide the command line arguments in build.xml properly. \n");
				System.exit(1);
			}
			try {
				if (args[0] == null || args[1] == null) {
					throw new NullPointerException(
							"The Value entered for Argument 0/1 is null or it is Empty");
				} else {
					if (args[0].endsWith(".txt")) {
						fileNameInput = args[0];
					} else {
						System.err
								.println("The value entered should end with .txt for the Input file name provided");
						System.exit(1);
					}
					int debugValue = Integer.parseInt(args[2]);
					if (debugValue > 4) {
						System.err
								.println("The value entered is greater than expected debug value");
						System.exit(1);
					} else {
						LoggerDebug.setDEBUG_VALUE(Integer.parseInt(args[2]));
					}
				}
			} catch (NullPointerException ex) {
				System.err.println("Usage:" + ex.getMessage());
				System.exit(1);
			} catch (NumberFormatException ex) {
				System.err
						.println("Usage: The value entered is not of Integer type : Debug value only accepts integer value");
				System.exit(1);
			}
			int i = 0;
			int Num_Iterations = Integer.parseInt(args[1]);
			PopulateObjectSupport populateObject = null;
			long startTime = System.currentTimeMillis();
			while (i != Num_Iterations) {
				FileProcessorSupport fileProcessorSupport = new FileProcessorImpl(
						fileNameInput, fileNameOutput);
				populateObject = new PopulateObjectImp(fileProcessorSupport);
				populateObject.deSerializeObjects();
				i++;
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Number of non-duplicate First objects: " + populateObject.countDuplicateFirst());
			System.out.println("Total Number of First objects: " + populateObject.totalFirst());
			System.out.println("Number of non-duplicate Second objects: " + populateObject.countDuplicateSecond());
			System.out.println("Total Number of Second objects: " + populateObject.totalSecond());
			long time2 = (endTime - startTime)/(Num_Iterations);
			System.out.printf("Took time: %3f", (float)time2/1e3);
		}

}
