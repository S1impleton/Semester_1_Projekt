package ui.tui;

import model.Order;
import ui.libTUI.ConverterIF;

public class OrderConverter implements ConverterIF<Order> {
	
	@Override
	public String convertToString(Order order) {
		if(! (order instanceof Order)) {
			return "[Sorry, not a Order object]";
		}
		Order o = (Order)order;
		return "Nummer: " + o.getNumber() + ", produkter: " + o.getOrderLines().toString() + ", pris: " + o.sumTotalPrice() + ", kunde: " + o.getCustomer().getName();
		}
}
