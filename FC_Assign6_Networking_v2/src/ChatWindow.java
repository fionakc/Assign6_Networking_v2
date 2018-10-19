import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;

public class ChatWindow {

	private JFrame frame2;
	private JTextField txtInputtext;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatWindow window = new ChatWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ChatWindow() {
		initialize();
		frame2.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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

}
