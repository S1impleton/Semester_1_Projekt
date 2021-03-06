package test.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Product;
import model.ProductContainer;
import model.Supplier;

public class ProductContainerTest {
	private ProductContainer pCon;
	private Product p1, p2, p3, p4, p5;
	private Supplier s1, s2, s3;

	@Before
	public void setUp() throws Exception {
		pCon = pCon.getInstance();

		s1 = new Supplier("Bauhaus");
		s2 = new Supplier("Silvan");
		s3 = new Supplier("Jem og Fix");
		p1 = new Product(800, 1000, "5", "k�lk", "vinter", "hylde4", 25, 5, 30, s3);
		p2 = new Product(1000, 1200, "6", "hurtig k�lk", "vinter", "hylde 5", 18, 4, 25, s1);
		p3 = new Product(100, 300, "17", "kaffemaskine", "hvidevarer", "hylde 17", 5, 6, 10, s2);
		p4 = new Product(1300, 1500, "19", "vaskemaskine", "h�rdeHvidevarer", "hylde 19", 4, 4, 5, s1);
		p5 = new Product(3000, 3200, "52", "den sl�r h�rdt", "v�rkt�j", "Hylde 52", 10, 2, 15, s3);
	}

	@After
	public void tearDown() throws Exception {
		pCon.clear();
	}

	@Test
	public void testGetInstance() {
		assertNotNull(ProductContainer.getInstance());
	}

	@Test
	public void testGetAll(){
		assertTrue(pCon.getAll().size() == 0);
		assertNotNull(pCon.getAll());
	}

	@Test
	public void testAddProduct(){
		pCon.addProduct(p1);
		assertTrue(pCon.getAll().size() == 1);
		pCon.addProduct(p2);
		pCon.addProduct(p3);
		assertTrue(pCon.getAll().size() == 3);
		assertFalse(pCon.getAll().size() == 2);
		assertFalse(pCon.getAll().size() == 4);	
	}

	@Test
	public void testClear(){
		pCon.addProduct(p1);	
		pCon.addProduct(p2);
		pCon.addProduct(p3);
		pCon.addProduct(p4);
		pCon.clear();
		assertTrue(pCon.getAll().size() == 0);
	}
}
