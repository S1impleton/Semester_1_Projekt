package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.model.CustomerDoesNotExistsException;
import model.*;

public class OrderCtrl {
	private ProductCtrl productCtrl = new ProductCtrl();
	private CustomerCtrl customerCtrl = new CustomerCtrl();
	private Order order = null;

	public OrderCtrl(){

	}

	public void createOrder(){
		order = new Order();
	}

	public Product findProduct(String productNumber){
		return productCtrl.findProduct(productNumber);
	}

	public void addProductToOrder(Product p, int amount){
		order.addProductToOrder(p, amount);
	}

	public Customer findCustomer(String phone){
		return customerCtrl.findCustomer(phone);
	}

	public double getTotalPrice(){
		return order.getTotalPrice();
	}
	
	public void addCustomerToOrder(Customer c) throws CustomerDoesNotExistsException{
		order.addCustomerToOrder(c.getPhone());
	}

	public void closeOrder(){
		order.setPackDate(null);
		order.setSendDate(null);
		order.setPayDate(null);
		order.setRegDate();
		OrderContainer.getInstance().addOrder(order);
		order = null;
	}

	public ArrayList<Order> getAllOrders(){
		return OrderContainer.getInstance().getAll();
	}
	
	public ArrayList<OrderLine> getAllOrderLines(){
		return order.getOrderLines();
	
	}
	
	public void removeOrderLine(OrderLine orderline){
		order.removeOrderLine(orderline);
	}
}
