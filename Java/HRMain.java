package com.cathaybk.practice.nt00550345.b;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class HRMain {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "保代部", 38000, 4000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000));

		for (Employee employee : employeeList) {
			employee.printInfo();
		}

		StringBuilder sb = new StringBuilder();
		File file = new File("C:\\Users\\Admin\\Desktop\\output.csv");
		String encoding = "UTF-8";

		try (BufferedWriter writter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(file), encoding))) {
			writter.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));
			for (Employee employee : employeeList) {
				if (employee instanceof Sales) {
					int performance = ((Sales) employee).performance;
					sb.append(employee.getName()).append(',').append(employee.getSalary() + performance).append('\n');
					writter.write(sb.toString());
					sb.setLength(0);

				} else {
					sb.append(employee.getName()).append(',').append(employee.getSalary()).append('\n');
					writter.write(sb.toString());
					sb.setLength(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
