package test.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import exceptions.model.CustomerAlreadyExistsException;
import exceptions.model.CustomerDoesNotExistsException;
import model.Customer;
import model.CustomerContainer;
import model.Order;
import model.OrderLine;
import model.Product;
import model.Supplier;

public class OrderTest {
	private Customer c1;
	private Order o1, o2;
	private Product p1, p2;
	private Supplier s1;
	private Order order;
	private OrderLine ol1, ol2;
	private CustomerContainer cCon;

	@Before
	public void setUp() throws Exception {
		cCon = CustomerContainer.getInstance();

		c1 = new Customer("Arne", "Sonjavej 10", "arne@gmail.com", "18451298", "privat");
		cCon.addCustomer(c1);

		s1 = new Supplier("S�ren");
		p1 = new Product(10, 20, "123689", "S�m", "V�rkt�j's redskaber", "ByggeCenter", 500, 50, 1000, s1);
		p2 = new Product(50, 200, "5404940", "Hammer", "V�rkt�j's redskaber", "ByggeCenter", 10, 5, 15, s1);
		order = new Order();

		ol1 = order.addProductToOrder(p1, 2);
		ol2 = order.addProductToOrder(p2, 5); 
	}

	@After
	public void tearDown() throws Exception {
		cCon.clear();
		order.clear();
	}

	/**
	 * Testing that the total price will add the price for all products.
	 */
	@Test
	public void sumTotalPriceTest() {
		assertEquals(1040, order.sumTotalPrice(), 0);
	} 

	/**
	 * Testing that the expiryDate will return null when the boolean is true.
	 */
	@Test
	public void setExpiryDateTest(){
		order.setExpiryDate(true);
		assertNull(order.getExpiryDate());	
	}

	/**
	 * Testing that the expiryDate will be set when it's boolean is false
	 */
	@Test
	public void setExpiryDateFromRegDateTest(){
		order.setRegDate();
		order.setExpiryDate(false);
		assertNotNull(order.getExpiryDate());
	}

	/**
	 * Testing that Products is being added to the orderLine
	 * Testing that the same product will not be added. but the amount will increase instead. 
	 */
	@Test
	public void addProductToOrderTest(){
		OrderLine ol3 = ol1;
		OrderLine ol4 = order.addProductToOrder(p2, 50);
		OrderLine ol5 = order.addProductToOrder(p1, 8);

		assertEquals(ol1.getProduct(), ol3.getProduct());
		assertNotEquals(ol2.getAmount(), ol4.getAmount());
		assertEquals(10, ol1.getAmount());
	}

	/**
	 * Testing that an existing customer is added to the order
	 * @throws CustomerAlreadyExistsException 
	 * @throws CustomerDoesNotExistsException 
	 */
	@Test
	public void addCustomerToOrderTest() throws CustomerAlreadyExistsException, CustomerDoesNotExistsException{
		Customer c2 = order.addCustomerToOrder(c1.getPhone());
		Customer c3 = new Customer("Tobias", "sofiesvej 25", "Tobias@mail.dk", "94960323", "privat");

		assertEquals(c1, c2);
		assertNotEquals(c2, c3);
	}
}
