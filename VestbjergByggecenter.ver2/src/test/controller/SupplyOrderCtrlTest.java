package test.controller;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import controller.SupplyOrderCtrl;
import model.Product;
import model.ProductContainer;
import model.Supplier;
import model.SupplyOrder;
import model.SupplyOrderContainer;
import model.SupplyOrderLine;

public class SupplyOrderCtrlTest {
	private Supplier s1, s2, s3, s4;
	private Product p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
	private ProductContainer pCon;
	private SupplyOrderContainer soCon;

	@Before
	public void setUp(){
		s1 = new Supplier("Bauhaus");
		s2 = new Supplier("Silvan");
		s3 = new Supplier("Jem og Fix");
		p1 = new Product(800, 1000, "5", "k�lk", "vinter", "hylde4", 25, 5, 30, s3);
		p2 = new Product(1000, 1200, "6", "hurtig k�lk", "vinter", "hylde 5", 18, 4, 25, s1);
		p3 = new Product(100, 300, "17", "kaffemaskine", "hvidevarer", "hylde 17", 5, 6, 10, s2);
		p4 = new Product(1300, 1500, "19", "vaskemaskine", "h�rdeHvidevarer", "hylde 19", 4, 4, 5, s1);
		p5 = new Product(3000, 3200, "52", "den sl�r h�rdt", "v�rkt�j", "Hylde 52", 10, 2, 15, s3);
		//Product(purchasePrice, salePrice, productNumber, description, productGroup, location, quantity, minAmount, maxAmount, supplier)
		pCon = ProductContainer.getInstance();
		pCon.addProduct(p1);
		pCon.addProduct(p2);
		pCon.addProduct(p3);
		pCon.addProduct(p4);
		pCon.addProduct(p5);
		soCon = SupplyOrderContainer.getInstance();
	}

	@Test
	public void findPossibleRestockProducts(){
		SupplyOrderCtrl sCtrl = new SupplyOrderCtrl();
		ArrayList<Product> productsTest = sCtrl.findPossibleRestockProducts();
		assertNotNull(productsTest);
		assertTrue(productsTest.size() == 1);
		assertFalse(productsTest.size() == 4);
	}

	@Test 
	public void amountToReorder(){
		SupplyOrderCtrl sCtrl = new SupplyOrderCtrl();
		assertTrue(5 == sCtrl.amountToReorder(p1));
		assertTrue(7 == sCtrl.amountToReorder(p2));
		assertTrue(5 == sCtrl.amountToReorder(p3));
		assertTrue(1 == sCtrl.amountToReorder(p4));
		assertTrue(5 == sCtrl.amountToReorder(p5));
		assertFalse(7 == sCtrl.amountToReorder(p1));
		assertFalse(4 == sCtrl.amountToReorder(p2));
		assertFalse(2 == sCtrl.amountToReorder(p3));
	}

	@Test
	public void sendApprovedProductsToOrder(){
		SupplyOrderCtrl sCtrl = new SupplyOrderCtrl();
		addMoreProducts();

		ArrayList<Product> productsRestock = sCtrl.findPossibleRestockProducts();

		//We assume that the user approved all the products.
		sCtrl.sendApprovedProductsToOrder(productsRestock);
		ArrayList<SupplyOrder> supplyOrders = sCtrl.getAllSupplyOrders();

		//Testing if each product which should be reordered is added to supplyOrders.
		for (SupplyOrder so : supplyOrders){
			ArrayList<SupplyOrderLine> sols = so.getSupplyOrderLines();
			for (SupplyOrderLine sol : sols){
				Product p = sol.getProduct();
				assertTrue(p == p3 || p == p6 || p == p8 || p == p9 || p == p10);
				assertFalse(p == p1 || p == p2 || p == p4 || p == p7); 
			}
		}
	}

