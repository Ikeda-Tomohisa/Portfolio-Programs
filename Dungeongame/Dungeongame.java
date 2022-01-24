//
//Dungeongame.java
//
import java.util.Scanner;

public class Dungeongame {
	public static void main(String[] args) {
		String[][] numbers = {
			{"1番","2番","3番","4番","5番"},
			{"6番","7番","8番","9番","10番"},
			{"11番","12番","13番","14番","15番"},
			{"16番","17番","18番","19番","20番"},
			{"21番","22番","23番","24番","25番"}
		};
		
		System.out.println("5×5マスの部屋がある。1～25番と床に書かれている。");
		System.out.println("スタートは左上の1番。ゴールは右下の25番。");
		System.out.println("落とし穴をすり抜けてゴールを目指そう。(5でやめる)");
		int x = 0;
		int y = 0;
		int monster_flag = 1;
		
		while(true){
			System.out.println("今の位置は"+numbers[x][y]);
			System.out.println("2:↓ 4:← 6:→ 8:↑");
			Scanner arrow = new Scanner(System.in);
			String direction = arrow.nextLine();
			int num = Integer.parseInt(direction);
			if(num==2){
				x+=1;
			}else if(num==4){
				y-=1;
			}else if(num==6){
				y+=1;
			}else if(num==8){
				x-=1;
			}else if(num==5){
				System.out.println("ゲームをやめた。");
				break;
			}else{
				System.out.println("方向に合わせて数字を入力しよう。");
				System.out.println("2:↓ 4:← 6:→ 8:↑");
			}
			if(x<0){
				System.out.println("もう上にはいけないよ。");
				x+=1;
			}else if(y<0){
				System.out.println("もう左にはいけないよ。");
				y+=1;
			}else if(x>4){
				System.out.println("もう下にはいけないよ。");
				x-=1;
			}else if(y>4){
				System.out.println("もう右にはいけないよ。");
				y-=1;
			}else{
				;
			}
			if((x==0 && y==3)||(x==1 && y==1)||(x==2 && y==3)||(x==3 && y==0)||(x==4 && y==3)){
				System.out.println("落とし穴だ！GAMEOVER");
				break;
			}
			if(x==0 && y==4){
				System.out.println("宝箱を見つけた。");
			}
			if(x==4 && y==0){
				System.out.println("豪華な宝箱を見つけた。");
			}
			if(x==3 && y==2 && monster_flag == 1){
				System.out.println("モンスターがあらわれた。");
				System.out.println("モンスターとたたかいますか？");
				System.out.println("y:たたかう  n:にげる");
				Scanner sc = new Scanner(System.in);
				String fight = sc.nextLine();
				if(fight.equals("y")){
					System.out.println("モンスターとたたかった。");
					System.out.println("少しケガを負った。モンスターをやっつけた。");
					monster_flag = 0;
				}else{
					System.out.println("モンスターから左へ逃げた。");
					y-=1;
				}
			}
			if(x==4 && y==4){
				System.out.println("ゴール！！");
				break;
			}
		}
	}	
}			