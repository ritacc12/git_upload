package com.cathaybk.practice.nt00550345.b;

public class Sales extends Employee {

	private int bonus; // 業績獎金
	private int payment; // 薪資

	public Sales(String name, String department, int salary, int performance) {
		super(name, department, salary);
		this.bonus = (int) (performance * 0.05); // this.bonus會從getBonus取得bonus的值，並在主類別重新設置bonus與payment的關係
		this.payment = salary + this.bonus; // this.payment會從getPayment取得payment的值，並在主類別重新設置payment與salary&bonus的關係
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
		System.out.println("業績獎金:" + bonus);
		System.out.println("總計:" + getPayment());
	}

}
