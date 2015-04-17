import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/*
 * CTF Server class.
 */
public class CTF {

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
                
              
                System.out.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

	
	
	
	
	
	
}
