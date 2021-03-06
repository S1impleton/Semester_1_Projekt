package ui.libTUI;

import java.util.*;

/**
 * Author: Istvan Knoll - text output re-written to Danish by Stefan 
 */
public class TextChoice<T> {
	private Scanner scanner;
	private ArrayList<T> options;
	private ConverterIF<T> converter;
	
	public TextChoice(ConverterIF<T> converter){
		this.converter = converter;
		options = new ArrayList<>();
		scanner = new Scanner(System.in);
	}
	
	public void addOption(T option) {
		this.options.add(option);
	}
	
	public void removeOption(T option){
		this.options.remove(option);
	}
	
	public T promptChoice(String title, String prompt) {
		System.out.println(title);
		displayOptions();
		int choice = new TextInput().promptInt(prompt);
		if(choice <= 0 || choice > options.size()) {
			return null;
		} else {
			return options.get(--choice);
		}
	}
	
	public T promptChoiceValidOnly(String title, String prompt) {
		System.out.println(title);
		displayOptions();
		boolean selecting = true;
		int choice = 0;
		while (selecting){
			choice = new TextInput().promptInt(prompt);
			if (choice <= options.size() && choice > 0 ){
				return options.get(--choice);
			}

			else if(choice == 0) {
				selecting = false;
			}
			else {
				System.out.println("Indtast venligst et gyldigt tal");
			}
		}

		return null;
	}
	
	public void displayOptions() {
		System.out.println("0 -> [Done]");
		if(!options.isEmpty()){
			for(int i = 0; i < options.size(); i++) {
				System.out.println("" + (i + 1) + " -> " + converter.convertToString(options.get(i)));
			}
		} else {
			System.out.println("Ingen valgmuligheder");
		}
	}
	
	
}
