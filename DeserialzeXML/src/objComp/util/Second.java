package objComp.util;

import objComp.fileOperations.LoggerDebug;

/**
 * Description - This is the clas inwhich the reflection has been taking place 
 * @author Vinayak
 * @since 2nd may 2015
 */

public class Second {
	double DoubleValue;
	int IntValue;
	private LoggerDebug loggerDebug = LoggerDebug.getInstance();

	/**
	 * An empty constructor that has been defined for the operation of the First class
	 */
	public Second() {
		loggerDebug.printToStdout(2, "The construtor has been called for Second");
	}
	public double getDoubleValue() {
		return DoubleValue;
	}
	public void setDoubleValue(double doubleValue) {
		DoubleValue = doubleValue;
	}
	public int getIntValue() {
		return IntValue;
	}
	public void setIntValue(int intValue) {
		IntValue = intValue;
	}
	/**
	 * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by HashMap. 
	   The general contract of hashCode is: Whenever it is invoked on the same object more than once during an execution of a Java application,
	   the hashCode method must consistently return the same integer, provided no information used in equals comparisons on the object is modified. 
	   This integer need not remain consistent from one execution of an application to another execution of the same application. 
	   If two objects are equal according to the equals(Object) method, then calling the hashCode method on each of the two objects 
	   must produce the same integer result. 
	   @Returns: a hash code value for this object.
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(DoubleValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + IntValue;
		return result;
	}
	/**
	 * Indicates whether some other object is "equal to" this one. 
       The equals method implements an equivalence relation on non-null object references: 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Second other = (Second) obj;
		if (Double.doubleToLongBits(DoubleValue) != Double
				.doubleToLongBits(other.DoubleValue))
			return false;
		if (IntValue != other.IntValue)
			return false;
		return true;
	}
}
