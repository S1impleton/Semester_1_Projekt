package ui.tui;

import java.util.ArrayList;

import controller.OrderCtrl;
import exceptions.model.CustomerDoesNotExistsException;
import model.Customer;
import model.Order;
import model.OrderContainer;
import model.Product;
import ui.libTUI.*;

public class OrderUI{
	private OrderConverter orderConverter = new OrderConverter();
	private OrderCtrl orderCtrl = new OrderCtrl();
	private HelpText help = new HelpText();
	private TextInput ti = new TextInput();
	
	public void orderMenu(){
		boolean orderMenuActive = true;
		
		while (orderMenuActive) {
			System.out.println("--- Ordre menu ---");
			System.out.println("1. Opret ordre");
			System.out.println("2. Vis alle ordrer");
			System.out.println("9. Tilbage");
			String choice = ti.promptString("V�lg:");
			switch (choice) {
			case "1":
				createOrder();
				break;
			case "2":
				showOrders();
				break;
			case "9":
				orderMenuActive = false;
				break;
			default:
				help.notExistingCommand();
				break;
			}
		}
	}

	private void showOrders() {
		ArrayList<Order> orders = OrderContainer.getInstance().getAll();
		for(Order o : orders){
			System.out.println(orderConverter.convertToString(o));
		}
	}

	private void createOrder() {
		orderCtrl.createOrder();
		findProductsForOrder();
		findCustomerForOrder();
		orderCtrl.closeOrder();
	}

	private void findCustomerForOrder() {
		boolean findCustomerForOrder = true;
		while (findCustomerForOrder) {
			System.out.println("Tilf�j kunde til ordre");
			System.out.println("1. Eksisterende kunde");
			System.out.println("2. Ny kunde");
			String choice = ti.promptString("V�lg:");
			switch (choice) {
			case "1":
				boolean succes = false;
				while(!succes){
				String phone = ti.promptString("Indtast telefonnummer");
				Customer c = orderCtrl.findCustomer(phone);
				if (c != null){
					System.out.println("Kunde: " + c.getName());
					try {
						orderCtrl.addCustomerToOrder(c);
						//Because of time limitations, this was the quickest fix
						findCustomerForOrder = false;
						succes = true;
					} catch (Exception e) {
						try {
							throw new CustomerDoesNotExistsException();
						} catch (Exception e1) {
							e1.getMessage();
						}
					}
				}
				if (c == null){
				System.out.println("Could not find customer");
				}
				}
				break;
			case "2":
				System.out.println("Ikke implementeret");
				break;
			default:
				help.notExistingCommand();
				break;
			}
		}
	}

	private void findProductsForOrder() {
		int noOfProducts = 0;
		boolean findProductsForOrder = true;
		while (findProductsForOrder || noOfProducts == 0) {
			System.out.println("Tilf�j vare til ordre");
			System.out.println("1. Tilf�j vare");
			System.out.println("9. F�rdig");
			String choice = ti.promptString("V�lg:");
			switch (choice) {
			case "1":
				boolean succes = false;
				while(!succes){
					String productNumber = ti.promptString("Indtast varenummer");
					Product p = orderCtrl.findProduct(productNumber);
					if (p != null){
						int amount = ti.promptInt("Indtast antal");
						orderCtrl.addProductToOrder(p, amount);
						System.out.println("Ordre deltotal: " + orderCtrl.getTotalPrice());
						noOfProducts++;
						succes = true;
					}
					else {
						help.notExistingCommand();
					}
				}
				break;
			case "9":
				findProductsForOrder = false;
				break;
			default:
				help.notExistingCommand();
				break;
			}
		}
	}
}
