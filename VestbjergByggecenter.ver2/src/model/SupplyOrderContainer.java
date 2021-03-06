package model;

import java.util.ArrayList;

public class SupplyOrderContainer {
	private ArrayList<SupplyOrder> supplyOrders;
	private static SupplyOrderContainer instance; 

	private SupplyOrderContainer(){
		supplyOrders = new ArrayList<>();
	}

	public static SupplyOrderContainer getInstance(){
		if(instance == null){
			instance = new SupplyOrderContainer();
		}
		return instance;
	}

	/**
	 * Adds an existing SupplyOrder to the ArrayList of supplyOrders.
	 * @param so is the SupplyOrder, which is to be added.
	 */
	public void addSupplyOrder(SupplyOrder so){
		supplyOrders.add(so);
	}

	/**
	 * @return a copy of the ArrayList which contains all the current SupplyOrders.
	 */
	public ArrayList<SupplyOrder> getAll(){
		ArrayList<SupplyOrder> res = supplyOrders;
		return res;
	}

	/**
	 * is a clear method for testing this class.
	 * Clears the container instance.
	 */
	public void clear(){
		supplyOrders.clear();
	}

	public void restoreFromFile(SupplyOrderContainer supplyOrderContainer) {
		instance = supplyOrderContainer;
	}
}
