package ui.tui;

import model.*;
import ui.libTUI.TextInput;
import controller.*;

public class MainMenuUI implements ui.IMainMenuUI {
	private TextInput ti;
	private HelpText help;
	private OrderUI orderUI;
	private SupplyOrderUI supplyOrderUI;
	private SaveAndLoadFromFile saveAndLoad;
	{
		ti = new TextInput();
		help = new HelpText();
		orderUI = new OrderUI();
		supplyOrderUI = new SupplyOrderUI();
		saveAndLoad = new SaveAndLoadFromFile();
	}

	public static void main(String[] args) {
		setup();
		new MainMenuUI().run();
	}

	@Override
	public void run() {
		boolean running = true;
		saveAndLoad.loadAll();
		while (running) {
			System.out.println("--- Vestbjerg Byggecenter ---");
			System.out.println("1. Ordrer");
			System.out.println("2. Bestillinger");
			System.out.println("9. Afslut");

			String choice = ti.promptString("V�lg menu: ");
			switch (choice) {
			case "1":
				orderUI.orderMenu();
				break;
			case "2":
				supplyOrderUI.SupplyOrderMainMenu();
				break;
			case "9":
				saveAndLoad.saveAll();
				System.out.println("God arbejdslyst");
				running = false;
				break;
			default:
				help.notExistingCommand();
				break;
			}
		}
	}

	public static void setup(){
		Supplier s1, s2, s3;
		Product p1, p2, p3, p4, p5, p6, p7, p8, p9;
		ProductContainer pCon;
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

		c1 = new Customer("Jakob Jakobsen", "Sofiendalsvej 60", "jakob@jakobsen.com", "12345678", "privat");
		c2 = new Customer("T�mrermester", "L�gtevej 4", "hej@t�mreren.dk", "12121212", "erhverv");
		c3 = new Customer("Hans Hansen", "Birkevej 10", "hans@gmail.com", "77778888", "privat");

		cCon = CustomerContainer.getInstance();
		cCon.addCustomer(c1);
		cCon.addCustomer(c2);
		cCon.addCustomer(c3);
	}
}
