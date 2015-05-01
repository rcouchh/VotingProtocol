import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;


public class CTFFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CTFFrame frame = new CTFFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CTFFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCtfServer = new JLabel("CTF Server");
		lblCtfServer.setBounds(296, 16, 86, 34);
		contentPane.add(lblCtfServer);
		
		final JTextPane console = new JTextPane();
		console.setBounds(306, 79, 349, 344);
		contentPane.add(console);
		
		JButton btnInitializeCTFServer = new JButton("Initialize CTF Server");
		btnInitializeCTFServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	initializeCTFConnection();
				console.setText("CTF CLA connection established!");
			}
		});
		btnInitializeCTFServer.setBounds(49, 102, 175, 48);
		contentPane.add(btnInitializeCTFServer);
		
		JButton btnConnectToCla = new JButton("Connect to CLA");
		btnConnectToCla.setBounds(49, 217, 175, 48);
		contentPane.add(btnConnectToCla);
	}
	
	public void receiveCLAConnection(){
		try{
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
   	        
		} catch(Exception e){
            e.printStackTrace();
		}
	}
	
}
