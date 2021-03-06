package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.OrderCtrl;
import controller.SaveAndLoadFromFile;
import exceptions.model.CustomerDoesNotExistsException;
import model.Customer;
import model.OrderContainer;
import model.Product;
import ui.TableModels.MyTableCreateOrder;
import ui.gui.Constants;
import ui.gui.MainMenuView;
import ui.gui.Setup;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;

public class CreateOrderView_GAMLE extends JFrame {

	private JPanel contentPane;

	private JTextField phoneTextField;
	private JTextField nameTextField;
	private JTextField addressTextField;
	private JTextField zipcityTextField;
	private JTextField emailTextField;
	private JTextField customerIdTextField;
	private JTextField customerTypeTextField;
	private JTextField customerGroupTextField;
	private JTextField productIdTextField;
	private JTextField descriptionTextField;
	private JTextField productGroupTextField;
	private JTextField productStatusTextField;
	private JTextField quantityTextField;

	private JTable tableOrder;

	private OrderCtrl orderCtrl = new OrderCtrl();
	private Customer c;
	private Product p;
	private OrderContainer orderCont = OrderContainer.getInstance();
	private MyTableCreateOrder myTable = new MyTableCreateOrder();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Setup.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateOrderView_GAMLE frame = new CreateOrderView_GAMLE();
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
	public CreateOrderView_GAMLE(){		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		orderCtrl.createOrder();

		JPanel customerPanel = new JPanel();
		customerPanel.setBounds(70, 60, 348, 266);
		contentPane.add(customerPanel);
		customerPanel.setLayout(null);

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

		phoneTextField = new JTextField();
		phoneTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				c = orderCtrl.findCustomer(phoneTextField.getText());

				if(c != null){
					nameTextField.setText(c.getName());
					addressTextField.setText(c.getAddress());
					emailTextField.setText(c.getEmail());
					customerIdTextField.setText(c.getID());
					customerTypeTextField.setText(c.getType());

				}
				else{
					nameTextField.setText(null);
					addressTextField.setText(null);
					emailTextField.setText(null);
					customerIdTextField.setText(null);
					customerTypeTextField.setText(null);

				}
			}
		});
		phoneTextField.setBounds(118, 30, 158, 20);
		customerPanel.add(phoneTextField);
		phoneTextField.setColumns(10);

		nameTextField = new JTextField();
		nameTextField.setBounds(118, 54, 158, 22);
		customerPanel.add(nameTextField);
		nameTextField.setColumns(10);

		addressTextField = new JTextField();
		addressTextField.setBounds(118, 79, 158, 22);
		customerPanel.add(addressTextField);
		addressTextField.setColumns(10);

		zipcityTextField = new JTextField();
		zipcityTextField.setBounds(118, 105, 158, 22);
		customerPanel.add(zipcityTextField);
		zipcityTextField.setColumns(10);

		emailTextField = new JTextField();
		emailTextField.setBounds(118, 130, 158, 22);
		customerPanel.add(emailTextField);
		emailTextField.setColumns(10);

		customerIdTextField = new JTextField();
		customerIdTextField.setBounds(118, 155, 158, 22);
		customerPanel.add(customerIdTextField);
		customerIdTextField.setColumns(10);

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

		JLabel lblNewLabel = new JLabel("Kunde");
		lblNewLabel.setBounds(70, 44, 46, 14);
		contentPane.add(lblNewLabel);

		JPanel productPanel = new JPanel();
		productPanel.setBounds(430, 60, 333, 266);
		contentPane.add(productPanel);
		productPanel.setLayout(null);

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

		productIdTextField = new JTextField();
		productIdTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				p = orderCtrl.findProduct(productIdTextField.getText());

				if(p != null){
					descriptionTextField.setText(p.getDescription());
					productGroupTextField.setText(p.getProductGroup());
					productStatusTextField.setText(Integer.toString(p.getQuantity()));
				}
				else{
					descriptionTextField.setText(null);
					productGroupTextField.setText(null);
					productStatusTextField.setText(null);
				}
			}
		});
		productIdTextField.setBounds(118, 30, 158, 20);
		productPanel.add(productIdTextField);
		productIdTextField.setColumns(10);

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
		lblVare.setBounds(430, 43, 56, 16);
		contentPane.add(lblVare);

		JButton createOrderBtn = new JButton("Opret ordre");
		createOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createOrderButtonClicked();
			}
		});
		createOrderBtn.setBounds(649, 476, 114, 25);
		contentPane.add(createOrderBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 355, 693, 108);
		contentPane.add(scrollPane);

		tableOrder = new JTable();
		scrollPane.setViewportView(tableOrder);
		tableOrder.setModel(myTable);

		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuView.getInstance().goBack();
			}
		});
		btnBack.setBounds(Constants.btnBackXCoordinate, Constants.btnBackYCoordinate, Constants.btnBackWidth, Constants.btnBackHeight);
		contentPane.add(btnBack);
	}

	public void createOrderLineButtonClicked(){

		if(productStatusTextField.equals(null) || productStatusTextField.equals(0)){
			System.out.println("Vare ikke p� lager");
		}
		else{
			p = orderCtrl.findProduct(productIdTextField.getText());
			String currAmount = quantityTextField.getText();
			int amount = Integer.parseInt(currAmount);

			orderCtrl.addProductToOrder(p, amount);
			myTable.setData(orderCtrl.getAllOrderLines());
		}
	}

	public void addCustomerToOrderClicked(){

		if(nameTextField.equals(null)){
			System.out.println("Kunde ikke fundet");
		}
		else{
			c = orderCtrl.findCustomer(phoneTextField.getText());
			try {
				orderCtrl.addCustomerToOrder(c);
			} catch (CustomerDoesNotExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	public void clearAllText(){
		phoneTextField.setText("");
		nameTextField.setText("");
		addressTextField.setText("");
		zipcityTextField.setText("");
		emailTextField.setText("");
		customerIdTextField.setText("");
		customerTypeTextField.setText("");
		customerGroupTextField.setText("");
		productIdTextField.setText("");
		descriptionTextField.setText("");
		productGroupTextField.setText("");
		productStatusTextField.setText("");
		quantityTextField.setText("");
		myTable.setData(null);

	}

	private void createOrderButtonClicked(){
		SaveAndLoadFromFile save = new SaveAndLoadFromFile();
		orderCtrl.closeOrder();
		clearAllText();
		save.saveAll();
		orderCtrl.createOrder();

	}
}
