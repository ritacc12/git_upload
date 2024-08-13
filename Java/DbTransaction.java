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

public class DbTransaction {
	public static final String INSERT_CARS_SQL = "insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)";
	public static final String UPDATE_CARS_SQL = "update STUDENT.CARS set MIN_PRICE = ?, PRICE = ? where MANUFACTURER = ? and TYPE = ? ";
	public static final String DELETE_CARS_SQL = "delete from STUDENT.CARS where MANUFACTURER = ? ";

	public static final String connUrl = "jdbc:oracle:thin:@//localhost:1521/XE";

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection(connUrl, "student", "student123456");
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

				sb.append("製造商：").append(map.get("Manufacturer")).append("，型號：").append(map.get("Type")).append("，售價：$")
						.append(price).append("，底價：$").append(lowprice);
				System.out.println(sb.toString());
				sb.setLength(0);
			}
			sb.append("查無結果");
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		getInstraction();
	}

	private static void getInstraction() {

		try (Scanner scan = new Scanner(System.in)) {
			System.out.print("請選擇以下指令輸入:select、insert、update、delete");
			System.out.println();
			String instruction = scan.next();

			if (instruction.equals("select")) {
				doQuery();
			} else if (instruction.equals("insert")) {
				doInsert();
			} else if (instruction.equals("update")) {
				System.out.println("update");
				doUpdate();
			} else if (instruction.equals("delete")) {
				System.out.println("delete");
				doDelete();
			} else {
				System.out.println("輸入無效指令，請輸入select、insert、update、delete");
				DbTransaction.getInstraction();
				return;
			}
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void doInsert() {
		try (Connection conn = DriverManager.getConnection(connUrl, "student", "student123456");
				Scanner scan = new Scanner(System.in);) {

			try {
				System.out.print("請輸入製造商:");
				String manufacturerInput = VaildInputStringInput(scan);

				System.out.print("請輸入類型:");
				String typeInput = VaildInputStringInput(scan);

				System.out.print("請輸入底價:");
				int minPriceInput = VaildIntInput(scan);

				System.out.print("請輸入售價:");
				int priceInput = VaildIntInput(scan);

				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement(INSERT_CARS_SQL);

				pstmt.setString(1, manufacturerInput);
				pstmt.setString(2, typeInput);
				pstmt.setInt(3, minPriceInput);
				pstmt.setInt(4, priceInput);
				pstmt.executeUpdate();

				System.out.println("新增成功");
				conn.commit();
			} catch (Exception e) {
				System.out.println("新增失敗");
				try {
					conn.rollback();
				} catch (Exception e2) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void doQuery() {
		String connUrl = "jdbc:oracle:thin:@//localhost:1521/XE";

		try (Connection conn = DriverManager.getConnection(connUrl, "student", "student123456");) {
			PreparedStatement pstmt = conn
					.prepareStatement("select *from STUDENT.CARS where MANUFACTURER = ? and TYPE = ? ");
			try (Scanner scan = new Scanner(System.in)) {
				System.out.print("請輸入製造商:");
				String manufacturerInput = VaildInputStringInput(scan);

				System.out.print("請輸入類型:");
				String typeInput = VaildInputStringInput(scan);

				pstmt.setString(1, manufacturerInput);
				pstmt.setString(2, typeInput);
			}

			// ResultSet物件儲存查詢結果
			ResultSet rs = pstmt.executeQuery();

			// 使用StringBuilder做字串串接
			StringBuilder sb = new StringBuilder();
			sb.append("查詢結果： ");
			while (rs.next()) {
				sb.append("製造商： ").append(rs.getString("Manufacturer")).append("，型號：").append(rs.getString("Type"))
						.append("，售價：").append(rs.getString("PRICE")).append("，底價：").append(rs.getString("MIN_PRICE"));
			}
			System.out.println(sb.toString());

		} catch (Exception e) {
			System.out.println("未查詢到此資料");
			e.printStackTrace();
			DbTransaction.getInstraction();
		}

	}

	public static void doUpdate() {
		String connUrl = "jdbc:oracle:thin:@//localhost:1521/XE";

		try (Connection conn = DriverManager.getConnection(connUrl, "student", "student123456");) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_CARS_SQL);
			pstmt.setString(1, "MANUFACTURER");

			try (Scanner scan = new Scanner(System.in)) {

				System.out.print("請輸入更新的底價:");

				int minPriceInput = scan.nextInt();
				System.out.print("請輸入更新的售價:");
				int priceInput = scan.nextInt();

				System.out.print("請輸入製造商:");
				String manufacturerInput = scan.next();
				System.out.print("請輸入類型:");
				String typeInput = scan.next();

				pstmt.setInt(1, minPriceInput);
				pstmt.setInt(2, priceInput);

				pstmt.setString(3, manufacturerInput);
				pstmt.setString(4, typeInput);

				pstmt.executeUpdate();
				System.out.println("更新成功");
				DbTransaction.getInstraction();

			} catch (Exception e) {
				System.out.println("更新失敗");
				getInstraction();
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void doDelete() {
		String connUrl = "jdbc:oracle:thin:@//localhost:1521/XE";

		try (Connection conn = DriverManager.getConnection(connUrl, "student", "student123456");) {
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CARS_SQL);

			try (Scanner scan = new Scanner(System.in)) {
				System.out.print("請輸入要刪除的製造商:");
				String manufacturerInput = VaildInputStringInput(scan);

				pstmt.setString(1, manufacturerInput);
			}

			pstmt.executeQuery();
			System.out.println("刪除成功");

		} catch (Exception e) {
			System.out.println("刪除失敗");
			e.printStackTrace();
			DbTransaction.getInstraction();

		}
	}

	private static String VaildInputStringInput(Scanner scan) {
		while (true) {
			String input = scan.next().trim();
			if (!input.isEmpty()) {
				return input;
			} else {
				System.out.println("請輸入字串");
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