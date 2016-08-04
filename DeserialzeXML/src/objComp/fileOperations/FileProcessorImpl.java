/**
 * 
 */
package objComp.fileOperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Description - This is the File processor which works for performing the file operations to be performed.
 * @author Vinayak Subhash Pingale
 * @since April 18th 2015
 * @version 1.0
 * @implements {@link FileProcessorSupport}
 * @exception	FileNotFoundException, IOException, URISyntaxException
 */

public class FileProcessorImpl implements FileProcessorSupport {

	private FileReader fileReader = null;
	private FileWriter fileWriter = null;
	private String fileName = null;
	private String outputFileName = null;
	private BufferedReader getSingleLineBufferedReader = null;
	private BufferedWriter bufferedWriter = null;
	private File file = null;
	private LoggerDebug loggerDebug = LoggerDebug.getInstance();

	/**
	 * This constructor sets the name of the file which has been used to perform the operation's
	 * @param in_Filename
	 */
	
	public FileProcessorImpl() {
		loggerDebug.printToStdout(2, "FileProcessor  Constructor has been called.");
	}

	public FileProcessorImpl(String in_Filename, String out_Filename) {
		this.fileName = in_Filename;
		this.outputFileName = out_Filename;
		loggerDebug.printToStdout(2, "FileProcessor parametrized Constructor has been called.");
	}
	/**
	 * This function has been added to perform the operation of opening a file
	 * @param fileNameIn - To get the name of the file in the path on which the operations has to be performed
	 */
		
	@Override
	public void openFile() {
		try {
			file = new File(this.getFileName());
			if (file.length() == 0) {
				System.err.println("File length is zero exiting from the Application. "+this.getClass());
				System.exit(1);
			}
			fileReader = new FileReader(getFileName());
			this.setGetSingleLineBufferedReader(new BufferedReader(fileReader));
		} catch (FileNotFoundException e) {
			System.err.println("File that has been supposed to work for File operation has not been found in "+this.getClass());
			System.exit(1);
		}
	}
	
	public void openForWriting() {
		loggerDebug.printToStdout(3, "FileProcessor  openForWriting has been called.");
		try {
			file = new File(this.getOutputFileName());
			if (!file.exists()) {
				file.createNewFile();
			}
			fileWriter = new FileWriter(file.getAbsoluteFile(),true);
			this.setBufferedWriter(new BufferedWriter(fileWriter));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * This function reads from the file when the File descriptor has been provided by the File processor using the Open file
	 * @return String that has been read from the file for this application it has been restricted to reading single line from the file.
	 */
	
	@Override
	public synchronized String readFromFile() {
		String getString = null;
		try {
			getString = this.getGetSingleLineBufferedReader().readLine();
			
		} catch (IOException e) {
			System.err.println("IOException in "+this.getClass()+" has been occurred while reading the file from the file system.");
			System.exit(1);
		}
			return getString;
	}
	/**
	 * This function write to the file when the File descriptor has been provided by the File processor using the Open file
	 * @return String that has been read from the file for this application it has been restricted to reading single line from the file.
	 */
	
	
	@Override
	public void writeToFile(String contentsTowWrite) {
		loggerDebug.printToStdout(3, "FileProcessor  openForWriting has been called.");
		try {
			this.getBufferedWriter().write(contentsTowWrite);
		} catch (IOException e) {
			System.err.println("IOException in "+this.getClass()+" has been occurred while writing the file from the file system.");
			System.exit(1);
		}
		
	}
	/**
	 * This function reads from the file when the File descriptor has been provided by the File processor using the Open file
	 * @return String that has been read from the file for this application it has been restricted to reading single line from the file.
	 */
	
	@Override
	public void getFileHandleWriterandClose() {
		loggerDebug.printToStdout(3, "FileProcessor  getFileHandleWriterandClose has been called.");
		try {
			this.getBufferedWriter().close();
		} catch (IOException e) {
			System.err.println("IOException in "+this.getClass()+" has been occurred while writing the file from the file system.");
			System.exit(1);
		}
	}
	/**
	 * This function has been added to perform the operation of closing a file
	 * @param fileNameIn - To get the name of the file in the path on which the operations has to be performed
	 */
	
	@Override
	public void closeFile() {
		loggerDebug.printToStdout(3, "FileProcessor closeFile has been called.");
		//file.
		try {
			this.getGetSingleLineBufferedReader().close();
		} catch (IOException e) {
			System.err.println("IOException in "+this.getClass()+" has been occurred while writing the file from the file system.");
			System.exit(1);
		}
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BufferedReader getGetSingleLineBufferedReader() {
		return getSingleLineBufferedReader;
	}

	public void setGetSingleLineBufferedReader(
			BufferedReader getSingleLineBufferedReader) {
		this.getSingleLineBufferedReader = getSingleLineBufferedReader;
	}

	public BufferedWriter getBufferedWriter() {
		return bufferedWriter;
	}

	public void setBufferedWriter(BufferedWriter bufferedWriter) {
		this.bufferedWriter = bufferedWriter;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	
	
}
