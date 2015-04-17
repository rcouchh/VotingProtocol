import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;


/*
 * Voter client class. Communicated over SSL to CLA/CTF servers.
 */
public class Voter {
	String name;
	String validationID;
	boolean valid;
	boolean voted;
	
	 public static void main(String[] arstring) {
	        try {
	           SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
	           SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 3577);

	           InputStream inputstream = System.in;
	           InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
	           BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

	           OutputStream outputstream = sslsocket.getOutputStream();
	           OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
	           BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);

	           String string = null;
	           while ((string = bufferedreader.readLine()) != null) {
	               bufferedwriter.write(string + '\n');
	               bufferedwriter.flush();
	           }
	       } catch (Exception exception) {
	           exception.printStackTrace();
	       }
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
	

	
}
