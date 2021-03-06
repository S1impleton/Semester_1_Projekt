package ui.gui;

import model.Customer;
import model.CustomerContainer;
import model.Product;
import model.ProductContainer;
import model.Stock;
import model.StockProduct;
import model.Supplier;

public class Setup {

	public static void setup(){
		Supplier s1, s2, s3;
		Product p1, p2, p3, p4, p5, p6, p7, p8, p9;
		StockProduct sp1, sp2, sp3, sp4, sp5, sp6, sp7, sp8, sp9;
		ProductContainer pCon;
		Stock stock;
		Customer c1, c2, c3;
		CustomerContainer cCon;

		s1 = new Supplier("Bauhaus");
		s2 = new Supplier("Silvan");
		s3 = new Supplier("Jem og Fix");
		p1 = new Product(1500, 2000, "1", "snescooter", "vinter", "Hylde 1", 10, 4, 15, s3);
		p2 = new Product(800, 1000, "2", "k�lk", "vinter", "hylde4", 5, 20, 30, s3);
		p3 = new Product(1000, 1200, "3", "hurtig k�lk", "vinter", "hylde 5", 3, 4, 25, s1);
		p4 = new Product(100, 300, "4", "kaffemaskine", "hvidevarer", "hylde 17", 5, 6, 10, s2);
		p5 = new Product(1300, 1500, "5", "vaskemaskine", "h�rdeHvidevarer", "hylde 19", 4, 4, 5, s1);
		p6 = new Product(3000, 3200, "6", "den sl�r h�rdt", "v�rkt�j", "Hylde 52", 10, 2, 15, s3);
		p7 = new Product(20, 30, "102", "Sp�r - 4Meter", "ByggeMaterialer", "r�kke 16", 16, 40, 100, s1);
		p8 = new Product(450, 899, "140", "H�ndvask, hvid, 40cm", "K�kken", "Hylde 74", 4, 10, 15, s2);
		p9 = new Product(120, 250, "13", "Sneskovl - 35cm", "vinter", "Hylde 6", 29, 40, 65, s3);

		pCon = ProductContainer.getInstance();
		pCon.addProduct(p1);
		pCon.addProduct(p2);
		pCon.addProduct(p3);
		pCon.addProduct(p4);
		pCon.addProduct(p5);
		pCon.addProduct(p6);
		pCon.addProduct(p7);
		pCon.addProduct(p8);
		pCon.addProduct(p9);

		sp1 = new StockProduct(p1, "Reol A : Hylde 1", "Afd: 1");
		sp2 = new StockProduct(p2, "Reol B : Hylde 2", "Afd: 1");
		sp3 = new StockProduct(p3, "Reol B : Hylde 3", "Afd: 1");
		sp4 = new StockProduct(p4, "Reol F : Hylde 6", "Afd: 2");
		sp5 = new StockProduct(p5, "Reol D : Hylde 5", "Afd: 1");
		sp6 = new StockProduct(p6, "Reol C : Hylde 4", "Afd: 1");
		sp7 = new StockProduct(p7, "Reol E : Hylde 2", "Afd: 2");
		sp8 = new StockProduct(p8, "Reol D : Hylde 4", "Afd: 1");
		sp9 = new StockProduct(p9, "Reol E : Hylde 6", "Afd: 2");

		stock = Stock.getInstance();
		stock.addStockProduct(sp1);
		stock.addStockProduct(sp2);
		stock.addStockProduct(sp3);
		stock.addStockProduct(sp4);
		stock.addStockProduct(sp5);
		stock.addStockProduct(sp6);
		stock.addStockProduct(sp7);
		stock.addStockProduct(sp8);
		stock.addStockProduct(sp9);

		c1 = new Customer("Jakob Jakobsen", "Sofiendalsvej 60", "jakob@jakobsen.com", "12345678", "privat");
		c2 = new Customer("T�mrermester", "L�gtevej 4", "hej@t�mreren.dk", "12121212", "erhverv");
		c3 = new Customer("Hans Hansen", "Birkevej 10", "hans@gmail.com", "77778888", "privat");

		cCon = CustomerContainer.getInstance();
		cCon.addCustomer(c1);
		cCon.addCustomer(c2);
		cCon.addCustomer(c3);
	}
}
