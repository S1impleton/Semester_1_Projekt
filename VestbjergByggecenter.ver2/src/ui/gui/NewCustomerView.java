package ui.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerCtrl;
import controller.SaveAndLoadFromFile;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewCustomerView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameTxt;
	private JTextField addressTxt;
	private JTextField typeTxt;
	private JTextField emailTxt;
	private JTextField phoneTxt;
	private CreateOrderView co;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewCustomerView dialog = new NewCustomerView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	
	public NewCustomerView() {
		
		setBounds(100, 100, 399, 391);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNavn = new JLabel("Navn:");
		lblNavn.setBounds(60, 113, 56, 16);
		contentPanel.add(lblNavn);
		
		JLabel lblAdresse = new JLabel("Adresse:");
		lblAdresse.setBounds(60, 163, 56, 16);
		contentPanel.add(lblAdresse);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(60, 208, 56, 16);
		contentPanel.add(lblEmail);
		
		JLabel lblKundetype = new JLabel("Kundetype:");
		lblKundetype.setBounds(60, 262, 88, 16);
		contentPanel.add(lblKundetype);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(148, 110, 116, 22);
		contentPanel.add(nameTxt);
		nameTxt.setColumns(10);
		
		addressTxt = new JTextField();
		addressTxt.setBounds(148, 160, 116, 22);
		contentPanel.add(addressTxt);
		addressTxt.setColumns(10);
		
		typeTxt = new JTextField();
		typeTxt.setBounds(148, 259, 116, 22);
		contentPanel.add(typeTxt);
		typeTxt.setColumns(10);
		
		emailTxt = new JTextField();
		emailTxt.setBounds(148, 205, 116, 22);
		contentPanel.add(emailTxt);
		emailTxt.setColumns(10);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setBounds(60, 70, 56, 16);
		contentPanel.add(lblTelefon);
		
		phoneTxt = new JTextField();
		phoneTxt.setBounds(148, 67, 116, 22);
		contentPanel.add(phoneTxt);
		phoneTxt.setColumns(10);
		
		JLabel lblOpretNyKunde = new JLabel("Opret ny kunde");
		lblOpretNyKunde.setFont(new Font("Consolas", Font.BOLD, 18));
		lblOpretNyKunde.setBounds(125, 13, 164, 16);
		contentPanel.add(lblOpretNyKunde);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonClicked();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

	
	protected void okButtonClicked() {
		if(areFieldsFilled()){
		CustomerCtrl cCtrl = new CustomerCtrl();
		cCtrl.createCustomer(nameTxt.getText(), addressTxt.getText(), emailTxt.getText(), phoneTxt.getText(), typeTxt.getText());
		co.setCustomerFields(cCtrl.findCustomer(phoneTxt.getText()));
		SaveAndLoadFromFile save = new SaveAndLoadFromFile();
		save.saveAll();
		setVisible(false);
		dispose();
		}
		else{
			JOptionPane.showMessageDialog(contentPanel, "Alle felter skal udfyldes!");
			System.out.println("b�rge");
		}
	}

	public void carryFieldsFromPreviousFrame(String phone, String name, String  address, String email, String type){
		if (phone != null){
			phoneTxt.setText(phone);
		}
		if (name != null){
			nameTxt.setText(name);
		}
		if (address != null){
			addressTxt.setText(address);
		}
		if (email != null){
			emailTxt.setText(email);
		}
		if (type != null){
			typeTxt.setText(type);
		}
	}
	
	private boolean areFieldsFilled(){

		/*if(nameTxt.getText() == null){
			return false;
		}
		else if(addressTxt.getText() == ""){
			return false;
		}
		else if(emailTxt.getText() == ""){
			return false;
		}
		else if(typeTxt.getText() == ""){
			return false;
		}*/
		String[] fields = {nameTxt.getText(), addressTxt.getText(), emailTxt.getText(), typeTxt.getText()};
		for (String tf : fields){
			if (tf.length() == 0){
				return false;
			}
		}
		
		return true;
	}

	/*public void setPreviousFrame(CreateOrderView_FORESLAG cov) {
		co = cov;
		
	}*/

	public void setPreviousFrame(CreateOrderView cov) {
		co = cov;
		
	}
}
