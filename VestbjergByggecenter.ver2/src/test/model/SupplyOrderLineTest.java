package test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Product;
import model.Supplier;
import model.SupplyOrderLine;

public class SupplyOrderLineTest {
	SupplyOrderLine sol1, sol2, sol3, sol4, sol5;
	Product p1, p2, p3, p4, p5;
	Supplier s1, s2, s3;

	@Before
	public void setUp() {
		s1 = new Supplier("Bauhaus");
		s2 = new Supplier("Silvan");
		s3 = new Supplier("Jem og Fix");

		p1 = new Product(800, 1000, "5", "k�lk", "vinter", "hylde4", 25, 5, 30, s3);
		p2 = new Product(1000, 1200, "6", "hurtig k�lk", "vinter", "hylde 5", 18, 4, 25, s1);
		p3 = new Product(100, 300, "17", "kaffemaskine", "hvidevarer", "hylde 17", 5, 6, 10, s2);
		p4 = new Product(1300, 1500, "19", "vaskemaskine", "h�rdeHvidevarer", "hylde 19", 4, 4, 5, s1);
		p5 = new Product(3000, 3200, "52", "den sl�r h�rdt", "v�rkt�j", "Hylde 52", 10, 2, 15, s3);

		sol1 = new SupplyOrderLine(p1, 11);
		sol2 = new SupplyOrderLine(p2, 22);
		sol3 = new SupplyOrderLine(p3, 33);
		sol4 = new SupplyOrderLine(p4, 44);
		sol5 = new SupplyOrderLine(p5, 55);
	}

	@Test
	public void getProduct() {
		assertEquals(p1, sol1.getProduct());
		assertEquals(p2, sol2.getProduct());
		assertEquals(p3, sol3.getProduct());
		assertEquals(p4, sol4.getProduct());
		assertEquals(p5, sol5.getProduct());
		assertNotEquals(p2, sol1.getProduct());
		assertNotEquals(p4, sol2.getProduct());
		assertNotEquals(p5, sol3.getProduct());
		assertNotEquals(p1, sol4.getProduct());
	}

	@Test
	public void getAmount() {
		assertTrue(11 == sol1.getAmount());
		assertTrue(22 == sol2.getAmount());
		assertTrue(33 == sol3.getAmount());
		assertTrue(44 == sol4.getAmount());
		assertTrue(55 == sol5.getAmount());
		assertFalse(22 == sol1.getAmount());
		assertFalse(55 == sol2.getAmount());
		assertFalse(11 == sol3.getAmount());
		assertFalse(22 == sol4.getAmount());
	}
}
