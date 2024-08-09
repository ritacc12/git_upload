package com.cathaybk.practice.nt00550345.b;

public class Sales extends Employee {

	int performance; // 業績
	private int bonus; // 業績獎金
	private int payment; // 薪資

	public Sales(String name, String department, int salary, int performance) {
		super(name, department, salary);
		this.performance = (int) (performance * 0.05); // 轉換為業績獎金
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = (int) (performance * 0.05);

	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = getSalary() + bonus;
	};

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("業績獎金:" + Math.round(performance));
		System.out.println("總計:" + Math.round(getSalary() + performance));
	}

}
