package com.cathaybk.practice.nt00550345.b;

public class Sales extends Employee {

	private int bonus; // 業績獎金
	private int payment; // 薪資

	public Sales(String name, String department, int salary, int performance) {
		super(name, department, salary);
		// 在建構的時候可以直接取得bonus set完成的資料
		this.bonus = (int) (performance * 0.05); 
		// 把salary + this.bonus的值設定給this.payment，建構子拿到這個值直接給個個class的成員變數 (若後續有人使用getPpayment()方法就可以直接取得payment變數的值)
		this.payment = salary + this.bonus; 
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
