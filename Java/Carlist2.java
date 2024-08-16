package com.cathaybk.practice.nt00550345.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Carlist2 {

	public static void main(String[] args) throws IOException {
		WriteOut();
		GroupBy();
	}

	public static void GroupBy() throws IOException {
		List<Map<String, String>> groupBycarList = new ArrayList<>();

		try (InputStreamReader inputStreamReader1 = new InputStreamReader(
				new FileInputStream("C:\\Users\\Admin\\Desktop\\Java評量_第6題cars.csv"));
				BufferedReader bwBufferedReader = new BufferedReader(inputStreamReader1);) {

			String line = null;
			bwBufferedReader.readLine(); // 讀取第一行 是否為標題

			// 會從第二行開始讀數
			while ((line = bwBufferedReader.readLine()) != null) {

				String item[] = line.split(",");

				Map<String, String> newCar = new HashMap<>();
				newCar.put("Manufacturer", item[0].trim());
				newCar.put("Type", item[1].trim());
				newCar.put("minPrice", item[2].trim());
				newCar.put("price", item[3].trim());

				groupBycarList.add(newCar);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Collections.sort(groupBycarList, new Comparator<Map<String, String>>() {
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				return o1.get("Manufacturer").compareTo(o2.get("Manufacturer"));
			}

		});

		// 新建立resultMap作為分組後的資料
		Map<String, List<Map<String, String>>> resultMap = new TreeMap<String, List<Map<String, String>>>();

		for (Map<String, String> carmap : groupBycarList) {
			String manufacturer = carmap.get("Manufacturer");

			if (resultMap.containsKey(manufacturer)) {// 若已有包含供應商，則加入List<Map<String, String>>
				List<Map<String, String>> groupedlist = resultMap.get(manufacturer);
				groupedlist.add(carmap);
			} else { // 若為新找到的供應商則先加入List中再加入resultMapi
				List<Map<String, String>> resList = new ArrayList<>();
				resList.add(carmap);
				resultMap.put(manufacturer, resList);
			}
		}
		// System.out.println(resultMap);

		BigDecimal grandMinPriceTotal = BigDecimal.ZERO;
		BigDecimal grandPriceTotal = BigDecimal.ZERO;

		System.out.printf("%-12s\t  %-12s\t %-12s\t %-12s\t %n", "Manufacturer", "Type", "MinPrice", "Price");
		for (Map.Entry<String, List<Map<String, String>>> entry : resultMap.entrySet()) {
			String manufacturer = entry.getKey();
			List<Map<String, String>> cars = entry.getValue();

			BigDecimal manufacturerMinPriceTotal = BigDecimal.ZERO;
			BigDecimal manufacturerPriceTotal = BigDecimal.ZERO;

			for (Map<String, String> car : cars) {
				BigDecimal minPrice = new BigDecimal(car.get("minPrice"));
				BigDecimal price = new BigDecimal(car.get("price"));

				// %#10.0f 不會印出小數點 => 1f 印出一位
				System.out.printf("%-12s\t %-12s\t %#-10.1f\t %#-10.1f\t %n", manufacturer, car.get("Type"), minPrice,
						price);

				manufacturerMinPriceTotal = manufacturerMinPriceTotal.add(minPrice);
				manufacturerPriceTotal = manufacturerPriceTotal.add(price);
			}

			System.out.printf("%-12s\t %-12s\t %#-10.1f\t %#-10.1f\t %n", "小計", "", manufacturerMinPriceTotal,
					manufacturerPriceTotal);
			grandMinPriceTotal = grandMinPriceTotal.add(manufacturerMinPriceTotal);
			grandPriceTotal = grandPriceTotal.add(manufacturerPriceTotal);
		}

		// 打印總計
		System.out.printf("%-12s\t %-12s\t %#-10.1f\t %#-10.1f\t %n", "合計", "", grandMinPriceTotal, grandPriceTotal);

	}

	public static void WriteOut() {
		List<Map<String, String>> carList = new ArrayList<>();

		try (InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream("C:\\Users\\Admin\\Desktop\\Java評量_第6題cars.csv"));) {

			try (BufferedReader bwBufferedReader = new BufferedReader(inputStreamReader);
					BufferedWriter writter = new BufferedWriter(
							new FileWriter("C:\\Users\\Admin\\Desktop\\cars2.csv"))) {
				String line = null;

				StringBuilder sb = new StringBuilder();
				while ((line = bwBufferedReader.readLine()) != null) {
					String item[] = line.split(",");

					String manufacturer = item[0].trim();
					String type = item[1].trim();
					String minPrice = item[2].trim();
					String price = item[3].trim();

					sb.append(manufacturer).append(' ').append(type).append(' ').append(minPrice).append(' ')
							.append(price).append(' ');
					sb.setLength(0);

					Map<String, String> map = new HashMap<>();
					map.put("Manufacturer", manufacturer);
					map.put("Type", type);
					map.put("minPrice", minPrice);
					map.put("price", price);

					carList.add(map);

					System.out.println();

					try {
						Collections.sort(carList, new Comparator<Map<String, String>>() {
							@Override
							public int compare(Map<String, String> o1, Map<String, String> o2) {
								return -(o1.get("price").compareTo(o2.get("price")));
							}
						});
						System.out.println("排序成功");

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("排序失敗");
					}

				}
				for (Map<String, String> map : carList) {
					sb.append(map.get("Manufacturer")).append(',').append(map.get("Type")).append(',')
							.append(map.get("minPrice")).append(',').append(map.get("price")).append('\n');

					System.out.println(sb.toString());
					writter.write(sb.toString());
					sb.setLength(0);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