	public void addMoreProducts(){
		p6 = new Product(400, 500, "06", "�kse", "v�rkt�j", "Hylde 50", 5, 12, 20, s2);  //quantity, minAmount, maxAmount, supplier)
		p7 = new Product(100, 150, "07", "Saks", "v�rkt�j", "Hylde 30", 22, 15, 44, s3);
		p8 = new Product(700, 1100, "08", "Sav", "v�rkt�j", "Hylde 11", 10, 20, 15, s3);
		p9 = new Product(350, 550, "09", "Slagbor", "v�rkt�j", "Hylde 54", 2, 30, 15, s1);
		p10 = new Product(50, 300, "10", "Ravpluks", "S�m og div.", "Hylde 32", 55, 100, 15, s1);

		s4 = new Supplier("S�m A/S");
		p11 = new Product(10, 50, "11", "1cm S�m", "S�m og div.", "Hylde 31", 200, 100, 15, s4);

		pCon.addProduct(p6);
		pCon.addProduct(p7);
		pCon.addProduct(p8);
		pCon.addProduct(p9);
		pCon.addProduct(p10);
		pCon.addProduct(p11);
	}

	@Test
	public void addHashMapToContainer(){
		SupplyOrderCtrl sCtrl = new SupplyOrderCtrl();
		addMoreProducts();
		ArrayList<Product> approvedProducts = sCtrl.findPossibleRestockProducts();
		HashMap<Supplier, SupplyOrder> tempSupplyOrders = sCtrl.sendApprovedProductsToOrder(approvedProducts);
		sCtrl.addHashMapToContainer(tempSupplyOrders);
		ArrayList<SupplyOrder> supplyOrders = soCon.getAll();

		//Testing if the hashMap contains the Suppliers that should have products to be reordered.
		assertTrue(tempSupplyOrders.containsKey(s2));
		assertTrue(tempSupplyOrders.containsKey(s3));
		assertTrue(tempSupplyOrders.containsKey(s1));
		assertFalse(tempSupplyOrders.containsKey(s4));

		//Testing if each product which should be reordered is added to supplyOrders.
		for (SupplyOrder so : supplyOrders){
			ArrayList<SupplyOrderLine> sols = so.getSupplyOrderLines();
			for (SupplyOrderLine sol : sols){
				Product p = sol.getProduct();
				assertTrue(p == p3 || p == p6 || p == p8 || p == p9 || p == p10);
				assertFalse(p == p1 || p == p2 || p == p4 || p == p7); 
			}
		}
	}

	@Test
	public void getAllSupplyOrders(){
		SupplyOrderCtrl sCtrl = new SupplyOrderCtrl();
		addMoreProducts();
		ArrayList<Product> products = sCtrl.findPossibleRestockProducts();
		sCtrl.sendApprovedProductsToOrder(products);
		ArrayList<SupplyOrder> supplyOrders = sCtrl.getAllSupplyOrders();
		assertNotNull(supplyOrders);
		//The supplyOrders should have a size of 3 as there is 3 Suppliers with products to be reordered.
		assertTrue(supplyOrders.size() == 3);
		assertFalse(supplyOrders.size() == 2);
		assertFalse(supplyOrders.size() == 4);
	}

	@Test
	public void setSOLToRecieved(){
		SupplyOrderCtrl sCtrl = new SupplyOrderCtrl();
		addMoreProducts();
		ArrayList<Product> products = sCtrl.findPossibleRestockProducts();
		sCtrl.sendApprovedProductsToOrder(products);

		ArrayList<SupplyOrder> supplyOrders = sCtrl.getAllSupplyOrders();

		SupplyOrderLine sol = supplyOrders.get(0).getSupplyOrderLines().get(0);
		sCtrl.setSOLToRecieved(sol);
		assertTrue(sol.isRecieved());
		sol = supplyOrders.get(1).getSupplyOrderLines().get(0);
		assertFalse(sol.isRecieved());
	}

	@Test
	public void getNotRecievedSupplyOrderLines(){
		SupplyOrderCtrl sCtrl = new SupplyOrderCtrl();
		addMoreProducts();
		ArrayList<Product> products = sCtrl.findPossibleRestockProducts();
		sCtrl.sendApprovedProductsToOrder(products);
		ArrayList<SupplyOrder> supplyOrders = sCtrl.getAllSupplyOrders();

		for (SupplyOrder so : supplyOrders){
			ArrayList<SupplyOrderLine> supplyOrderLines = sCtrl.getNotRecievedSupplyOrderLines();
			for (SupplyOrderLine sol : supplyOrderLines){
				assertFalse(sol.isRecieved());
			}
		}
	}

	@After
	public void TearDown(){
		pCon.clear();
		soCon.clear();
	}
}
