package com.cathaybk.practice.nt00550345.b;

public class Employee implements IWork {

	private String name;
	private String department;
	private int salary;
	private int payment;

	public Employee() {

	};

	public Employee(String name, String department) {
		this.name = name;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public void printInfo() {
		System.out.println("薪資單");
		System.out.println("姓名:" + name + "\t " + "工作部門:" + department);
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	};
}
