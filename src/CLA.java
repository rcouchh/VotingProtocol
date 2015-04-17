import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/*
 * CLA Server class. 
 */
public class CLA {
	
	//main
    public static void main(String[] args) {
        try {
            SSLServerSocketFactory sslserversocketfactory =
                    (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket sslserversocket =
                    (SSLServerSocket) sslserversocketfactory.createServerSocket(3577);
            SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();

            InputStream inputstream = sslsocket.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

            String string = null;
            String stringLower = null;
            String name = null;
            
            while ((string = bufferedreader.readLine()) != null) {
                System.out.println(string);
                stringLower=string.toLowerCase();
                
                //if string contains request for validation ID, from Voter
                if(stringLower.startsWith("request ")){
                
                //
                int index=stringLower.indexOf("request ");
                name=string.substring(index+8);
                String ID = requestValidationID(name);
                if(ID!=null){
                	System.out.println("Validation #: "+ID);
                }
                else{
                	System.out.println("Error creating validation ID!");
                }
              }//end if requesting validation
                System.out.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    
    
	 
    //list of eligible voters
	static validList list = new validList();
	
	//list of validation numbers given out to voters
	//each entry contains voters name, validationNum, voted
	static ArrayList<CLAEntry> CLAList = new ArrayList<>();
	
	
	
	
	//takes in request from voter seeking validationID
	//input is name of voter
	public static String requestValidationID(String name){
		
		//confirm voter is on list of eligible voters
		boolean eligible = list.isValid(name);
		
		//if eligible, generate voterID
		if(eligible==true){
		String voterID = generateValidationID();
		
		//add voterID to list of validation numbers given out
		CLAList.add(new CLAEntry(name, voterID, false));
		
		return voterID;
		}
		else{ //ineligible
		return null;
		}
		
	}
	
	
	
	//sends CLAList to the CTF
	public void sendCLAList(ArrayList<CLAEntry> CLAList){
		
	}
	
	
	
	//create random validationID
	//creates ID via UUID
	public static String generateValidationID(){
		UUID id = UUID.randomUUID();
		//System.out.println("UUID: "+id);
		return String.valueOf(id);
	}
	
	
	
	//creates ID via SecureRandom and MessageDigest
	public String generateValidationSecureID() throws NoSuchAlgorithmException{
		String validationID="";
		
		SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
		//generate random number
		String randomNum = new Integer(prng.nextInt()).toString();
		
	    //get its digest
	    MessageDigest sha = MessageDigest.getInstance("SHA-1");
	    byte[] result =  sha.digest(randomNum.getBytes());

	    System.out.println("Random number: " + randomNum);
	    System.out.println("Message digest: " + hexEncode(result));
		
		return validationID;
	}
	static private String hexEncode(byte[] aInput){
	    StringBuilder result = new StringBuilder();
	    char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
	    for (int idx = 0; idx < aInput.length; ++idx) {
	      byte b = aInput[idx];
	      result.append(digits[ (b&0xf0) >> 4 ]);
	      result.append(digits[ b&0x0f]);
	    }
	    return result.toString();
	  }
	
	
	
	
}
