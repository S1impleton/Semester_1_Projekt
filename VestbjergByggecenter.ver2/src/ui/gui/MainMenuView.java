package ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SaveAndLoadFromFile;
import ui.IMainMenuUI;

public class MainMenuView extends JFrame implements IMainMenuUI {

	private JPanel contentPane;
	private SaveAndLoadFromFile saveAndLoad = new SaveAndLoadFromFile();
	private Stack<JPanel> panelStack = new Stack<>();
	private static MainMenuView frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new MainMenuView().run();
	}

	@Override
	public void run() {
		try {
			Setup.setup();
			saveAndLoad.loadAll();
			frame = new MainMenuView();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public MainMenuView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Constants.backgroundColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		createBtnOrder();
		createBtnSupplier();
	}

	private void createBtnOrder() {
		ImageIcon iconOrder = new ImageIcon(getClass().getClassLoader().getResource("images/cart.png"));
		JButton btnOrder = new JButton(iconOrder);
		btnOrder.setFocusPainted(false);
		btnOrder.setText("Ordrer");
		btnOrder.setFont(new Font(Constants.fontName, Constants.fontStyle, Constants.fontSize));
		btnOrder.setMnemonic(KeyEvent.VK_O);
		btnOrder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnCreateBtnOrderClicked();
			}

		});
		contentPane.setLayout(null);


		btnOrder.setBackground(Constants.backgroundColor);
		btnOrder.setBorder(null);
		btnOrder.setBounds(Constants.btnMenuRightXCoordinate, Constants.btnMenuRightYCoordinate, Constants.btnMenuRightWidth, Constants.btnMenuRightHeight);
		getContentPane().add(btnOrder);
	}

	private void createBtnSupplier() {
		ImageIcon iconSupplier = new ImageIcon(getClass().getClassLoader().getResource("images/dolly.png"));
		JButton btnLager = new JButton(iconSupplier);
		btnLager.setFocusPainted(false);
		btnLager.setText("Lager");
		btnLager.setFont(new Font(Constants.fontName, Constants.fontStyle, Constants.fontSize));
		btnLager.setMnemonic(KeyEvent.VK_L);
		btnLager.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnCreateBtnSupplierClicked();


			}
		});

		btnLager.setBackground(Constants.backgroundColor);
		btnLager.setBorder(null);
		btnLager.setBounds(Constants.btnMenuLeftXCoordinate, Constants.btnMenuLeftYCoordinate, Constants.btnMenuLeftWidth, Constants.btnMenuLeftHeight);
		getContentPane().add(btnLager);
	}

	private void btnCreateBtnOrderClicked() {
		OrderMenuView omv = new OrderMenuView();
		activateContentPane((JPanel) omv.getContentPane(), contentPane);
	}

	private void btnCreateBtnSupplierClicked(){
		StockMainView sv = new StockMainView();
		activateContentPane((JPanel) sv.getContentPane(), contentPane);
	}

	public void goBack(){
		setContentPane(panelStack.pop());
		contentPane.setVisible(true);
		contentPane.revalidate();
	}

	public void setLastPanel(JPanel jp){
		panelStack.push(jp);
	}

	public static MainMenuView getInstance(){
		return frame;
	}

	protected void activateContentPane(JPanel newContentPane, JPanel currContentPane){
		setLastPanel(currContentPane);
		setContentPane(newContentPane);
		newContentPane.setVisible(true);
		newContentPane.revalidate();
		newContentPane.requestFocus();
	}
}
