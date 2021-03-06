package model;

public class Product{
	private String productNumber;
	private String description;
	private String productGroup;
	private String location; 
	private int quantity;
	private int minAmount;
	private int maxAmount;
	private ProductPrice productPrice;
	private Supplier supplier;

	public Product(double purchasePrice, double salePrice, String productNumber, String description, String productGroup, 
			String location, int quantity, int minAmount, int maxAmount, Supplier supplier){
		productPrice = new ProductPrice(purchasePrice, salePrice);
		this.productNumber = productNumber;
		this.description = description;
		this.productGroup = productGroup;
		this.location = location;
		this.quantity =  quantity;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.supplier = supplier;
	}

	public Product(){

	}

	public String getProductNumber() {
		return productNumber;
	}

	public String getDescription() {
		return description;
	}

	public String getProductGroup(){
		return productGroup;
	}

	public String getLocation(){
		return location;
	}

	public double getPurchasePrice(){
		return productPrice.getPurchasePrice();
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public int getMinAmount() {
		return minAmount;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getSalePrice(){
		return productPrice.getSalePrice();
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
