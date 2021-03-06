package ui.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StockMainView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Setup.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockMainView frame = new StockMainView();
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
	public StockMainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Constants.backgroundColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		ImageIcon iconReorder = new ImageIcon(getClass().getClassLoader().getResource("images/reorder.png"));
		JButton btnReorderProduct = new JButton(iconReorder);
		btnReorderProduct.setFocusPainted(false);
		btnReorderProduct.setText("Genbestilling");
		btnReorderProduct.setFont(new Font(Constants.fontName, Constants.fontStyle, Constants.fontSize));
		btnReorderProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnReorderProductClicked();
			}
		});
		btnReorderProduct.setBackground(Constants.backgroundColor);
		btnReorderProduct.setBorder(null);
		btnReorderProduct.setBounds(Constants.btnMenuTopXCoordinate, Constants.btnMenuTopYCoordinate, Constants.btnMenuTopWidth, Constants.btnMenuTopHeight);
		contentPane.add(btnReorderProduct);

		ImageIcon iconRecieve = new ImageIcon(getClass().getClassLoader().getResource("images/recieve.png"));
		JButton btnRecieveProduct = new JButton(iconRecieve);
		btnRecieveProduct.setFocusPainted(false);
		btnRecieveProduct.setText("Modtag Vare");
		btnRecieveProduct.setFont(new Font(Constants.fontName, Constants.fontStyle, Constants.fontSize));
		btnRecieveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRecieveProductClicked();
			}
		});
		btnRecieveProduct.setBackground(Constants.backgroundColor);
		btnRecieveProduct.setBorder(null);
		btnRecieveProduct.setBounds(Constants.btnMenuRightXCoordinate, Constants.btnMenuRightYCoordinate, Constants.btnMenuRightWidth, Constants.btnMenuRightHeight);
		contentPane.add(btnRecieveProduct);

		ImageIcon iconWareHouse = new ImageIcon(getClass().getClassLoader().getResource("images/warehouse.png"));
		JButton btnStockView = new JButton(iconWareHouse);
		btnStockView.setFocusPainted(false);
		btnStockView.setText("Lageroversigt");
		btnStockView.setFont(new Font(Constants.fontName, Constants.fontStyle, Constants.fontSize));
		btnStockView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStockViewClicked();
			}
		});
		btnStockView.setBackground(Constants.backgroundColor);
		btnStockView.setBorder(null);
		btnStockView.setBounds(Constants.btnMenuLeftXCoordinate, Constants.btnMenuLeftYCoordinate, Constants.btnMenuLeftWidth, Constants.btnMenuLeftHeight);
		contentPane.add(btnStockView);

		JButton btnBack = new JButton("Tilbage");
		btnBack.setFont(new Font(Constants.fontName, Constants.fontStyle, Constants.fontSize));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackClicked();
			}
		});
		btnBack.setBounds(Constants.btnBackXCoordinate, Constants.btnBackYCoordinate, Constants.btnBackWidth, Constants.btnBackHeight);
		contentPane.add(btnBack);
	}

	protected void btnStockViewClicked() {
		StockView sv = new StockView();
		MainMenuView.getInstance().activateContentPane((JPanel) sv.getContentPane(), contentPane);
	}

	protected void btnRecieveProductClicked() {
		RecieveSupplyOrderView rsov = new RecieveSupplyOrderView();
		MainMenuView.getInstance().activateContentPane((JPanel) rsov.getContentPane(), contentPane);

	}

	protected void btnBackClicked() {
		MainMenuView.getInstance().goBack();
	}

	protected void btnReorderProductClicked() {
		SupplyOrderView sov = new SupplyOrderView();
		MainMenuView.getInstance().activateContentPane((JPanel) sov.getContentPane(), contentPane);

	}



}
