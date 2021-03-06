package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

import controller.*;
import model.Product;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SupplyOrderView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TabelModelSupplyOrder myModel = new TabelModelSupplyOrder();
	private TableModelCheckbox myTable = new TableModelCheckbox();
	
	private SupplyOrderCtrl sCtrl = new SupplyOrderCtrl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainGUI.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplyOrderView frame = new SupplyOrderView();
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
	public SupplyOrderView() {
		setTitle("Genbestil Vare");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Genbestillingsvare");
		lblNewLabel.setBounds(296, 86, 111, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 104, 578, 93);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(myTable);
		table.setAutoCreateRowSorter(true);
		myModel.setData(sCtrl.findPossibleRestockProducts());
		
		
		
		JButton btnGodkendBestilling = new JButton("Godkend Bestilling");
		btnGodkendBestilling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGodkendBestillingClicked();
			}
		});
		btnGodkendBestilling.setBounds(521, 416, 137, 23);
		contentPane.add(btnGodkendBestilling);
	}

	protected void btnGodkendBestillingClicked() {
		ArrayList<Product> approvedProducts = new ArrayList<>();
		for (int i = 0; i<table.getRowCount(); i++){
			if (table.isRowSelected(i)){
				approvedProducts.add(myModel.getData(i));
				
				
				
			}
			
	}
		ConfirmSupplyOrderView csov = new ConfirmSupplyOrderView();
		csov.setVisible(true);
		csov.printProductsToOrder(sCtrl.sendApprovedProductsToOrder(approvedProducts));
		this.setVisible(false);
		this.dispose();
		
	}
}
