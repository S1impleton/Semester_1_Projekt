package ui.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import controller.OrderCtrl;
import ui.TableModels.MyTableOrder;

public class ShowAllOrdersView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private MyTableOrder myTable = new MyTableOrder();
	private JButton btnBack;
	private JTextField txtQuery;
	private TableRowSorter<MyTableOrder> sorter;

	private OrderCtrl orderCtrl = new OrderCtrl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {	
			public void run() {
				try {
					ShowAllOrdersView frame = new ShowAllOrdersView();
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
	public ShowAllOrdersView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 578);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		txtQuery = new JTextField();
		txtQuery.setToolTipText("s\u00F8g");
		txtQuery.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filter(txtQuery.getText());
			}
		});

		txtQuery.setBounds(40, 15, 254, 22);
		txtQuery.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Click column header to sort");
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(Constants.listXCoordinate, Constants.listYCoordinate, Constants.listWidth, Constants.btnMenuLeftHeight);

		table = new JTable();
		table.setToolTipText("Click Product to select");
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);

		table.setModel(myTable);
		table.getColumnModel().getColumn(0).setPreferredWidth(330);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		sorter = new TableRowSorter<MyTableOrder>();

		myTable.setData(orderCtrl.getAllOrders());
		table.setAutoCreateRowSorter(true);

		btnBack = new JButton("Tilbage");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCloseClicked();
			}
		});
		btnBack.setBounds(Constants.btnBackXCoordinate, Constants.btnBackYCoordinate, Constants.btnBackWidth, Constants.btnBackHeight);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(txtQuery);
		contentPane.add(scrollPane);
		contentPane.add(btnBack);
	}

	protected void filter(String query) {
		TableRowSorter<MyTableOrder> tr = new TableRowSorter<MyTableOrder>(myTable);
		table.setRowSorter(tr);

		tr.setRowFilter(RowFilter.regexFilter("(?i)" + query));

		myTable.setData(orderCtrl.getAllOrders());
	} 

	protected void btnCloseClicked() {
		MainMenuView.getInstance().goBack();
	}
}
