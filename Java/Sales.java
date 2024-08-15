package com.cathaybk.practice.nt00550345.b;

public class Sales extends Employee {

	private int bonus; // 業績獎金
	private int payment; // 薪資

	public Sales(String name, String department, int salary, int performance) {
		super(name, department, salary);
		bonus = (int) (performance * 0.05);
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getPayment() {
		payment = Math.round(getSalary() + bonus);
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	};

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("業績獎金:" + Math.round(bonus));
		System.out.println("總計:" + getPayment());
	}

}
