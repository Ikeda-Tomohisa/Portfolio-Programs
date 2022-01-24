//
//MyCard.java
//
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MyCard {
	public Card[] myCard = new Card[5];
	
	public MyCard() {
		myCard[0] = new Card(CardKinds.SLAVE.getNumber(),CardKinds.SLAVE.getString());
		for(int i=1; i<5; i++){
			myCard[i] = new Card(CardKinds.CITIZEN.getNumber(),CardKinds.CITIZEN.getString());
		}
	}
	
	public void shuffle() {
		List<Card> list = new ArrayList<Card>(Arrays.asList(myCard));
		Collections.shuffle(list);
		myCard = (Card[])list.toArray(new Card[list.size()]);
	}
	
	public void remove(int n) {
		List<Card> list = new ArrayList<Card>(Arrays.asList(myCard));
		list.remove(n);
		myCard = (Card[])list.toArray(new Card[list.size()]);
	}
	
	/*
	public static void main(String[] args) {
		MyCard mycard = new MyCard();
		mycard.shuffle();
		for(int i=0; i<5; i++) {
			System.out.println(mycard.myCard[i]);
		}
		//System.out.println(mycard.myCard[0]);
	}
	*/
}