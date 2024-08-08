package com.cathaybk.practice.nt00550345.b;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class HRMain {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "保代部", 38000, 4000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000, 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000, 80000));

		for (Employee employee : employeeList) {
			employee.printInfo();
		}

		File file = new File("C:\\Users\\Admin\\Desktop\\output.csv");
		try (BufferedWriter writter = new BufferedWriter(new FileWriter(file))) {
			for (Employee employee : employeeList) {
				writter.write(employee.getName() + "," + employee.getPayment() + "\n");
			}
			writter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
