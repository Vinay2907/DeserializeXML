package objComp.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import objComp.fileOperations.FileProcessorSupport;
import objComp.fileOperations.LoggerDebug;
import objComp.strategy.StrategySupport;
/**
 * Description - This has been used to perform the task of populating objects from the file
 * @author Vinayak
 * @since 2nd may 2015
 */

public class PopulateObjectImp implements PopulateObjectSupport,StrategySupport {
	private Map<Object,Integer> firstElements = null;
	private Map<Object,Integer> secondElements = null;
	private Map<String, Class<?>> dataLookUp = null;
	private FileProcessorSupport fileProcessorSupport = null;
	private String stringBuilder = null;
	private LoggerDebug loggerDebug = LoggerDebug.getInstance();

	
	public PopulateObjectImp(FileProcessorSupport fileProcessorSupport_In) {
		loggerDebug.printToStdout(2, "The construtor has been called for PopulateObjectImp");
		firstElements = new ConcurrentHashMap<Object, Integer>();
		secondElements = new ConcurrentHashMap<Object, Integer>();
		dataLookUp = new ConcurrentHashMap<String, Class<?>>();
		this.getDataLookUp().put("int", Integer.TYPE);
		this.getDataLookUp().put("String", String.class);
		this.getDataLookUp().put("double", Double.TYPE);
		this.getDataLookUp().put("Integer", Integer.class);
		this.fileProcessorSupport = fileProcessorSupport_In;
	}
	/**
	 * method used to count the duplicate elements
	 * @return integer value of the count
	 */

	@Override
	public int countDuplicateFirst() {
		loggerDebug.printToStdout(3, "The method has been called for countDuplicateFirst");
		int count = 0;
		Set<Object> keyValue = this.firstElements.keySet();
		for(Object setObject : keyValue){
			count += this.firstElements.get(setObject);
		}
		return count;
	}
	/**
	 * method used to count the duplicate elements
	 * @return integer value of the count
	 */

	@Override
	public int countDuplicateSecond() {
		loggerDebug.printToStdout(3, "The method has been called for countDuplicateSecond");
		
		int count = 0;
		Set<Object> keyValue = this.secondElements.keySet();
		for(Object setObject : keyValue){
			count += this.secondElements.get(setObject);
		}
		return count;
	}
	/**
	 * method used to count the duplicate elements
	 * @return integer value of the count
	 */

	@Override
	public int totalFirst() {
		loggerDebug.printToStdout(3, "The method has been called for totalFirst");
		
		return this.getFirstElements().size();
	}
	/**
	 * method used to count the duplicate elements
	 * @return integer value of the count
	 */

	@Override
	public int totalSecond() {
		loggerDebug.printToStdout(3, "The method has been called for totalSecond");
		
		return this.getSecondElements().size();
		
	}
	/**
	 * The method has been used for checking whether the string starts with a particular delimitter or not
	 * @param firstLine
	 * @return boolean value whether the condition provided is true or false.
	 */
	
	@Override
	public boolean checkFirst(String firstString) {
		return firstString.contains(PopulateObjectSupport.FIRST);
	}
	/**
	 * The method has been used for checking whether the string starts with a particular delimitter or not
	 * @param firstLine
	 * @return boolean value whether the condition provided is true or false.
	 */
	
	@Override
	public boolean checkSecond(String secondString) {
		return secondString.contains(PopulateObjectSupport.SECOND);
	}
	/**
	 * The method has been used for checking whether the string starts with a particular delimitter or not
	 * @param firstLine
	 * @return boolean value whether the condition provided is true or false.
	 */
	
	@Override
	public boolean checkStringStart(String startSwith) {
		return startSwith.startsWith(PopulateObjectSupport.FQN);
	}
	/**
	 *  to read data member values from an inputFile and accordingly create instances of First and Second. 
	 *  Decide the appropriate return value and parameters for the method deserObjects
	 */
	
