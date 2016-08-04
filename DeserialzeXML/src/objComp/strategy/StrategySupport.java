package objComp.strategy;

/**
 * Description - This class has been used for the operations to perform check operation while fetching 
 * the data from the read file of the file processor support class
 * @author Vinayak
 * @since - 2nd May 2015
 */
public interface StrategySupport {
	/**
	 * The method has been used for checking whether the string starts with a particular delimitter or not
	 * @param firstLine
	 * @return boolean value whether the condition provided is true or false.
	 */
	public boolean checkStringStart(String firstLine); 
	/**
	 * The method has been used for checking whether the string starts with a particular delimitter or not
	 * @param firstLine
	 * @return boolean value whether the condition provided is true or false.
	 */
	
	public boolean checkFirst(String firstString); 
	/**
	 * The method has been used for checking whether the string starts with a particular delimitter or not
	 * @param firstLine
	 * @return boolean value whether the condition provided is true or false.
	 */
	
	public boolean checkSecond(String secondString);
}
