//
//Ecard2.java
//
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Ecard2 {
	
	void startGame() {
		System.out.println("game start");
		
		MyCard mycards = new MyCard();
		CpuCard cpucards = new CpuCard();
		
		int number_of_cards = 5;
		
		mycards.shuffle();
		cpucards.shuffle();
		
		while(true) {
			System.out.println("your cards are...");
			for(int i = 0; i < number_of_cards; i++) {
				System.out.printf("%d:%s  ",i,mycards.myCard[i].getKinds());
			}
			System.out.println("\n");
			System.out.printf("choose a number from 0 to %d : ",number_of_cards-1);
			Scanner num1 = new Scanner(System.in);
			String num2 = num1.nextLine();
			int number = Integer.parseInt(num2);
			
			System.out.print("your card is ");
			System.out.printf("%s.\n",mycards.myCard[number].getKinds());
			
			Random random = new Random();
			int cpunumber = random.nextInt(number_of_cards);
			System.out.print("cpu card is ");
			System.out.printf("%s.\n",cpucards.cpuCard[cpunumber].getKinds());
			
			if(mycards.myCard[number].getNumber() == 0 && cpucards.cpuCard[cpunumber].getNumber() == 2) {
				System.out.println("YOU WIN");
				break;
			} else if(cpucards.cpuCard[cpunumber].getNumber() > mycards.myCard[number].getNumber()) {
				System.out.println("YOU LOSE");
				break;
			} else {
				System.out.println("DRAW");
				mycards.remove(number);
				cpucards.remove(cpunumber);
				number_of_cards--;
				mycards.shuffle();
				cpucards.shuffle();
			}
		}
	}
	
	public static void main(String[] args) {
		Ecard2 game = new Ecard2();
		game.startGame();
	}
}