//
//Card.java
//
public class Card {
	private int number;
	private String kinds;
	public Card(int number, String kinds) {
		this.number = number;
		this.kinds = kinds;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getKinds() {
		return kinds;
	}
	@Override
	public String toString() {
		return "Card [number=" + number + ", kinds=" + kinds + "]";
	}
}