package com.cathaybk.practice.nt00550345.b;

public class MultiplicationTable {

	public static void main(String[] args) {
		int length = 10;

		for (int i = 1; i < length; i++) { // 1-9
			for (int j = 2; j < length; j++) { // 2-9
				System.out.print(j + "*" + i + "=" + (i * j)+" ");
				System.out.print("\t");		
			}
			
			System.out.println();
		}
	}

}
