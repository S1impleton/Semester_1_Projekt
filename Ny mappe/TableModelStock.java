package gui;

import java.util.*;
import model.*;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TableModelStock extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<StockProduct> stockProducts;

	public TableModelStock(){
		stockProducts = new ArrayList<>();
	}

	@Override
	public int getColumnCount(){
		return 7;
	}

	@Override
	public int getRowCount(){
		return stockProducts == null ? 0 : stockProducts.size();
	}

	@Override
	public String getColumnName(int ix) {
		System.out.print("getColumnName(" + ix + ") ");
		switch(ix) {
		case 0: System.out.println("Name"); return "Varenummer";
		case 1: System.out.println("Beskrivelse");return "Beskrivelse";
		case 2: System.out.println("Varegruppe");return "Varegruppe";
		case 3: System.out.println("Placering");return "Placering";
		case 4: System.out.println("Afdeling");return "Afdeling";
		case 5: System.out.println("LeverandÝr");return "LeverandÝr";
		case 6 : System.out.println("Antal");return "Antal";
		
		default: System.out.println("???");return "???";
		}
	}

	@Override
	public Object getValueAt(int row, int col){
		
		switch(col){
		case 0: return stockProducts.get(row).getProduct().getProductNumber();
		case 1: return stockProducts.get(row).getProduct().getDescription();
		case 2: return stockProducts.get(row).getProduct().getProductGroup();
		case 3: return stockProducts.get(row).getPlacement();
		case 4: return stockProducts.get(row).getLocation();
		case 5: return stockProducts.get(row).getProduct().getSupplier().getName();
		case 6: return stockProducts.get(row).getQty();
		
		default: return null;
		}
		
		
		/*if (col == 0){
			return stockProducts.get(row).getName();
		}
		else {
			return stockProducts.get(row).getLines().size();
		}*/

	}

	public void setData(ArrayList<StockProduct> data) {
		this.stockProducts = data;
		super.fireTableDataChanged();
	}

	public StockProduct getData(int selectedRow) {
		if(selectedRow >= 0 && selectedRow < stockProducts.size()) {
			return this.stockProducts.get(selectedRow);
		}
		return null;
	}
	
	
	



}