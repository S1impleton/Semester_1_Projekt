package ui.tui;

public class HelpText {

	public void notExistingCommand() {
		System.out.println("Der findes ikke en s�dan kommando");
	}

	public void reportException(Exception e){
		System.out.println("Fejl: " + e.getMessage());
	}

	public void cancel(){
		System.out.println("Annulleret");
	}

	public void emptyString(){
		System.out.println("Strengen m� ikke v�re tom");
	}
}
