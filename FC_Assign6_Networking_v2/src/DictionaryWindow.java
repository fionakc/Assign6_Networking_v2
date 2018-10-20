import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JLabel;

public class DictionaryWindow {

	private JFrame frame3;
	private JTextField txtWord;
	private JTextField txtDef;
	private JLabel lblMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictionaryWindow window = new DictionaryWindow();
					window.frame3.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DictionaryWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame3 = new JFrame();
		frame3.setBounds(100, 100, 576, 467);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame3.getContentPane().setLayout(gridBagLayout);
		
		txtWord = new JTextField();
		txtWord.setText("word");
		GridBagConstraints gbc_txtWord = new GridBagConstraints();
		gbc_txtWord.gridwidth = 6;
		gbc_txtWord.insets = new Insets(0, 0, 5, 5);
		gbc_txtWord.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWord.gridx = 2;
		gbc_txtWord.gridy = 1;
		frame3.getContentPane().add(txtWord, gbc_txtWord);
		txtWord.setColumns(10);
		
		JButton btnAsk = new JButton("Ask");
		GridBagConstraints gbc_btnAsk = new GridBagConstraints();
		gbc_btnAsk.insets = new Insets(0, 0, 5, 5);
		gbc_btnAsk.gridx = 10;
		gbc_btnAsk.gridy = 1;
		frame3.getContentPane().add(btnAsk, gbc_btnAsk);
		
		txtDef = new JTextField();
		txtDef.setText("def");
		GridBagConstraints gbc_txtDef = new GridBagConstraints();
		gbc_txtDef.gridheight = 7;
		gbc_txtDef.gridwidth = 6;
		gbc_txtDef.insets = new Insets(0, 0, 5, 5);
		gbc_txtDef.fill = GridBagConstraints.BOTH;
		gbc_txtDef.gridx = 2;
		gbc_txtDef.gridy = 3;
		frame3.getContentPane().add(txtDef, gbc_txtDef);
		txtDef.setColumns(10);
		
		JButton btnAddnew = new JButton("AddNew");
		GridBagConstraints gbc_btnAddnew = new GridBagConstraints();
		gbc_btnAddnew.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddnew.gridx = 3;
		gbc_btnAddnew.gridy = 11;
		frame3.getContentPane().add(btnAddnew, gbc_btnAddnew);
		
		lblMessage = new JLabel("message");
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.gridwidth = 6;
		gbc_lblMessage.insets = new Insets(0, 0, 0, 5);
		gbc_lblMessage.gridx = 2;
		gbc_lblMessage.gridy = 13;
		frame3.getContentPane().add(lblMessage, gbc_lblMessage);
	}

}
