import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;


public class VoterFrame extends JFrame {

	String name;
	String validationID;
	static int voterID;
	boolean valid;
	boolean voted;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtValidationId;
	private JTextField txtPsudoname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VoterFrame frame = new VoterFrame();
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
	public VoterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVoter = new JLabel("Voter");
		lblVoter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVoter.setBounds(15, 16, 55, 34);
		contentPane.add(lblVoter);
		
		final JTextPane console = new JTextPane();
		console.setBounds(0, 457, 569, 110);
		contentPane.add(console);
		
		JLabel lblConsole = new JLabel("Console");
		lblConsole.setBounds(265, 436, 61, 20);
		contentPane.add(lblConsole);
		
		txtName = new JTextField();
		txtName.setText("Enter Name");
		txtName.setBounds(52, 159, 169, 34);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JButton btnRequestValidationID = new JButton("Request Validation ID");
		btnRequestValidationID.setBounds(234, 159, 185, 34);
		btnRequestValidationID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = txtName.getText();
				String validationID = CLAFrame.requestValidationID(name);
				if(validationID!=null){
				console.setText("Success! Validation ID: "+validationID);
				}
				else{
				console.setText("Voter name: "+name +" is ineligible!");
				}
			}
		});
		contentPane.add(btnRequestValidationID);
		
		JButton btnInitializeVoterCla = new JButton("Initialize Voter CLA");
		btnInitializeVoterCla.setBounds(52, 66, 169, 45);
		btnInitializeVoterCla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initializeVoterToCLA();
				console.setText("Voter -> CLA connection established!");
			}
		});
		contentPane.add(btnInitializeVoterCla);
		
		JLabel label = new JLabel("1)");
		label.setBounds(25, 75, 20, 26);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("2)");
		label_1.setBounds(25, 163, 20, 27);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("3)");
		label_2.setBounds(25, 238, 20, 20);
		contentPane.add(label_2);
		
		txtValidationId = new JTextField();
		txtValidationId.setText("Validation ID");
		txtValidationId.setBounds(52, 235, 146, 26);
		contentPane.add(txtValidationId);
		txtValidationId.setColumns(10);
		
		txtPsudoname = new JTextField();
		txtPsudoname.setText("Psudo-Name");
		txtPsudoname.setBounds(52, 277, 146, 26);
		contentPane.add(txtPsudoname);
		txtPsudoname.setColumns(10);
	}
	
	
	/*
	public Voter(String name){
		this.name=name;
	}
	*/
	public void initializeVoterToCLA(){
		try {
        	/*
        	 * socket for Voter -> CLA
        	 */
           SSLSocketFactory sslsocketfactoryCLA = (SSLSocketFactory) SSLSocketFactory.getDefault();
           SSLSocket sslsocketCLA = (SSLSocket) sslsocketfactoryCLA.createSocket("localhost", 3577);

      //     InputStream inputstreamVoter = System.in;
      //     InputStreamReader inputstreamreaderVoter = new InputStreamReader(inputstreamVoter);
      //     BufferedReader bufferedreaderVoter = new BufferedReader(inputstreamreaderVoter);

           OutputStream outputstreamCLA = sslsocketCLA.getOutputStream();
           OutputStreamWriter outputstreamwriterCLA = new OutputStreamWriter(outputstreamCLA);
           BufferedWriter bufferedwriterCLA = new BufferedWriter(outputstreamwriterCLA);
           
           
           String string = null;
     /*       while ((string = bufferedreaderVoter.readLine()) != null) {
        	   
        	   
        	 //  String validationNum = CLA.requestValidationID("");
        	   
        	   //send message to CTF(voterID, validationNum, vote)
        //	   String candidate="President";
        //	   String vote = castVote(voterID,validationNum,candidate);
        	   
               bufferedwriterCLA.write(string + '\n');
               bufferedwriterCLA.flush();
           }*/
           
       } catch (Exception exception) {
           exception.printStackTrace();
       }
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
