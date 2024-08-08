package com.cathaybk.practice.nt00550345.b;

public class Supervisor extends Employee {

	private int payment;

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	};

	public Supervisor(String name, String department, int payment, int salary) {
		super(name, department);
		this.payment = payment;
	}

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("月薪:" + payment);
		System.out.println("總計:" + payment);
	}
}
