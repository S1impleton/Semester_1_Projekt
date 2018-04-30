package ui.TableModels;

import java.util.*;
import model.*;

import javax.swing.table.DefaultTableModel;
import controller.*;

public class TableModelRecieveSupplyOrder extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<SupplyOrderLine> supplyOrderLines;
	private SupplyOrderCtrl sCtrl = new SupplyOrderCtrl();
	private ArrayList<Boolean> selections;

	public TableModelRecieveSupplyOrder(){
		supplyOrderLines = new ArrayList<>();
		selections = new ArrayList<>();
		loadSelections();
	}

	private void loadSelections() {
		for (int i = 0; i < supplyOrderLines.size(); i++){
			selections.add(false);
		}
	}

	@Override
	public int getColumnCount(){
		return 6;
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
		case 5: return "Selection";
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
		case 5: return selections.get(row);
		default: return null;
		}
	}

	public void setData(ArrayList<SupplyOrderLine> data) {
		this.supplyOrderLines = data;
		loadSelections();
		super.fireTableDataChanged();
	}

	public SupplyOrderLine getData(int selectedRow) {
		if(selectedRow >= 0 && selectedRow < supplyOrderLines.size()) {
			return this.supplyOrderLines.get(selectedRow);
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == 5){
			return true;
		}
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (aValue instanceof Boolean && column == 5) {
			selections.set(row, (boolean)aValue);
		}
	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return Integer.class;
		case 3:
			return Double.class;
		case 4: 
			return Double.class;
		case 5:
			return Boolean.class;
		default: return String.class;    
		}
	}

	public void setAllSelected() {
		setData(supplyOrderLines);
		for (int i = 0; i < selections.size(); i++){
			setValueAt(true, i, 5);
		}
	}

	public void SetAllUnselected(){
		setData(supplyOrderLines);
		for (int i = 0; i < selections.size(); i++){
			setValueAt(false, i, 5);
		}
	}
}