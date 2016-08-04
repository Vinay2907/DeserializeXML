package objComp.util;
/**
 * Description - This has been used to perform the task of populating objects from the file
 * @author Vinayak
 * @since 2nd may 2015
 */
public interface PopulateObjectSupport {
	/**
	 * Variables declared as public static final that has been used to perfom various contants checking in the class.
	 */
	public static final int OBJECTCOUNT = 1;
	public static final String FQN ="fqn:";
	public static final String FIRST ="First";
	public static final String SECOND ="Second";
	public static final String SETTER = "set";
	public static final String INTEGERVAR = "int";
	public static final String DOUBLEVAR = "double";
	public static final String STRINGVAR = "String";
	/**
	 *  to read data member values from an inputFile and accordingly create instances of First and Second. 
	 *  Decide the appropriate return value and parameters for the method deserObjects
	 */
	public void deSerializeObjects();
	/**
	 * Data method that is been used for storing the actual objects
	 * @param actualObject
	 */
	public void setFirstandSecondObjects(Object actualObject);
	/**
	 * method used to count the duplicate elements
	 * @return integer value of the count
	 */
	public int countDuplicateFirst();
	/**
	 * method used to count the duplicate elements
	 * @return integer value of the count
	 */

	public int countDuplicateSecond();
	/**
	 * method used to count the duplicate elements
	 * @return integer value of the count
	 */

	public int totalFirst();
	/**
	 * method used to count the duplicate elements
	 * @return integer value of the count
	 */

	public int totalSecond();
}
