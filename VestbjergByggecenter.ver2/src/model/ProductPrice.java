package model;

import java.time.LocalDate;

public class ProductPrice {
	private double purchasePrice;
	private double salePrice;
	private LocalDate fromDate;

	public ProductPrice(double purchasePrice, double salePrice){
		this.setPurchasePrice(purchasePrice);
		this.setSalePrice(salePrice);
		fromDate = LocalDate.now(); 
		this.setFromDate(fromDate);
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	

	public LocalDate getFromDate() {
		return fromDate;
	}

	/**
	 * Sets the date for the purchasePrice, which is the date, that it was updated
	 * @param fromDate is the LocalDate class.
	 */

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
}
