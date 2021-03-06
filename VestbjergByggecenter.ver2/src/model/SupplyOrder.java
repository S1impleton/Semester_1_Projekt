package model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SupplyOrder {
	private ArrayList<SupplyOrderLine> supplyOrderLines;
	private Supplier supplier;
	private LocalDate date;

	public SupplyOrder(Supplier supplier){
		supplyOrderLines = new ArrayList<>();
		setSupplier(supplier);
		date = LocalDate.now();
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void addSupplyOrderLine(SupplyOrderLine sol){
		supplyOrderLines.add(sol);
	}

	public ArrayList<SupplyOrderLine> getSupplyOrderLines(){
		ArrayList<SupplyOrderLine> res = supplyOrderLines;
		return res;
	}
}
