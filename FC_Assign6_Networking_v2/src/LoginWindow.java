import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;

public class LoginWindow {

	private JFrame frame;
	private JTextField txtUser;
	private JTextField txtPassword;
	private JButton btnSubmit;
	private String userVal=null;
	private String passVal=null;
	

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
		
		//button commands from v1
		btnSubmit.addActionListener(new ActionListener()
		{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("clicked submit btn");
			userVal=txtUser.getText();
			passVal=txtPassword.getText();
			
							
			try {						
				String serverAddress = "127.0.0.1";
		        int port = 9070;
		        Socket socket = new Socket(serverAddress, port);
				
		        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				
		        //this print is on the socket
		        out.println("auth:"+userVal+":"+passVal);			        
		        
		        System.out.println("sends to server");
		        
		        BufferedReader input =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        System.out.println("recieves from server");
		        String reply=input.readLine();  //returns y or n
		        System.out.println("read line");
		        System.out.println(reply);
		        
		        if(reply.equals("y")) {
					//auth=true;
					System.out.println("is true");
					//startChat();
					frame.setVisible(false);
				}
		        //clickSubmit=true;
		        System.out.println("end click");
		        
		      //get stuck here
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
			
		}
		
		
		});
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 601, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 262, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblUser = new JLabel("User:");
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.insets = new Insets(0, 0, 5, 0);
		gbc_lblUser.gridx = 3;
		gbc_lblUser.gridy = 1;
		frame.getContentPane().add(lblUser, gbc_lblUser);
		
		txtUser = new JTextField();
		txtUser.setText("user");
		GridBagConstraints gbc_txtUser = new GridBagConstraints();
		gbc_txtUser.insets = new Insets(0, 0, 5, 0);
		gbc_txtUser.gridx = 3;
		gbc_txtUser.gridy = 2;
		frame.getContentPane().add(txtUser, gbc_txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.gridx = 3;
		gbc_lblPassword.gridy = 3;
		frame.getContentPane().add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setText("password");
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 3;
		gbc_txtPassword.gridy = 4;
		frame.getContentPane().add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 3;
		gbc_btnSubmit.gridy = 6;
		frame.getContentPane().add(btnSubmit, gbc_btnSubmit);
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
