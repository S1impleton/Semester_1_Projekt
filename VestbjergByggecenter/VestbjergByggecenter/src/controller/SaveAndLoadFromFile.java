package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.OrderContainer;
import com.google.gson.Gson;

public class SaveAndLoadFromFile {
	private final String orderPath = "orders.txt";
	private Gson gson;
	private String json;

	public void saveAll() {
		saveOrders();
	}

	public void loadAll() {
		loadOrders();
	}

	/**
	 * Using extern library GSON to save a java object to GSON object.
	 * Will create a file in default directory called "orders.txt".
	 * If file already exist the file will be overwritten.
	 */
	private void saveOrders() {
		gson = new Gson();
		String jsonString = gson.toJson(OrderContainer.getInstance());
		try {
			FileWriter fw = new FileWriter(new File(orderPath), false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(jsonString);
			bw.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	/**
	 * Using extern library GSON to load a GSON object to java object.
	 * Will load a file, if it exist, in default directory called "orders.txt".
	 * If file does not exist an exception is thrown.
	 */
	public void loadOrders(){
		gson = new Gson();
		String res;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(orderPath));
			String input = null;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator"); 
			while((input = reader.readLine()) != null){
				stringBuilder.append(input);
				stringBuilder.append(ls);
			}
			res = stringBuilder.toString();
			reader.close();

			OrderContainer orderContainer = gson.fromJson(res, OrderContainer.class);
			orderContainer.restoreFromFile(orderContainer);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}

