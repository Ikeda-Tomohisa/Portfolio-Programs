//
//Ecard.java
//
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Ecard {
	public static void main(String[] args) {
		//自分の手札
		String[] my = new String[] {"slave","citizen","citizen","citizen","citizen"};
		List<String> myhand =  new ArrayList<String>(Arrays.asList(my));
		
		//CPUの手札
		String[] cpu = new String[] {"king","citizen","citizen","citizen","citizen"};
		List<String> cpuhand =  new ArrayList<String>(Arrays.asList(cpu));
		
		//手札の枚数
		int count = 5;
		
		System.out.println("game start!!");
		while(true){
			System.out.println("あなたの手札は\n");
			for(int i = 0;i < myhand.size(); i++) {
				System.out.printf("%s ",myhand.get(i));
			}
			System.out.println("\n");
			System.out.printf("0～%dの数字を選んでください。",count-1);
			Scanner num1 = new Scanner(System.in);
			String num2 = num1.nextLine();
			int number = Integer.parseInt(num2);
			
			System.out.print("あなたが出した手札は : ");
			System.out.println(myhand.get(number));
			
			int cpunumber = new Random().nextInt(count);
			System.out.print("CPUが出した手札は : ");
			System.out.println(cpuhand.get(cpunumber));
			
			//勝利/敗北条件
			if((myhand.get(number)).equals("slave")){
				if((cpuhand.get(cpunumber)).equals("king")){
					System.out.println("あなたの勝ちです。");
					break;
				}else{
					System.out.println("あなたの負けです。");
					break;
				}
			}else{
				if((cpuhand.get(cpunumber)).equals("king")){
					System.out.println("あなたの負けです。");
					break;
				}else{
					System.out.println("引き分けです。");
					myhand.remove(number);
					cpuhand.remove(cpunumber);
					count--;
				}
			}
		}
	}
}
