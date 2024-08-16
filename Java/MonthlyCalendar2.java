package com.cathaybk.practice.nt00550345.b;

import java.time.LocalDate;
import java.util.Scanner;

public class MonthlyCalendar2 {

	public static <Calendar> void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.print("輸入介於1-12之間的整數:");
		int month = VaildIntInput(scanner);
		scanner.close();

		if (month < 1 || month > 12) {
			System.out.println("無效的月份。請輸入1到12之間的數字");
			System.exit(0); // 也可以改為return 結束方法執行
		}

		LocalDate date = LocalDate.now().withMonth(month).withDayOfMonth(1);

		System.out.println("\t" + date.getYear() + "年" + month + "月" + "\t");
		System.out.printf("%s %n", "-----------------------");
		System.out.printf("%2s %2s %2s %2s %2s %2s %2s %n%s %n", "日", "一", "二", "三", "四", "五", "六",
				"=======================");

		// 1 % 7 = 1 (一) 2 % 7 = 2 (二) 3 % 7 = 3 (三) 4 % 7 = 4 (四) 5 % 7 = 5 (五) 6 % 7 =
		// 6 (六) 7 % 7 = 0 (日)
		int dayOfWeek = date.getDayOfWeek().getValue() % 7;
		for (int i = 0; i < dayOfWeek; i++) {
			System.out.print("   ");
		}

		for (int i = 1; i <= LocalDate.now().lengthOfMonth(); i++) {
			System.out.printf("%2d ", i);
			if ((dayOfWeek + i) % 7 == 0) {
				System.out.println();
			}

		}

	}

	private static int VaildIntInput(Scanner scan) {
		while (true) {
			if (scan.hasNextInt()) { // 確認輸入的是否為int
				return scan.nextInt();
			} else {
				System.err.println("請輸入數字");
				scan.next(); // 清除無效輸入
			}
		}
	}
}
