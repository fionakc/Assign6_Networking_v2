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
	private JLabel lblLoginmessage;
	
	/**
	 * try putting these up here, in prep for moving other window inside this class
	 */
	
	String serverAddress = "127.0.0.1";
    int port = 9070;
    Socket socket; 
	
    
    /**
     * from chatwindow class
     */
    private JFrame frame2;
	private JTextField txtInputtext;

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initializeLoginWindow();
		initializeChatWindow();
		
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
//				String serverAddress = "127.0.0.1";
//		        int port = 9070;
//		        Socket socket = new Socket(serverAddress, port);
				socket= new Socket(serverAddress, port);  //this needs to be inside the try/catch
				
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
		        	lblLoginmessage.setVisible(false);
					//auth=true;
					System.out.println("is true");
					//startChat();
					frame.setVisible(false);
					frame2.setVisible(true);
					//ChatWindow chatwindow = new ChatWindow();
					
				}else {
					/**
					 * this bit is new
					 */
					lblLoginmessage.setVisible(true);  
					
					
					/**
					 * temp dump this in here so can see second window
					 */
					
					lblLoginmessage.setVisible(false);
					//auth=true;
					System.out.println("is true");
					//startChat();
					frame.setVisible(false);
					ChatWindow chatwindow = new ChatWindow();
					
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
		
		
	} //end LoginWindow
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeLoginWindow() {
		frame = new JFrame();
		frame.setBounds(100, 100, 601, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 262, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_btnSubmit.gridx = 3;
		gbc_btnSubmit.gridy = 6;
		frame.getContentPane().add(btnSubmit, gbc_btnSubmit);
		
		
		/**
		 * new label here
		 */
		lblLoginmessage = new JLabel("loginMessage");
		GridBagConstraints gbc_lblLoginmessage = new GridBagConstraints();
		gbc_lblLoginmessage.gridx = 3;
		gbc_lblLoginmessage.gridy = 8;
		lblLoginmessage.setText("Login unsuccessful");
		frame.getContentPane().add(lblLoginmessage, gbc_lblLoginmessage);
		lblLoginmessage.setVisible(false);
	}

	/**
	 * new code, see if can put both frames in same class
	 */
	private void initializeChatWindow() {
		frame2 = new JFrame();
		frame2.setBounds(100, 100, 544, 416);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {400, 0, 30};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame2.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblChat = new JLabel("Chat");
		GridBagConstraints gbc_lblChat = new GridBagConstraints();
		gbc_lblChat.gridheight = 11;
		gbc_lblChat.insets = new Insets(0, 0, 5, 5);
		gbc_lblChat.gridx = 0;
		gbc_lblChat.gridy = 0;
		frame2.getContentPane().add(lblChat, gbc_lblChat);
		
		txtInputtext = new JTextField();
		txtInputtext.setText("InputText");
		GridBagConstraints gbc_txtInputtext = new GridBagConstraints();
		gbc_txtInputtext.insets = new Insets(0, 0, 0, 5);
		gbc_txtInputtext.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInputtext.gridx = 0;
		gbc_txtInputtext.gridy = 11;
		frame2.getContentPane().add(txtInputtext, gbc_txtInputtext);
		txtInputtext.setColumns(10);
		
		JButton btnTalk = new JButton("Talk");
		GridBagConstraints gbc_btnTalk = new GridBagConstraints();
		gbc_btnTalk.gridx = 1;
		gbc_btnTalk.gridy = 11;
		frame2.getContentPane().add(btnTalk, gbc_btnTalk);
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