	@Override
	public void deSerializeObjects() {
		loggerDebug.printToStdout(3, "The method has been called for deSerializeObjects");
		
		String className = null;
		Class<?> cls = null;
		Object createdObject = null;
		String contentsofClass[] = null;
		Map<String,String> keyValues = null;
		Class<?> signature[] = new Class<?>[1];
		String methodName = null;
		Object [] param = new Object[1];
		Object resultantInvocation = null;
		int count = 0;
		this.getFileProcessorSupport().openFile();
		while((stringBuilder = this.getFileProcessorSupport().readFromFile())!=null) {
			if(this.checkStringStart(stringBuilder)) {
				count = 0;
				String tokens[] = stringBuilder.split(":");
				className = tokens[1];
					try {
						cls = Class.forName(className);
						createdObject = cls.newInstance();
					} catch (ClassNotFoundException classNotFoundException) {
						System.err.println("ClassNot found exception has occured");
						System.exit(0);
					} catch (InstantiationException  | IllegalAccessException instantiationandIllegalaccessException) {
						System.err.println("IllegalAccessException or InstantiationException exception has occured");
						System.exit(0);
					}
			} else {
				count ++;
				contentsofClass = stringBuilder.split(",");
				String tokensofContents[] = null;
				Method method = null;
				keyValues = new HashMap<String, String>();
				 for(String conString : contentsofClass ) {
					 tokensofContents=conString.trim().split("=");
					 keyValues.put(tokensofContents[0], tokensofContents[1]);
				 }
				 signature[0] = this.dataLookUp.get(keyValues.get("type"));
				 methodName = PopulateObjectSupport.SETTER + keyValues.get("var");
				 try {
					method = cls.getDeclaredMethod(methodName, signature[0]);
				} catch (NoSuchMethodException | SecurityException e) {
					System.err.println("NoSuchMethodException or SecurityException exception has occured");
					System.exit(0);
				}
				 if(keyValues.get("type").equals(PopulateObjectSupport.INTEGERVAR)) {
					 param[0] = new Integer(keyValues.get("value"));
				 } else if(keyValues.get("type").equals(PopulateObjectSupport.DOUBLEVAR)) {
					 param[0] = new Double(keyValues.get("value"));
				 }else if(keyValues.get("type").equals(PopulateObjectSupport.STRINGVAR)) {
					 param[0] = new String(keyValues.get("value"));
				 }
				 try {
					resultantInvocation = method.invoke(createdObject, param);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					System.err.println("IllegalAccessException or InstantiationException or InvocationTargetException  exception has occured");
					System.exit(0);
				}
				 if(count == 2) {
					 setFirstandSecondObjects(createdObject);
				 }
			}
		}
		this.getFileProcessorSupport().closeFile();
	}
	
	/**
	 * Data method that is been used for storing the actual objects
	 * @param actualObject
	 */
	
	@Override
	public void setFirstandSecondObjects(Object actualObject) {
		loggerDebug.printToStdout(3, "The method has been called for setFirstandSecondObjects");
		
		if(actualObject instanceof First) {
			if(!firstElements.containsKey(actualObject)) {
				firstElements.put(actualObject, PopulateObjectSupport.OBJECTCOUNT);
			} else {
				firstElements.put(actualObject,firstElements.get(actualObject)+PopulateObjectSupport.OBJECTCOUNT);			
			}
		} else if(actualObject instanceof Second) {
			if(!secondElements.containsKey(actualObject)) {
				secondElements.put(actualObject, PopulateObjectSupport.OBJECTCOUNT);
			} else {
				secondElements.put(actualObject,secondElements.get(actualObject)+PopulateObjectSupport.OBJECTCOUNT);			
				
			}
		}
	}
	
	public Map<Object, Integer> getFirstElements() {
		return firstElements;
	}

	public void setFirstElements(Map<Object, Integer> firstElements) {
		this.firstElements = firstElements;
	}

	public Map<Object, Integer> getSecondElements() {
		return secondElements;
	}

	public void setSecondElements(Map<Object, Integer> secondElements) {
		this.secondElements = secondElements;
	}

	public Map<String, Class<?>> getDataLookUp() {
		return dataLookUp;
	}

	public void setDataLookUp(Map<String, Class<?>> dataLookUp) {
		this.dataLookUp = dataLookUp;
	}
	public FileProcessorSupport getFileProcessorSupport() {
		return fileProcessorSupport;
	}
	public void setFileProcessorSupport(FileProcessorSupport fileProcessorSupport) {
		this.fileProcessorSupport = fileProcessorSupport;
	}

		
}
