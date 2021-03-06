package controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import model.*;

public class SupplyOrderCtrl {
	private SupplyOrderContainer supplyOrderCon;
	private  HashMap <Supplier, SupplyOrder> currSupplyOrders;
	private StockCtrl sCtrl;

	public SupplyOrderCtrl(){
		supplyOrderCon = SupplyOrderContainer.getInstance();
		sCtrl = new StockCtrl();
	}

	/**
	 * Returns a list of Products which needs to be ordered.
	 * This method checks whether or not they are already ordered, by checking the supplyOrderLine
	 * attribute isRecieved, in our list of SupplyOrders, which holds all current orders for new Products.
	 * @return list of Products we need, but is not yet ordered.
	 */
	public ArrayList<Product> findPossibleRestockProducts(){
		ProductCtrl productCtrl = new ProductCtrl();
		ArrayList<Product> restockProducts = productCtrl.findRestockProducts();
		ArrayList<SupplyOrder> temp = supplyOrderCon.getAll();
		for(SupplyOrder s : temp){
			ArrayList<SupplyOrderLine> currSOLs = s.getSupplyOrderLines();
			for (SupplyOrderLine so : currSOLs){
				if(!so.isRecieved() && restockProducts.contains(so.getProduct())){
					restockProducts.remove(so.getProduct());
				}
			}
		}
		return restockProducts;
	}

	/** 
	 * Calculates the amount of an product, which is to be ordered.
	 * Based on maxAmount set for the Product, and the current inventory.
	 * @param p is the Product we need.
	 * @return amount to reorder.
	 */
	public int amountToReorder(Product p){
		return p.getMaxAmount() - p.getQuantity();
	}
	
	public double getTotalPrice(Product p){
		return amountToReorder(p) * p.getPurchasePrice();
	}

	/** 
	 * This method creates a HashMap, where the SupplyOrder is stored with the Products that has been approved for 
	 * restocking. It takes a Supplier which is the key, and the SupplyOrder as value, which
	 * holds the Products which is ordered from that same Supplier whom is the key.
	 * It checks for each Product, if there is already an Supplier added. If there is, 
	 * it will add the current holding Product to that list. If not, it will create a new Key with the Supplier for the given
	 * Product.
	 * @param takes an ArrayList<Product> of approvedProducts, which is the products the user has confirmed for restocking.
	 * @return a HashMap, where the approved products is ordered by Supplier(key)
	 */
	public HashMap<Supplier, SupplyOrder> sendApprovedProductsToOrder(ArrayList<Product> approvedProducts){
		HashMap<Supplier, SupplyOrder> tempSupplyOrders = new HashMap<>();
		for(Product p : approvedProducts){
			Supplier sup = p.getSupplier();
			int amount = amountToReorder(p);
			SupplyOrderLine sol = new SupplyOrderLine(p, amount);
			if(tempSupplyOrders.containsKey(sup)){
				tempSupplyOrders.get(sup).addSupplyOrderLine(sol);
			}
			else{
				SupplyOrder tempNewSupplyOrder = new SupplyOrder(sup);
				tempNewSupplyOrder.addSupplyOrderLine(sol);
				tempSupplyOrders.put(sup, tempNewSupplyOrder);
			}
		}
		//addHashMapToContainer(tempSupplyOrders);
		currSupplyOrders = tempSupplyOrders;
		return tempSupplyOrders; 
	}
	
	public HashMap<Supplier, SupplyOrder> getCurrentSupplyOrder(){
		return currSupplyOrders;
	}

	/**
	 * Adds the HashMap containing the SupplyOrders for each Supplier, to the
	 * SupplyOrderContainer.
	 * @param HashMap<Supplier, SupplyOrder>
	 */
	public void addHashMapToContainer(HashMap<Supplier, SupplyOrder> tempSupplyOrders){
		Collection<SupplyOrder> c = tempSupplyOrders.values();
		Iterator<SupplyOrder> it = c.iterator();
		while(it.hasNext()){
			SupplyOrder temp = it.next();
			supplyOrderCon.addSupplyOrder(temp);
		}
	}

	/**
	 * @return all SupplyOrders from the SupplyOrderContainer.
	 */
	public ArrayList<SupplyOrder> getAllSupplyOrders(){
		return supplyOrderCon.getAll();
	}

	/**
	 * This method checks for all orders which are not yet recieved.
	 * @return a list of all SupplyOrders that have not been marked as recieved by the iventory workers.
	 */
	public ArrayList<SupplyOrderLine> getNotRecievedSupplyOrderLines(){
		ArrayList<SupplyOrder> supplyOrders = getAllSupplyOrders();
		ArrayList<SupplyOrderLine> sol = new ArrayList<>();
		for (SupplyOrder s : supplyOrders){
			for(SupplyOrderLine so: s.getSupplyOrderLines()){
				if (!so.isRecieved()){
					sol.add(so);
				}
			}
		}
		return sol;
	}

	/**
	 * This method sets the given SupplyOrderLine to recieved, when the inventory has recieved the
	 * given Product.
	 * @param takes an SupplyOrderLine.
	 */
	public void setSOLToRecieved(SupplyOrderLine supplyOrderLine){
		supplyOrderLine.setRecieved(true);
		Product p = supplyOrderLine.getProduct();
		sCtrl.updateStockRecieved(p, amountToReorder(p));
		/*ArrayList<SupplyOrderLine> sol = getNotRecievedSupplyOrderLines();
		for (SupplyOrderLine s: sol){
			if (s.equals(supplyOrderLine)){
				s.setRecieved(true);
				Product p = s.getProduct();
				sCtrl.updateStockRecieved(p, amountToReorder(p));
			}
		}*/
	}
}
