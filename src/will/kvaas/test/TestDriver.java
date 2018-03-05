package will.kvaas.test;

import java.util.Random;

import xyz.keyvalue.api.*;

public class TestDriver {

	private TestDriver(){}
	
	public final static String makeRandomString() {
		  
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    Random random = new Random();
	    
	    int targetStringLength = random.nextInt(1000000);

	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	 
	    System.out.println(generatedString);
	    
	    return generatedString; 
	    
	}//end makeRandomString
	
	public static void main(String[] args) {

		IKVaaS xVal = new KVaaS();

		System.out.printf("getKey() = %s%n", xVal.getKey());
		
		String randomString = TestDriver.makeRandomString();
		
		try
		{
		
		xVal.putValue(randomString);
		
		}
		catch(KVaaSException kvex)
		{
			System.out.println(kvex.toString());
			kvex.printStackTrace();
		}//end try
		
		try { Thread.sleep(3000); } catch (Exception ex) { }		
		
		String str = xVal.getValue();
		
		if(str.equals(randomString))
		{
			System.out.println("Strings match!");
		}
		else
		{
			System.out.println("String do not match!");
		}
		
		System.out.printf("%n%n");

		System.out.printf("%n%n");

		System.exit(0);

	}// end main
		//
}
