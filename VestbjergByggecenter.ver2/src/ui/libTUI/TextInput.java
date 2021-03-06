package ui.libTUI;

import java.util.*;

/**
 * Author: Istvan Knoll - text output re-written to Danish by Stefan 
 */
public class TextInput {
	private Scanner scanner = new Scanner(System.in);

	public String promptString(String prompt){
		System.out.println(prompt + " ");
		String res = scanner.nextLine();
		return res;
	}

	public boolean promptBoolean(String prompt){
		String res = promptString(prompt + " (j eller n) ");
		if(res.equalsIgnoreCase("j") || res.equalsIgnoreCase("ja")){
			return true;
		} else if(res.equalsIgnoreCase("n") || res.equalsIgnoreCase("nej")){
			return false;
		} else {
			System.out.println("Du skal bruge ja (j) eller nej (n).");
			return promptBoolean(prompt);
		}
	}

	public double promptDouble(String prompt){
		System.out.println(prompt + " (0..9 og . er tilladt) ");
		while(true){
			if(scanner.hasNextDouble()){
				return scanner.nextDouble();
			} else {
				System.out.println("Kun decimaltal er tilladt f.eks -5.7, 0.01, 13.37");
				scanner.nextLine();
			}
		}
	}

	public int promptInt(String prompt){
		System.out.println(prompt + " (0..9 er tilladt) ");
		while(true){
			if(scanner.hasNextInt()){
				return Integer.parseInt(scanner.nextLine());
			} else {
				System.out.println("Kun heltal er tilladt f.eks. -5, 0, 3, 42");
				scanner.nextLine();
			}
		}
	}
}
