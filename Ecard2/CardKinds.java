//
//CardKinds.java
//
public enum CardKinds {
	
	SLAVE(0,"slave"),
	CITIZEN(1,"citizen"),
	KING(2,"king");
	
	private int cardNumber;
	private String cardName;
	
	private CardKinds(int cardNumber, String cardName) {
		this.cardNumber = cardNumber;
		this.cardName = cardName;
	}
	
	public int getNumber() {
		return this.cardNumber;
	}

	public String getString() {
		return this.cardName;
	}
	/*
	public static void main(String[] args) {
		System.out.println(CardKinds.SLAVE);
	}
	*/
}