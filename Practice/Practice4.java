package com.cathaybk1;


import java.util.Random;

public class Practice4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

   	 String[] choices = {"stone", "scissor", "paper"};
//      Random random = new Random();
//      int choice = random.nextInt(choices.length); 
//      System.out.printf("A出拳:%s", choices[choice]);
//
//      int choice2 = random.nextInt(choices.length); 
//      System.out.printf(""+"B出拳:%s",choices[choice2]);

		int player1win = 0;
		int player2win = 0;
		int times = 0;

		Random rand = new Random();
		int choice = rand.nextInt(3);
		int choice2 = rand.nextInt(3);

		while (player1win < 2 && player2win < 2) {
			times++;
			if (choice > choice2) {
				player1win++;
				System.out.println("A出拳" + choice + "," + "B出拳" + choice2 + "A獲勝");
			} else if (choice < choice2) {
				player2win++;
				System.out.println("A出拳" + choice + "," + "B出拳" + choice2 + "B獲勝");
			} else {
				System.out.println("A出拳" + choice + "," + "B出拳" + choice2 + "平手");
			}

			if (player1win == 2 || player2win == 2) {
				break;
			}
		}

	}

}
