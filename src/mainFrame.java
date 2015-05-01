import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;


public class mainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterName;
	private JTextField txtResult;
	private JTextField txtEnterValidation;
	private JTextField txtEnterName_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
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
	public mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 800);
		contentPane = new JPanel();
		this.setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton initialize = new JButton("Initialize");
		initialize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		initialize.setBounds(15, 73, 115, 29);
		contentPane.add(initialize);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(72, 164, 115, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblConsole = new JLabel("Console");
		lblConsole.setBounds(498, 27, 69, 20);
		contentPane.add(lblConsole);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(145, 73, 115, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblSetupSteps = new JLabel("Setup Steps");
		lblSetupSteps.setBounds(98, 22, 89, 30);
		contentPane.add(lblSetupSteps);
		
		JLabel lblVoter = new JLabel("Voter");
		lblVoter.setBounds(118, 381, 69, 20);
		contentPane.add(lblVoter);
		
		JButton btnCastVote = new JButton("Cast Vote");
		btnCastVote.setBounds(84, 597, 115, 29);
		contentPane.add(btnCastVote);
		
		txtEnterName = new JTextField();
		txtEnterName.setText("Enter Name");
		txtEnterName.setBounds(65, 417, 146, 26);
		contentPane.add(txtEnterName);
		txtEnterName.setColumns(10);
		
		txtResult = new JTextField();
		txtResult.setEditable(false);
		txtResult.setText("Result");
		txtResult.setBounds(116, 459, 238, 29);
		contentPane.add(txtResult);
		txtResult.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Confirm Eligibility");
		btnNewButton_2.setBounds(254, 416, 159, 29);
		contentPane.add(btnNewButton_2);
		
		JLabel label = new JLabel("1)");
		label.setBounds(30, 420, 20, 20);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("2)");
		label_1.setBounds(30, 522, 20, 20);
		contentPane.add(label_1);
		
		txtEnterValidation = new JTextField();
		txtEnterValidation.setText("Enter Validation #");
		txtEnterValidation.setBounds(65, 519, 146, 26);
		contentPane.add(txtEnterValidation);
		txtEnterValidation.setColumns(10);
		
		txtEnterName_1 = new JTextField();
		txtEnterName_1.setText("Enter Name");
		txtEnterName_1.setBounds(65, 555, 146, 26);
		contentPane.add(txtEnterName_1);
		txtEnterName_1.setColumns(10);
		
		JCheckBox chckbxCandidate1 = new JCheckBox("Candidate1");
		chckbxCandidate1.setBounds(274, 518, 139, 29);
		contentPane.add(chckbxCandidate1);
		
		JCheckBox chckbxCandidate2 = new JCheckBox("Candidate2");
		chckbxCandidate2.setBounds(274, 554, 139, 29);
		contentPane.add(chckbxCandidate2);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(390, 73, 301, 286);
		contentPane.add(textPane);
	}
}
