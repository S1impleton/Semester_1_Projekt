package model;

import java.time.LocalDate;
import java.util.ArrayList;
import exceptions.model.CustomerDoesNotExistsException;

public class Order{
	private static int autoGenNumber = 1;
	private int number;
	private LocalDate regDate;
	private LocalDate packDate;
	private LocalDate sendDate;
	private LocalDate payDate;
	private double totalPrice;
	private boolean placed;
	private LocalDate expiryDate;
	private Customer customer;
	private OrderLine orderLine;
	private ArrayList<OrderLine> orderLines;

	public Order(){
		setNumber(autoGenerateOrderNumber());
		this.regDate = null;
		this.packDate = null;
		this.sendDate = null;
		this.payDate = null;
		this.placed = false;
		this.expiryDate = null;
		orderLines = new ArrayList<OrderLine>();
		this.totalPrice = sumTotalPrice();
		customer = null;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public LocalDate getRegDate() { 
		return regDate;
	}

	public LocalDate setRegDate() {
		LocalDate today = LocalDate.now();
		return regDate = today;
	}

	public LocalDate getPackDate() { 
		return packDate;
	}

	public void setPackDate(LocalDate packDate) {
		this.packDate = packDate;
	}

	public LocalDate getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDate sendDate) {
		this.sendDate = sendDate;
	}

	public LocalDate getPayDate() {
		return payDate;
	}

	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}

	public double getTotalPrice() {
		return sumTotalPrice();
	}

	public boolean isPlaced() {
		return placed;
	}

	public void setPlaced(boolean placed) {
		this.placed = placed;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Auto generating a productNumber to the order. the id for an new order is the same as the last +1. 
	 * @return returns id for the new order.
	 */
	private int autoGenerateOrderNumber(){
		return autoGenNumber++;
	}
	
    public double sumTotalPrice(){
        return totalPrice = sumTotalPrice(orderLines.size() - 1); 
    }
    
	/**
	 * Recursive function that is adding all the products prices together and returns a total amount
	 * @param i is int
	 * @return returns the total price for the order.
	 */
	private double sumTotalPrice(int i) {
		double temp = 0;
        
        if(i >= 0 && i <= orderLines.size()){
            OrderLine ol = orderLines.get(i);
            Product p = ol.getProduct();
            temp = p.getSalePrice() * ol.getAmount();
            return temp + sumTotalPrice(i - 1);
        }
        else {
            return temp;
        }
	} 
	
	/**
	 * setting the expiryDate depending on whether a discount has been placed.
	 * if the order has been the expiaryDate for the discount is hard coded to 14 days.  
	 * @param placed is boolean
	 * @return returns the expiryDate.
	 */
	public LocalDate setExpiryDate(boolean placed) { 
		if(placed){
			expiryDate = null;
		}
		if(!placed){
			expiryDate = regDate.plusDays(14);
		}
		return expiryDate;
	}
	
	/**
	 * Adding a Product to the order, pending on if the product already exists
	 * if it does exist it'll add the two amounts together into the product already existing in the order. 
	 * @param p is Product
	 * @param amount is the number wanted for the specific product.
	 * @return returns using the method createOrderLine(p, a); 
	 */
    public OrderLine addProductToOrder(Product p, int amount){
        if(orderLines.size() != 0){
            for(OrderLine orderLine : orderLines){
                if(orderLine.getProduct().equals(p)){
                    int temp = orderLine.getAmount() + amount;
                    orderLine.setAmount(temp);
                    return orderLine;
                } else {
                    return createOrderLine(p, amount);
                }
            }
        }
        else {
            return createOrderLine(p, amount);
        }
        return null;
    }
    
    /**
     * Creates an OrderLine object and adding it to the orderLine List.
     * @param p is the product
     * @param amount is the number wanted for the specific product
     * @return
     */
    private OrderLine createOrderLine(Product p, int amount){
    	OrderLine ol = new OrderLine(p, amount);
        orderLines.add(ol);
        return ol;
    }

    /**
     * Adds a Existing Customer to the order using the CustomerContainer.
     * @param phone is the phone number of the customer. 
     * @return returns the found customer.
     */
	public Customer addCustomerToOrder(String phone) throws CustomerDoesNotExistsException{
		try{
			CustomerContainer customerCont = CustomerContainer.getInstance();
			return customer = customerCont.findCustomer(phone);
		} catch (Exception e) {
			throw new CustomerDoesNotExistsException();
		}
	}

	public void clear() {
		orderLines.clear();
	}

	public Customer getCustomer() {
		return customer;
	}

	public ArrayList<String> getProducts(){
		ArrayList<String> p = new ArrayList<>();
		String product = null;
		for(int i = 0; i < orderLines.size(); i++){
		 product =  orderLines.get(i).getProduct().getDescription();
		 p.add(product);
		}
		return p; 
		//return orderLines.iterator().next().getProduct();
	}
	
	public ArrayList<Integer> getAmount(){
		ArrayList<Integer> ia = new ArrayList<>();
		int amount = 0;
		for(int i = 0; i < orderLines.size(); i++){
		 amount = orderLines.get(i).getAmount();
		 ia.add(amount);
		}
		return ia; 	
	}
	
	public OrderLine getOrderLine(){
		return orderLine;
	}
	public ArrayList<OrderLine> getOrderLines() {
		return new ArrayList<>(orderLines);
	}
}
