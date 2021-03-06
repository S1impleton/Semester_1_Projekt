package gui;

import controller.*;
import model.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;

public class StockView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private TableModelStock myTable = new TableModelStock();
	private JButton btnClose;
	private JTextField txtQuery;
	private TableRowSorter<TableModelStock> sorter;
	
	
	private StockCtrl stockCtrl = new StockCtrl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockView frame = new StockView();
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
	public StockView() {
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
					System.out.println("Hey");
					filter(txtQuery.getText());
				}
			});
		
			txtQuery.setBounds(12, 17, 254, 22);
			txtQuery.setColumns(10);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setToolTipText("Click column header to sort");
			scrollPane.setForeground(Color.BLACK);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBounds(12, 52, 693, 431);
			
			table = new JTable();
			table.setToolTipText("Click Product to select");
			table.setBackground(Color.WHITE);
			scrollPane.setViewportView(table);
			
			table.setModel(myTable);
			sorter = new TableRowSorter<TableModelStock>();
			
			myTable.setData(stockCtrl.getAllStockProducts());
			table.setAutoCreateRowSorter(true);
			
			btnClose = new JButton("Close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btnCloseClicked();
				}
			});
			btnClose.setBounds(406, 493, 190, 25);
			contentPane.setLayout(null);
			contentPane.setLayout(null);
			contentPane.add(txtQuery);
			contentPane.add(scrollPane);
			contentPane.add(btnClose);
		}

	protected void filter(String query) {
	 
		TableRowSorter<TableModelStock> tr = new TableRowSorter<TableModelStock>(myTable);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));
		
		myTable.setData(stockCtrl.getAllStockProducts());
		}
		
		
		
	

	protected void btnCloseClicked() {
		this.setVisible(false);
		this.dispose();
		
	}
	}


