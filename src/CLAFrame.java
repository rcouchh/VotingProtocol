import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTable;


public class CLAFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CLAFrame frame = new CLAFrame();
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
	public CLAFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextPane txtpnConsole = new JTextPane();
		txtpnConsole.setEditable(false);
		txtpnConsole.setText("Console");
		txtpnConsole.setBounds(384, 97, 277, 305);
		contentPane.add(txtpnConsole);
		
		JLabel lblClaServer = new JLabel("CLA Server");
		lblClaServer.setBounds(294, 26, 92, 34);
		contentPane.add(lblClaServer);
		
		JButton btnInitializeClaServer = new JButton("Initialize CLA Server");
		btnInitializeClaServer.setBounds(44, 70, 186, 34);
		btnInitializeClaServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initializeCLAConnection();
				txtpnConsole.setText("CLA Server connection established!");
			}
		});
		contentPane.add(btnInitializeClaServer);
		
		JLabel lblConsole = new JLabel("Console");
		lblConsole.setBounds(482, 61, 69, 20);
		contentPane.add(lblConsole);
		
		JButton btnInitializeCtfConnection = new JButton("Initialize CTF Connection");
		btnInitializeCtfConnection.setBounds(41, 123, 224, 39);
		btnInitializeCtfConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					initializeCTFConnection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				txtpnConsole.setText("CLA Server connection established!");
			}
		});
		contentPane.add(btnInitializeCtfConnection);
		
		JButton btnSendClaList = new JButton("Send CLA List");
		btnSendClaList.setEnabled(false);
		btnSendClaList.setBounds(117, 436, 173, 32);
		contentPane.add(btnSendClaList);
		
		table = new JTable();
		table.setBounds(0, 307, 277, 125);
		contentPane.add(table);
		
		JLabel lblClaList = new JLabel("CLA List");
		lblClaList.setBounds(29, 287, 69, 20);
		contentPane.add(lblClaList);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(10, 438, 92, 29);
		contentPane.add(btnRefresh);
		
		
		
	}
	
	
	
	
	/*
	 * static variables
	 */
	static BufferedReader input;
	static BufferedWriter output;
	static ObjectOutputStream objectOutput;
	
    //list of eligible voters
	static validList list = new validList();
	//list of validation numbers given out to voters
	//each entry contains voters name, validationNum, voted
	static ArrayList<CLAEntry> CLAList = new ArrayList<>();
	private JTable table;
	
	/*
	 * Initializes SSL connection between CLA/Voter
	 */
	 static void initializeCLAConnection(){
	    	try {
	            SSLServerSocketFactory sslserversocketfactory =
	                    (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
	            SSLServerSocket sslserversocket =
	                    (SSLServerSocket) sslserversocketfactory.createServerSocket(3577);
	            SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();

	           
	            InputStream inputstream = sslsocket.getInputStream();
	            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
	            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
	            
	            /* 
	             * Need to switch to an objectoutputstream to send list properly
	             */
	            OutputStream outputstream = sslsocket.getOutputStream();
		        OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
		        BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);
		        
		        
		        input=bufferedreader;
		        output=bufferedwriter;
	   	        System.out.println("Connection accepted from: "+sslserversocket.getInetAddress());

		      //  System.out.println("Successfully established CLA server on SSL port 3577");
	    	} catch (Exception exception) {
		            exception.printStackTrace();
		        }
	    }
	    
		
	 /*
	     * Establishes SSL connection with CTF server
	     */
	    static void initializeCTFConnection() throws IOException{
	    	 SSLServerSocketFactory sslserversocketfactory =
	                 (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
	         SSLServerSocket sslserversocket =
	                 (SSLServerSocket) sslserversocketfactory.createServerSocket(3578);
	         SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();

	         InputStream inputstream = sslsocket.getInputStream();
	         InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
	         BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

	         ObjectOutputStream outputstream = (ObjectOutputStream) sslsocket.getOutputStream();
		     objectOutput=outputstream;
		     OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
		     BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);
    }
    

	  //sends CLAList to the CTF
		public void sendCLAList(ArrayList<CLAEntry> CLAList){
		//	this.output.w
		}
		
		
	    
	 
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
		
		//create random validationID
		//creates ID via UUID
		public static String generateValidationID(){
			UUID id = UUID.randomUUID();
			//System.out.println("UUID: "+id);
			return String.valueOf(id);
		}
}
