import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/*
 * CTF Server class.
 * Operates as server on port 3578
 */
public class CTF {

	static ObjectInputStream inputCLA;
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
        try {
        	/*
        	 * open socket for CLA -> CTF interaction
        	 */
            SSLServerSocketFactory sslserversocketfactoryCLA =
                    (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket sslserversocketCLA =
                    (SSLServerSocket) sslserversocketfactoryCLA.createServerSocket(3578);
            SSLSocket sslsocketCLA = (SSLSocket) sslserversocketCLA.accept();

            ObjectInputStream inputstreamCLA = (ObjectInputStream) sslsocketCLA.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstreamCLA);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
            
            OutputStream outputstream = sslsocketCLA.getOutputStream();
   	        OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
   	        BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);
   	        
   	        System.out.println("CLA Connection accepted from: "+sslserversocketCLA.getInetAddress());
   	        
   	        /*
   	         * open socket for Voter -> CTF interaction
   	         */
   	        SSLServerSocketFactory sslserversocketfactoryVoter =
                 (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket sslserversocketVoter =
                 (SSLServerSocket) sslserversocketfactoryVoter.createServerSocket(3579);
            SSLSocket sslsocketVoter = (SSLSocket) sslserversocketCLA.accept();

            ObjectInputStream inputstreamVoter = (ObjectInputStream) sslsocketVoter.getInputStream();
            InputStreamReader inputstreamreaderVoter = new InputStreamReader(inputstreamVoter);
            BufferedReader bufferedreaderVoter = new BufferedReader(inputstreamreaderVoter);
         
            OutputStream outputstreamVoter = sslsocketVoter.getOutputStream();
	        OutputStreamWriter outputstreamwriterVoter = new OutputStreamWriter(outputstreamVoter);
	        BufferedWriter bufferedwriterVoter = new BufferedWriter(outputstreamwriterVoter);

	        
            String string = null;
            String stringLower = null;
            String name = null;
            ArrayList<CLAEntry> CLAList = null;
            
            //differentiate between Voter and CLA!
			while((CLAList = (ArrayList<CLAEntry>) inputstreamCLA.readObject()) != null){
				String s = (String)inputstreamCLA.readObject();
         // while ((string = bufferedreader.readLine()) != null) {
                System.out.println(string);
                stringLower=string.toLowerCase();
                
              
                System.out.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

	
	
	
	
	
	
}
