package gui;

import java.util.*;
import model.*;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import controller.*;

public class TabelModelRecieveSupplyOrder extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<SupplyOrderLine> supplyOrderLines;
	private SupplyOrderCtrl sCtrl = new SupplyOrderCtrl();

	public TabelModelRecieveSupplyOrder(){
		supplyOrderLines = new ArrayList<>();
	}

	@Override
	public int getColumnCount(){
		return 5;
	}

	@Override
	public int getRowCount(){
		return supplyOrderLines == null ? 0 : supplyOrderLines.size();
	}

	@Override
	public String getColumnName(int ix) {
		System.out.print("getColumnName(" + ix + ") ");
		switch(ix) {
		case 0: return "Varenummer";
		case 1: return "Beskrivelse";
		case 2: return "Antal";
		case 3: return "Pris pr. stk";
		case 4: return "Pris total";
		
		
		default: System.out.println("???");return "???";
		}
	}

	@Override
	public Object getValueAt(int row, int col){
		
		switch(col){
		case 0: return supplyOrderLines.get(row).getProduct().getProductNumber();
		case 1: return supplyOrderLines.get(row).getProduct().getDescription();
		case 2: return supplyOrderLines.get(row).getAmount();
		case 3: return supplyOrderLines.get(row).getProduct().getPurchasePrice();
		case 4: return sCtrl.getTotalPrice(supplyOrderLines.get(row).getProduct());
		
		default: return null;
		}
		
		
		/*if (col == 0){
			return supplyOrderLines.get(row).getName();
		}
		else {
			return supplyOrderLines.get(row).getLines().size();
		}*/

	}

	public void setData(ArrayList<SupplyOrderLine> data) {
		this.supplyOrderLines = data;
		super.fireTableDataChanged();
	}

	public SupplyOrderLine getData(int selectedRow) {
		if(selectedRow >= 0 && selectedRow < supplyOrderLines.size()) {
			return this.supplyOrderLines.get(selectedRow);
		}
		return null;
	}



}