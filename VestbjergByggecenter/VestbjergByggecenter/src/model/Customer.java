package model;

public class Customer extends Person{
	private String type;
	private static int privateCustomerID = 0;
	private static int buisnessCustomerID = 0;

	public Customer(String name, String address, String email, String phone, String type ){
		super(autoGenerateCustomerID(type), name, address, email, phone);
		this.type = setType(type);
	}

	public String getPhone(){
		return super.getPhone();
	}

	public String getType(){
		return type;
	}

	/**
	 * sets the type to either ''Privat'' or ''Erhverv''
	 * if the input doesnt match any off those, the type will be an empty String.
	 * @param input is String
	 * @return returns the type
	 */
	public String setType(String input){
		if(input.toLowerCase() != "privat" || input.toLowerCase() != "erhverv"){
			type = "";
		}
		type = input;
		return type;
	}

	/**
	 * creating an auto generated id, depending on the Customers type.
	 * @param type is String
	 * @return returns the result
	 */
	private static String autoGenerateCustomerID(String type){
		String res = null;

		if(type.equalsIgnoreCase("privat")){
			privateCustomerID++;
			res = "1" + privateCustomerID;
		}
		else if(type.equalsIgnoreCase("erhverv")){
			buisnessCustomerID++;
			res = "2" + buisnessCustomerID;
		}
		return res;
	}

	/**
	 * Simply used for resetting IDs for testing.
	 */
	public static void clearIDs(){
		privateCustomerID = 0;
		buisnessCustomerID = 0;
	}
}
