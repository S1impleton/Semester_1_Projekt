package ui;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CustomerCtrl;
import controller.OrderCtrl;
import controller.ProductCtrl;
import controller.SaveAndLoadFromFile;
import exceptions.model.CustomerDoesNotExistsException;
import model.Customer;
import model.OrderContainer;
import model.OrderLine;
import model.Product;
import ui.TableModels.MyTableCreateOrder;
import ui.gui.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.text.NavigationFilter;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;
import java.awt.Point;


public class CreateOrderView_FORESLAG extends JFrame {

	private JPanel contentPane;

	private JTextField phoneTxt;
	private JTextField nameTextField;
	private JTextField addressTextField;
	private JTextField zipcityTextField;
	private JTextField emailTextField;
	private JTextField customerIdTextField;
	private JTextField customerTypeTextField;
	private JTextField customerGroupTextField;
	private JTextField productIdTxt;
	private JTextField descriptionTextField;
	private JTextField productGroupTextField;
	private JTextField productStatusTextField;
	private JTextField quantityTextField;

	private JTable tableOrder;

	private OrderCtrl orderCtrl = new OrderCtrl();
	private CustomerCtrl cCtrl = new CustomerCtrl();
	private ProductCtrl pCtrl = new ProductCtrl();
	private Customer c;
	private Product p;
	private OrderContainer orderCont = OrderContainer.getInstance();
	public  MyTableCreateOrder myTable = new MyTableCreateOrder();
	private DefaultListModel listModel = new DefaultListModel();
	private DefaultListModel listModelP = new DefaultListModel();
	private JList cList;
	private JScrollPane spProd;
	private JList pList;
	private JButton btnFjern;
	private 	int cListIndex = 0;
	private int pListIndex = 0;
	private boolean isCustomerSet = false;
	private boolean isProductSet = false;
	private ArrayList<OrderLine> orderlines;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Setup.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateOrderView_FORESLAG frame = new CreateOrderView_FORESLAG();
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
	public CreateOrderView_FORESLAG(){
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		orderCtrl.createOrder();

		JPanel customerPanel = new JPanel();
		customerPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		customerPanel.setBounds(31, 60, 348, 266);
		contentPane.add(customerPanel);
		customerPanel.setLayout(null);

		cList = new JList();
		cList.setToolTipText("Klik p\u00E5 kunde for at v\u00E6lge\r\n");
		cList.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				cList.setSelectedIndex(cList.locationToIndex(e.getPoint()));
				cListIndex = cList.getSelectedIndex();
			}
		});

	

		cList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addCustomerToOrderClicked();
			}
		});
		cList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		cList.setBounds(118, 56, 197, 103);
		customerPanel.add(cList);
		cList.setModel(listModel);
		cList.setVisible(false);

		JLabel lblName = new JLabel("Navn:");
		lblName.setBounds(25, 58, 81, 14);
		customerPanel.add(lblName);

		JLabel lblPhone = new JLabel("Telefon:");
		lblPhone.setBounds(25, 33, 81, 14);
		customerPanel.add(lblPhone);

		JLabel lblAddresse = new JLabel("Adresse:");
		lblAddresse.setBounds(25, 83, 60, 14);
		customerPanel.add(lblAddresse);

		JLabel lblZipCity = new JLabel("Postnr. og by:");
		lblZipCity.setBounds(25, 109, 81, 14);
		customerPanel.add(lblZipCity);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(25, 134, 46, 14);
		customerPanel.add(lblEmail);

		JLabel lblCustomerNumber = new JLabel("Kundenummer:");
		lblCustomerNumber.setBounds(25, 159, 88, 14);
		customerPanel.add(lblCustomerNumber);

		JLabel lblCustomerType = new JLabel("Kundetype:");
		lblCustomerType.setBounds(25, 183, 81, 14);
		customerPanel.add(lblCustomerType);

		JLabel lblCustomerGroup = new JLabel("Kundegruppe:");
		lblCustomerGroup.setBounds(25, 208, 81, 14);
		customerPanel.add(lblCustomerGroup);

		phoneTxt = new JTextField();
		phoneTxt.setFont(new Font("Tahoma", Font.ITALIC, 12));
		phoneTxt.setText("s\u00F8g efter tlf.");
		phoneTxt.select(0, 15);

		phoneTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				phoneTextFieldActive();
				listKeySelect(e, cList);
			}

		});
		phoneTxt.setBounds(118, 30, 158, 20);
		customerPanel.add(phoneTxt);
		phoneTxt.setColumns(10);

		customerTypeTextField = new JTextField();
		customerTypeTextField.setBounds(118, 179, 158, 22);
		customerPanel.add(customerTypeTextField);
		customerTypeTextField.setColumns(10);

		customerGroupTextField = new JTextField();
		customerGroupTextField.setBounds(118, 204, 158, 22);
		customerPanel.add(customerGroupTextField);
		customerGroupTextField.setColumns(10);

		JButton btnAddCustomerToOrder = new JButton("Tilf\u00F8j kunde");
		btnAddCustomerToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomerToOrderClicked();
			}
		});
		btnAddCustomerToOrder.setBounds(227, 239, 109, 25);
		customerPanel.add(btnAddCustomerToOrder);

		addressTextField = new JTextField();
		addressTextField.setBounds(118, 79, 158, 22);
		customerPanel.add(addressTextField);
		addressTextField.setColumns(10);

		zipcityTextField = new JTextField();
		zipcityTextField.setBounds(118, 105, 158, 22);
		customerPanel.add(zipcityTextField);
		zipcityTextField.setColumns(10);

		nameTextField = new JTextField();
		nameTextField.setBounds(118, 54, 158, 22);
		customerPanel.add(nameTextField);
		nameTextField.setColumns(10);

		emailTextField = new JTextField();
		emailTextField.setBounds(118, 130, 158, 22);
		customerPanel.add(emailTextField);
		emailTextField.setColumns(10);

		customerIdTextField = new JTextField();
		customerIdTextField.setBounds(118, 155, 158, 22);
		customerPanel.add(customerIdTextField);
		customerIdTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Kunde");
		lblNewLabel.setBounds(31, 44, 46, 14);
		contentPane.add(lblNewLabel);

		JPanel productPanel = new JPanel();
		productPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		productPanel.setBounds(407, 60, 333, 266);
		contentPane.add(productPanel);
		productPanel.setLayout(null);
		
		spProd = new JScrollPane();
		spProd.setBounds(113, 54, 187, 109);
		productPanel.add(spProd);
		
		pList = new JList();
		pList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String productnr = (String) pList.getSelectedValue();
				StringTokenizer tokenizer = new StringTokenizer(productnr);
				productnr = tokenizer.nextToken();
				p = pCtrl.findProduct(productnr);
				setProductFields(p);
				quantityTextField.requestFocus();
			}
		});
		pList.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				pList.setSelectedIndex(pList.locationToIndex(e.getPoint()));
				pListIndex = pList.getSelectedIndex();
			}
		});
		pList.setBorder(new LineBorder(new Color(0, 0, 0)));
		spProd.setViewportView(pList);
		pList.setModel(listModelP);
		spProd.setVisible(false);
		
	
	
	
		
		JLabel lblGoodsNumber = new JLabel("Varenummer:");
		lblGoodsNumber.setBounds(25, 32, 86, 16);
		productPanel.add(lblGoodsNumber);

		JLabel lblDescription = new JLabel("Beskrivelse:");
		lblDescription.setBounds(25, 57, 86, 16);
		productPanel.add(lblDescription);

		JLabel lblGoodsGroup = new JLabel("Varegruppe:");
		lblGoodsGroup.setBounds(25, 85, 76, 16);
		productPanel.add(lblGoodsGroup);

		JLabel lblWarehouseStatus = new JLabel("Lagerstatus:");
		lblWarehouseStatus.setBounds(25, 110, 76, 16);
		productPanel.add(lblWarehouseStatus);

		productIdTxt = new JTextField();
		productIdTxt.setFont(new Font("Tahoma", Font.ITALIC, 13));
		productIdTxt.setText("s\u00F8g efter produktnummer");
		productIdTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				productIdTxtActive();
				listKeySelectProd(e, pList);
				/*p = orderCtrl.findProduct(productIdTxt.getText());

				if(p != null){

				}
				else{
					descriptionTextField.setText(null);
					productGroupTextField.setText(null);
					productStatusTextField.setText(null);
				}*/
			}
		});
		productIdTxt.setBounds(118, 30, 158, 20);
		productPanel.add(productIdTxt);
		productIdTxt.setColumns(10);

		descriptionTextField = new JTextField();
		descriptionTextField.setBounds(118, 54, 158, 22);
		productPanel.add(descriptionTextField);
		descriptionTextField.setColumns(10);

		productGroupTextField = new JTextField();
		productGroupTextField.setBounds(118, 82, 158, 22);
		productPanel.add(productGroupTextField);
		productGroupTextField.setColumns(10);

		productStatusTextField = new JTextField();
		productStatusTextField.setBounds(118, 107, 158, 22);
		productPanel.add(productStatusTextField);
		productStatusTextField.setColumns(10);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(12, 186, 56, 16);
		productPanel.add(label_1);

		JLabel lblQuantity = new JLabel("Antal: ");
		lblQuantity.setBounds(25, 171, 56, 16);
		productPanel.add(lblQuantity);

		quantityTextField = new JTextField();
		quantityTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					createOrderLineButtonClicked();
					productIdTxt.requestFocus();
				}
			}
		});
		quantityTextField.setBounds(119, 168, 61, 22);
		productPanel.add(quantityTextField);
		quantityTextField.setColumns(10);

		JButton btnAddGoodsToOrder = new JButton("Tilf\u00F8j vare");
		btnAddGoodsToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createOrderLineButtonClicked();
			}
		});
		btnAddGoodsToOrder.setBounds(224, 241, 97, 25);
		productPanel.add(btnAddGoodsToOrder);

		JLabel lblVare = new JLabel("Vare");
		lblVare.setBounds(407, 43, 56, 16);
		contentPane.add(lblVare);

		JButton createOrderBtn = new JButton("Opret ordre");
		createOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createOrderButtonClicked();
			}
		});
		createOrderBtn.setBounds(628, 500, 114, 25);
		contentPane.add(createOrderBtn);

		JScrollPane scrollPane = new JScrollPane();
	
		scrollPane.setBounds(31, 359, 709, 108);
		contentPane.add(scrollPane);

		tableOrder = new JTable();
		tableOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean isTableSelected = myTable.isTableselected();
					if(isTableSelected){
						btnFjern.setEnabled(true);
					}
					else{
						btnFjern.setEnabled(false);
					}
					
				
			}
		});
		
		scrollPane.setViewportView(tableOrder);
		tableOrder.setModel(myTable);
		
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuView.getInstance().goBack();
			}
		});
		btnBack.setBounds(31, 497, Constants.btnBackWidth, Constants.btnBackHeight);
		contentPane.add(btnBack);
		
		btnFjern = new JButton("Fjern");
		btnFjern.setEnabled(false);
		btnFjern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnFjernClicked();
			}
		});
		btnFjern.setBounds(670, 466, 70, 16);
		contentPane.add(btnFjern);

	}

	protected void btnFjernClicked() {
		for(int i =0; i < tableOrder.getRowCount(); i++){
			if ((boolean) myTable.getValueAt(i, 5)){
				orderCtrl.removeOrderLine(myTable.getData(i));
				myTable.setValueAt(false, i, 5);
			}
		myTable.setData(orderCtrl.getAllOrderLines());
		btnFjern.setEnabled(false);
		}
	}

	protected void listKeySelectProd(KeyEvent e, JList<String> jList) {
		JOptionPane jop = new JOptionPane();
		if (e.getKeyCode()==KeyEvent.VK_ENTER && !jop.isEnabled()){
			String productnr = (String) pList.getSelectedValue();
			if (productnr != null){
			StringTokenizer tokenizer = new StringTokenizer(productnr);
			productnr = tokenizer.nextToken();
			p = pCtrl.findProduct(productnr);
			
			setProductFields(p);
			spProd.setVisible(false);
			quantityTextField.requestFocus();
			}
			else {
				jop.showMessageDialog(contentPane, "Ugyldigt produktnummer");
			}
			
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN){

			if (pListIndex < jList.getModel().getSize()-1){
				pListIndex = pListIndex + 1;
				jList.setSelectedIndex(pListIndex);
				System.out.println(jList.getSelectedIndex());
			}
		}

		if (e.getKeyCode()==KeyEvent.VK_UP){

			if (pListIndex > 0){
				pListIndex = pListIndex - 1;
				jList.setSelectedIndex(pListIndex);
				System.out.println(jList.getSelectedIndex());
			}

		}


	}



	protected void productIdTxtActive() {
		//Instansiere en Customer objekt ud fra findCustomer(phoneTextField.getText()).
		//Vil v�re null indtil et gyldigt nummer er indtastet.
		Product p = pCtrl.findProduct(productIdTxt.getText());
		//Kalder metoden filterPhoneText som fylder "list" med match til det der st�r i phoneTextField.
		filterProductText(productIdTxt.getText());
		// If tjekker om listen eller phoneTextField er tom..
		if (!listModelP.isEmpty() && !(productIdTxt.getText().length() == 0)) {
			//pList.setVisible(true);
			spProd.setVisible(true);

		}
		// I s� fald �nskes listen ikke at poppe op
		else {
			//pList.setVisible(false);
			spProd.setVisible(false);
		}

		//Er der indtastet et gyldigt nummer vil c ikke l�ngere v�re null og de resterende felter udfyldes via setCustomerFields();
		if (p != null){
			setProductFields(p);

		}
		//setCustomersFields() s�tter boolean isCustomeSet til true. Hvis der redigeres phoneTextField efter der allerede er fundet
		//et match, antages der at man �nsker et andet match. Derfor cleares alle felterne med clearAllText().
		else if (isProductSet) {	
			clearProductText();;
			isProductSet = false;

		}

	}

	private void setProductFields(Product p) {
		productIdTxt.setText(p.getProductNumber());
		descriptionTextField.setText(p.getDescription());
		productGroupTextField.setText(p.getProductGroup());
		productStatusTextField.setText(Integer.toString(p.getQuantity()));
		isProductSet = true;
		spProd.setVisible(false);
	}

	protected void listKeySelect(KeyEvent e, JList<String> jList) {
		jList.setSelectedIndex(cListIndex);
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			addCustomerToOrderClicked();
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN){

			if (cListIndex < jList.getModel().getSize()-1){
				cListIndex = cListIndex + 1;
				jList.setSelectedIndex(cListIndex);
				System.out.println(jList.getSelectedIndex());
			}
		}

		if (e.getKeyCode()==KeyEvent.VK_UP){

			if (cListIndex > 0){
				cListIndex = cListIndex - 1;
				jList.setSelectedIndex(cListIndex);
				System.out.println(jList.getSelectedIndex());
			}

		}


	}

	protected void phoneTextFieldActive(){ 
		//Instansiere en Customer objekt ud fra findCustomer(phoneTextField.getText()).
		//Vil v�re null indtil et gyldigt nummer er indtastet.
		Customer c = cCtrl.findCustomer(phoneTxt.getText());
		//Kalder metoden filterPhoneText som fylder "list" med match til det der st�r i phoneTextField.
		filterPhoneText(phoneTxt.getText());
		// If tjekker om listen eller phoneTextField er tom..
		if (!listModel.isEmpty() && !(phoneTxt.getText().length() == 0)) {
			cList.setVisible(true);
		}
		// I s� fald �nskes listen ikke at poppe op
		else {
			cList.setVisible(false);
		}

		//Er der indtastet et gyldigt nummer vil c ikke l�ngere v�re null og de resterende felter udfyldes via setCustomerFields();
		if (c != null){
			setCustomerFields(c);

		}
		//setCustomersFields() s�tter boolean isCustomeSet til true. Hvis der redigeres phoneTextField efter der allerede er fundet
		//et match, antages der at man �nsker et andet match. Derfor cleares alle felterne med clearAllText().
		else if (isCustomerSet) {
			clearCustomerText();
			isCustomerSet = false;
		}

	}


	



	public void createOrderLineButtonClicked(){
		String productnr = (String) pList.getSelectedValue();
		StringTokenizer tokenizer = new StringTokenizer(productnr);
		productnr = tokenizer.nextToken();
		p = pCtrl.findProduct(productnr);
		
		if(productStatusTextField.equals(null) || productStatusTextField.equals(0)){
			System.out.println("Vare ikke p� lager");
		}
		else if (quantityTextField.getText().length() == 0){
			JOptionPane.showMessageDialog(contentPane, "Der er ikke angivet et antal");
		}
		else if (listModelP.isEmpty()){
			JOptionPane.showMessageDialog(contentPane, "Der er ikke valgt en vare");
		}
		else if(p == null){
			JOptionPane.showMessageDialog(contentPane, "Ugyldigt varenummer!");
		}
		
		else{
		
			
			String currAmount = quantityTextField.getText();
			int amount = Integer.parseInt(currAmount);
			
			orderCtrl.addProductToOrder(p, amount);
			myTable.setData(orderCtrl.getAllOrderLines());
			clearProductText();
			productIdTxt.setText("");
			productIdTxt.requestFocus();
		}
	
	}

	public void addCustomerToOrderClicked(){
		cListIndex =0;
		Customer c = cCtrl.findCustomer(phoneTxt.getText());
		if(!listModel.isEmpty()){
			String phone = (String) cList.getSelectedValue();
			phone = phone.substring(0,8);
			c = orderCtrl.findCustomer(phone);
			setCustomerFields(c);
			productIdTxt.requestFocus();
			productIdTxt.select(0, 25);
		}
		else if(phoneTxt.getText().length() == 8){
			int response = JOptionPane.showConfirmDialog(null, "�nsker du at oprette ny kunde udfra telefonnummeret: " + phoneTxt.getText() +"?", "Opret ny kunde",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
			      NewCustomerView ncd = new NewCustomerView();
			      ncd.setVisible(true);
			      ncd.carryFieldsFromPreviousFrame(phoneTxt.getText(), nameTextField.getText(), addressTextField.getText(), emailTextField.getText(), customerTypeTextField.getText());
			     // ncd.setPreviousFrame(this);
			} else if (response == JOptionPane.NO_OPTION) {
			      System.out.println("Yes button clicked");
			    } else if (response == JOptionPane.CLOSED_OPTION) {
			      System.out.println("JOptionPane closed");
			    }
		}
		else{
			JOptionPane.showMessageDialog(contentPane, "Ugyldigt kundenummer!");
		}
	}

	private void clearCustomerText(){
		//phoneTxt.setText("");
		nameTextField.setText("");
		addressTextField.setText(null);
		zipcityTextField.setText("");
		emailTextField.setText("");
		customerIdTextField.setText("");
		customerTypeTextField.setText("");
		customerGroupTextField.setText("");
	}

	private void clearProductText(){
		//productIdTxt.setText("");
		descriptionTextField.setText("");
		productGroupTextField.setText("");
		productStatusTextField.setText("");
		quantityTextField.setText("");
	}

	public void clearAllText(){
		phoneTxt.setText("");
		productIdTxt.setText("");
		clearCustomerText();
		clearProductText();
		myTable.setData(null);
		


	}

	protected void setCustomerFields(Customer c){
		cList.setVisible(false);
		phoneTxt.setText(c.getPhone());
		nameTextField.setText(c.getName());
		addressTextField.setText(c.getAddress());
		emailTextField.setText(c.getEmail());
		customerIdTextField.setText(c.getID());
		customerTypeTextField.setText(c.getType());
		isCustomerSet = true;
	}

	private void createOrderButtonClicked(){
		SaveAndLoadFromFile save = new SaveAndLoadFromFile();
		
		try {
			orderCtrl.addCustomerToOrder(orderCtrl.findCustomer(phoneTxt.getText()));
		} catch (CustomerDoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		orderCtrl.closeOrder();
		save.saveAll();
		clearAllText();
	}

	private void filterPhoneText(String filter){
		CustomerAdaptor cConverter = new CustomerAdaptor();
		ArrayList<Customer> customers = cCtrl.getAll();
		listModel.clear();
		for (Customer c : customers){
			if(c.getPhone().startsWith(filter)){
				listModel.addElement(cConverter.displayObjectInList(c));
			}

		}

	}

	private void filterProductText(String filter){
		ProductAdaptor pConverter = new ProductAdaptor();
		ArrayList<Product> products = pCtrl.getAll();
		listModelP.clear();
		for (Product p : products){
			if (p.getProductNumber().startsWith(filter)){
				listModelP.addElement(pConverter.displayObjectInList(p));
			}
		}

	}
}


