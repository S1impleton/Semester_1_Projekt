package ui.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

import controller.*;
import model.Product;
import ui.TableModels.TableModelCheckbox;
import ui.TableModels.TableModelSupplyOrder;

import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;

public class SupplyOrderView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModelSupplyOrder myModel = new TableModelSupplyOrder();
	private TableModelCheckbox myTable = new TableModelCheckbox();

	private SupplyOrderCtrl sCtrl = new SupplyOrderCtrl();
	private JCheckBox cb1 = new JCheckBox("V\u00E6lg alle");
	private JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Setup.setup();
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
		contentPane.setBackground(Constants.backgroundColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Genbestillingsvare");
		lblNewLabel.setFont(new Font(Constants.fontName, Constants.fontStyle, Constants.fontSize));
		lblNewLabel.setBounds(296, 25, 170, 14);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(Constants.listXCoordinate, Constants.listYCoordinate, Constants.listWidth, Constants.btnMenuLeftHeight);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(myModel);
		table.setAutoCreateRowSorter(true);
		myModel.setData(sCtrl.findPossibleRestockProducts());



		JButton btnGodkendBestilling = new JButton("Godkend Bestilling");
		btnGodkendBestilling.setFont(new Font(Constants.fontName, Constants.fontStyle, Constants.fontSize));
		btnGodkendBestilling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGodkendBestillingClicked();
			}
		});
		btnGodkendBestilling.setBounds(Constants.btnRightXCoordinate, Constants.btnRightYCoordinate, Constants.btnRightWidth, Constants.btnRightHeight);
		contentPane.add(btnGodkendBestilling);

		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.setFont(new Font(Constants.fontName, Constants.fontStyle, Constants.fontSize));
		btnTilbage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnTilbageClicked();
			}
		});
		btnTilbage.setBounds(Constants.btnBackXCoordinate, Constants.btnBackYCoordinate, Constants.btnBackWidth, Constants.btnBackHeight);
		contentPane.add(btnTilbage);


		cb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cb1Clicked();
			}
		});
		cb1.setBounds(Constants.checkAllXCoordinate, Constants.checkAllYCoordinate, Constants.checkAllWidth, Constants.checkAllHeight);
		cb1.setBackground(Constants.backgroundColor);
		contentPane.add(cb1);


		internalFrame.setBounds(168, 210, 239, 135);
		contentPane.add(internalFrame);


		JLabel lblFejl = new JLabel("FEJL!");
		internalFrame.getContentPane().add(lblFejl, BorderLayout.CENTER);
		internalFrame.setVisible(false);


	}

	protected void cb1Clicked() {
		if (cb1.isSelected()){
			myModel.setAllSelected();
		}
		else{
			myModel.SetAllUnselected();
		}

	}

	protected void btnTilbageClicked() {
		MainMenuView.getInstance().goBack();
	}

	protected void btnGodkendBestillingClicked() {

		ArrayList<Product> approvedProducts = new ArrayList<>();
		for (int i = 0; i<table.getRowCount(); i++){
			if ((boolean) myModel.getValueAt(i, 5)){
			approvedProducts.add(myModel.getData(i));
			}
		}
		
		if (approvedProducts.size() == 0){
			JOptionPane op = new JOptionPane();
			op.showMessageDialog(contentPane, "Der er ikke valgt nogen vare.", "Fejl", JOptionPane.WARNING_MESSAGE);
		}
		
		else {
			ConfirmSupplyOrderView csov = new ConfirmSupplyOrderView();
			MainMenuView.getInstance().activateContentPane((JPanel) csov.getContentPane(), (JPanel) getContentPane());
			csov.printProductsToOrder(sCtrl.sendApprovedProductsToOrder(approvedProducts));
		}
		
	}
}