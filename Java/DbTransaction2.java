package com.cathaybk.practice.nt00550345.b;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DbTransaction2 {

	public static final String INSERT_CARS_SQL = "insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)";
	public static final String UPDATE_CARS_SQL = "update STUDENT.CARS set MIN_PRICE = ?, PRICE = ? where MANUFACTURER = ? and TYPE = ? ";
	public static final String DELETE_CARS_SQL = "delete from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";
	public static final String QUERY_CARS_SQL = "select *from STUDENT.CARS where MANUFACTURER = ? and TYPE = ? ";

	public static final String CONN_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	public static final String DB_ACCOUNT = "student";
	public static final String DB_PASSWORD = "student123456";

	public static void main(String[] args) {
		getDBdata();
		getTransaction();
	}

	private static void getDBdata() {
		try (Connection conn = DriverManager.getConnection(CONN_URL, DB_ACCOUNT, DB_PASSWORD);
				PreparedStatement pstm = conn.prepareStatement("select * from STUDENT.CARS");) {

			ResultSet rs = pstm.executeQuery();

			List<Map<String, String>> carList = new ArrayList<>();

			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				map.put("Manufacturer", rs.getString("Manufacturer"));
				map.put("Type", rs.getString("Type"));
				map.put("MIN_PRICE", rs.getString("MIN_PRICE"));
				map.put("PRICE", rs.getString("PRICE"));

				carList.add(map);

			}
			StringBuilder sb = new StringBuilder();
			for (Map<String, String> map : carList) {
				BigDecimal price = new BigDecimal(map.get("PRICE"));
				BigDecimal lowprice = new BigDecimal(map.get("MIN_PRICE"));

				sb.append("製造商：").append(map.get("Manufacturer")).append("，型號：").append(map.get("Type")).append("，底價：$")
						.append(lowprice).append("，售價：$").append(price);
				System.out.println(sb.toString());
				sb.setLength(0);
			}
			sb.append("查無結果");
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getTransaction() {

		try (Scanner scan = new Scanner(System.in)) {
			System.out.print("請選擇以下指令輸入:select、insert、update、delete");
			System.out.println();
			String instruction = scan.next();

			if (("select").equals(instruction)) {
				System.out.print("請輸入製造商:");
				String manufacturerInput = VaildInputStringInput(scan);

				System.out.print("請輸入類型:");
				String typeInput = VaildInputStringInput(scan);
				query(manufacturerInput, typeInput);

			} else if (("insert").equals(instruction)) {
				System.out.print("請輸入製造商:");
				String manufacturerInput = VaildInputStringInput(scan);

				System.out.print("請輸入類型:");
				String typeInput = VaildInputStringInput(scan);

				System.out.print("請輸入底價:");
				String minPriceInput = VaildIntInput(scan);

				System.out.print("請輸入售價:");
				String priceInput = VaildIntInput(scan);

				Map<String, String> carMap = new HashMap<>();
				carMap.put("MANUFACTURER", manufacturerInput);
				carMap.put("TYPE", typeInput);
				carMap.put("MIN_PRICE", minPriceInput);
				carMap.put("PRICE", priceInput);

				insert(carMap);

			} else if (("update").equals(instruction)) {

				System.out.print("請輸入製造商:");
				String manufacturerInput = VaildInputStringInput(scan);

				System.out.print("請輸入類型:");
				String typeInput = VaildInputStringInput(scan);

				System.out.print("請輸入更新底價:");
				String minPriceInput = VaildIntInput(scan);

				System.out.print("請輸入更新售價:");
				String priceInput = VaildIntInput(scan);

				Map<String, String> carMap = new HashMap<>();
				carMap.put("MANUFACTURER", manufacturerInput);
				carMap.put("TYPE", typeInput);
				carMap.put("MIN_PRICE", minPriceInput);
				carMap.put("PRICE", priceInput);

				update(carMap);
			} else if (("delete").equals(instruction)) {
				System.out.print("請輸入要刪除的製造商:");
				String manufacturerInput = VaildInputStringInput(scan);
				System.out.print("請輸入類型:");
				String typeInput = VaildInputStringInput(scan);

				delete(manufacturerInput, typeInput);
			} else {
				System.out.println("輸入無效指令，請輸入select、insert、update、delete");
				DbTransaction2.getTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void insert(Map<String, String> carData) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, DB_ACCOUNT, DB_PASSWORD)) {
			conn.setAutoCommit(false);

			try (PreparedStatement pstmt = conn.prepareStatement(INSERT_CARS_SQL)) {

				pstmt.setString(1, carData.get("MANUFACTURER"));
				pstmt.setString(2, carData.get("TYPE"));
				pstmt.setBigDecimal(3, new BigDecimal(carData.get("MIN_PRICE")));
				pstmt.setBigDecimal(4, new BigDecimal(carData.get("PRICE")));

				// 設定查詢結果的回傳數量
				int rows = pstmt.executeUpdate();
				if (rows > 0) {
					conn.commit();
					System.out.println("新增成功");
				} else {
					System.err.println("新增失敗");
				}
			} catch (SQLException e) {
				conn.rollback();
				System.err.println("新增失敗: " + e.getMessage());
			}
		} catch (SQLException e) {
			System.err.println("資料庫連接失敗: " + e.getMessage());
		}
	}

	public static void query(String manufacturer, String type) {

		try (Connection conn = DriverManager.getConnection(CONN_URL, DB_ACCOUNT, DB_PASSWORD);) {
			try (PreparedStatement pstmt = conn.prepareStatement(QUERY_CARS_SQL)) {
				pstmt.setString(1, manufacturer);
				pstmt.setString(2, type);

				// 紀錄查詢資料結果
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					System.out.println("查詢結果： 製造商：" + rs.getString("MANUFACTURER") + "，類型：" + rs.getString("TYPE")
							+ "，售價：" + rs.getBigDecimal("PRICE") + "，底價：" + rs.getBigDecimal("MIN_PRICE"));
				} else {
					System.err.println("未查詢到此資料");
				}
			}
		} catch (SQLException e) {
			System.err.println("查詢失敗: " + e.getMessage());
		}
	}

	public static void update(Map<String, String> carData) {

		try (Connection conn = DriverManager.getConnection(CONN_URL, DB_ACCOUNT, DB_PASSWORD);) {
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(UPDATE_CARS_SQL);
			pstmt.setString(1, "MANUFACTURER");

			pstmt.setString(3, carData.get("MANUFACTURER"));
			pstmt.setString(4, carData.get("TYPE"));
			pstmt.setBigDecimal(1, new BigDecimal(carData.get("MIN_PRICE")));
			pstmt.setBigDecimal(2, new BigDecimal(carData.get("PRICE")));

			// 設定查詢結果的回傳數量
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				conn.commit();
				System.out.println("更新成功");
			} else {
				System.err.println("更新失敗，未找到匹配的記錄");
			}

		} catch (SQLException e) {
			System.err.println("資料庫連接失敗: " + e.getMessage());
			e.printStackTrace();
		}

	}

	private static void delete(String manufacturer, String type) {

		try (Connection conn = DriverManager.getConnection(CONN_URL, DB_ACCOUNT, DB_PASSWORD);) {
			try (PreparedStatement pstmt = conn.prepareStatement(DELETE_CARS_SQL);) {
				pstmt.setString(1, manufacturer);
				pstmt.setString(2, type);

				// 設定查詢結果的回傳數量
				int rows = pstmt.executeUpdate();
				if (rows > 0) {
					System.out.println("刪除成功");
				} else {
					System.err.println("未查詢到此筆資料，刪除失敗");
				}
			} catch (Exception e) {
				System.out.println("刪除失敗");
				e.printStackTrace();
			}

		} catch (SQLException e) {

			System.err.println("資料庫連接失敗: " + e.getMessage());
			e.printStackTrace();
		}

	}

	private static String VaildInputStringInput(Scanner scan) {
		while (true) {
			String input = scan.next().trim();
			if (!input.isEmpty()) {
				return input;
			} else {
				System.err.println("請輸入字串"); // 更新為err
			}
		}
	}

	private static String VaildIntInput(Scanner scan) {
		while (true) {
			if (scan.hasNextInt()) { // 確認輸入的是否為int
				return scan.next();
			} else {
				System.err.println("請輸入數字");
				scan.next(); // 清除無效輸入
			}
		}
	}
}
