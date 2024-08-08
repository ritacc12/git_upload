package com.cathaybk.practice.nt00550345.b;

public class Sales extends Employee {

	private int bonus;
	private int payment;

	public Sales(String name, String department, int payment, int bonus) {
		super(name, department);
		this.bonus = bonus;
		this.payment = payment;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	};

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("月薪:" + payment);
		System.out.println("業績獎金:" + Math.round(bonus * 0.05));
		System.out.println("總計:" + Math.round(payment + bonus * 0.05));

	}

}
