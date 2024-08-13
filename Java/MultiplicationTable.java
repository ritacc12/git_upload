package com.cathaybk.practice.nt00550345.b;

public class MultiplicationTable {

	public static void main(String[] args) {
		int length = 10;

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < length; i++) { // 1-9
			for (int j = 2; j < length; j++) { // 2-9
				if (i * j < 10) {
					sb.append(j).append('*').append(i).append('=').append(" ").append(i * j).append('\t');
				} else {
					sb.append(j).append('*').append(i).append('=').append(i * j).append('\t');
				}
				System.out.print(sb.toString());
				sb.setLength(0);
			}
			System.out.println();
		}
	}

}
