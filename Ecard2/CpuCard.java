//
//CpuCard.java
//
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CpuCard {
	public Card[] cpuCard = new Card[5];
	
	public CpuCard() {
		cpuCard[0] = new Card(CardKinds.KING.getNumber(),CardKinds.KING.getString());
		for(int i=1; i<5; i++){
			cpuCard[i] = new Card(CardKinds.CITIZEN.getNumber(),CardKinds.CITIZEN.getString());
		}
	}
	
	public void shuffle() {
		List<Card> list = new ArrayList<Card>(Arrays.asList(cpuCard));
		Collections.shuffle(list);
		cpuCard = (Card[])list.toArray(new Card[list.size()]);
	}
	public void remove(int n) {
		List<Card> list = new ArrayList<Card>(Arrays.asList(cpuCard));
		list.remove(n);
		cpuCard = (Card[])list.toArray(new Card[list.size()]);
	}
	
	/*
	public static void main(String[] args) {
		CpuCard cpucard = new CpuCard();
		cpucard.shuffle();
		for(int i=0; i<5; i++) {
			System.out.println(cpucard.cpuCard[i]);
		}
		//System.out.println(cpucard.cpuCard[0]);
	}
	*/
}