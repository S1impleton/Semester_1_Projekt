package model;

public class OrderLine {
	private Product product;
	private int amount;
	
	public OrderLine(Product product, int amount){
		this.product = product;
		this.amount = amount;
	}
	
	public Product getProduct(){
		return product;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public int setAmount(int amount){
		return this.amount = amount;
	}
	
	@Override
	public String toString(){
		return "beskrivelse: " + product.getDescription() + ", antal: " + amount;
		
	}
}
