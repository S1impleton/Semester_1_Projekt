package ui.gui;

import java.util.ArrayList;
import java.util.ArrayList;


import javax.swing.table.DefaultTableModel;

import model.Order;
import model.OrderLine;
import model.Product;

import model.Order;

public class MyTableCreateOrder extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<OrderLine> orders;
	private Order order;

	public MyTableCreateOrder(){
		orders = new ArrayList<>();
	}

	@Override
	public int getColumnCount(){
		return 5;
	}

	@Override
	public int getRowCount(){
		return orders == null ? 0 : orders.size();
	}

	@Override
	public String getColumnName(int ix) {
		System.out.print("getColumnName(" + ix + ") ");
		switch(ix) {
		case 0: System.out.println("Beskrivelse");return "Beskrivelse";
		case 1: System.out.println("Varenummer"); return "Varenummer";
		case 2: System.out.println("Pris"); return "Pris";
		case 3: System.out.println("Antal"); return "Antal";
		case 4: System.out.println("Total Pris"); return "Total Pris"; 
		default: System.out.println("???");return "???";
		}
	}

	@Override
	public Object getValueAt(int row, int col){
		switch(col){
		case 0: return orders.get(row).getProduct().getDescription();
		case 1: return orders.get(row).getProduct().getProductNumber();
		case 2: return orders.get(row).getProduct().getSalePrice();
		case 3: return orders.get(row).getAmount();
		case 4: return (orders.get(row).getAmount()) * (orders.get(row).getProduct().getSalePrice());
		default: return null;
		}
	}

	public void setData(ArrayList<OrderLine> data) {
		this.orders = data;
		super.fireTableDataChanged();
	}

	public OrderLine getData(int selectedRow) {
		if(selectedRow >= 0 && selectedRow < orders.size()) {
			return this.orders.get(selectedRow);
		}
		return null;
	}

}
