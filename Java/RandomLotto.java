package com.cathaybk.practice.nt00550345.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RandomLotto {

	public static void main(String[] args) {
		Random rand = new Random();

		List<Integer> randomLotto = new ArrayList<>();

		while (randomLotto.size() < 6) {
			int number = rand.nextInt(49) + 1;
			if (!randomLotto.contains(number)) {
				randomLotto.add(number);
			}
		}
		System.out.print("排序前：");
		for (Integer integer : randomLotto) {
			System.out.print(integer + " ");
		}
		System.out.println();

		//按照正常排序
		Collections.sort(randomLotto);
		System.out.print("排序後：");
		for (Integer integer : randomLotto) {
			System.out.print(integer + " ");
		}	
		
		System.out.println();
		
		//按照降冪排序
		Collections.sort(randomLotto, new Comparator<>() {

			@Override 
			public int compare(Integer o1, Integer o2) {
				return -(o1.compareTo(o2));
			}

		});
		System.out.print("按照降冪排序後：");
		for (Integer integer : randomLotto) {
			System.out.print(integer + " ");
		}	

	}
	
}
