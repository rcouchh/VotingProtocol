import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import java.io.*;
import java.util.Random;


/*
 * Voter client class. Communicated over SSL to CLA/CTF servers.
 */
public class Voter {
	String name;
	String validationID;
	static int voterID;
	boolean valid;
	boolean voted;
	
	 public static void main(String[] args) {
	        try {
	        	/*
	        	 * socket for Voter -> CLA
	        	 */
	           SSLSocketFactory sslsocketfactoryCLA = (SSLSocketFactory) SSLSocketFactory.getDefault();
	           SSLSocket sslsocketCLA = (SSLSocket) sslsocketfactoryCLA.createSocket("localhost", 3577);

	           InputStream inputstreamVoter = System.in;
	           InputStreamReader inputstreamreaderVoter = new InputStreamReader(inputstreamVoter);
	           BufferedReader bufferedreaderVoter = new BufferedReader(inputstreamreaderVoter);

	           OutputStream outputstreamCLA = sslsocketCLA.getOutputStream();
	           OutputStreamWriter outputstreamwriterCLA = new OutputStreamWriter(outputstreamCLA);
	           BufferedWriter bufferedwriterCLA = new BufferedWriter(outputstreamwriterCLA);
	           
	           
	           
	           /*
	            * Socket for Voter -> CTF
	            */
	           SSLSocketFactory sslsocketfactoryCTF = (SSLSocketFactory) SSLSocketFactory.getDefault();
	           SSLSocket sslsocketCTF = (SSLSocket) sslsocketfactoryCTF.createSocket("localhost", 3579);

	           InputStream inputstreamCTF = System.in;
	           InputStreamReader inputstreamreaderCTF = new InputStreamReader(inputstreamCTF);
	           BufferedReader bufferedreaderCTF = new BufferedReader(inputstreamreaderCTF);

	           OutputStream outputstreamCTF = sslsocketCTF.getOutputStream();
	           OutputStreamWriter outputstreamwriterCTF = new OutputStreamWriter(outputstreamCTF);
	           BufferedWriter bufferedwriterCTF = new BufferedWriter(outputstreamwriterCTF);

	           String string = null;
	     /*        while ((string = bufferedreader.readLine()) != null) {
	        	   
	        	   
	        	   String validationNum = CLA.requestValidationID("");
	        	   
	        	   //send message to CTF(voterID, validationNum, vote)
	        	   String candidate="President";
	        	   String vote = castVote(voterID,validationNum,candidate);
	        	   
	               bufferedwriter.write(string + '\n');
	               bufferedwriter.flush();
	           }
	           */
	       } catch (Exception exception) {
	           exception.printStackTrace();
	       }
	
		 int i=generateVoterID();
		 System.out.println(i);
		 
	 }
	
	public Voter(String name){
		this.name=name;
	}
	
	public boolean getVoted(){
		return this.voted;
	}
	
	public boolean getValid(){
		return this.valid;
	}
	
	public String getValidationID(){
		return this.validationID;
	}
	
	//generates random ID of length 10
	public static int generateVoterID(){		
		Random randomGenerator = new Random();
		int ID = randomGenerator.nextInt(1000000000);
		return ID;
	}
	
	//sends vote message to the CTF
	public static String castVote(int voterID, String validationNum, String candidate){
		String vote = "";
		vote = vote.concat(Integer.toString(voterID)+"."+validationNum+"."+candidate);
		return vote;
	}

	
}
