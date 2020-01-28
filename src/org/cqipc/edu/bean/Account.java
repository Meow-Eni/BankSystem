package org.cqipc.edu.bean;

public class Account {
	private int a_id;
	private String a_type;
	private double a_balance;
	private  Customer cid;
	
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getA_type() {
		return a_type;
	}
	public void setA_type(String a_type) {
		this.a_type = a_type;
	}
	public double getA_balance() {
		return a_balance;
	}
	public void setA_balance(double a_balance) {
		this.a_balance = a_balance;
	}
	public Customer getCid() {
		return cid;
	}
	public void setCid(Customer cid) {
		this.cid = cid;
	}
	public Account() {
		super();
	}
	
	public Account(String a_type, double a_balance, Customer cid) {
		super();
		this.a_type = a_type;
		this.a_balance = a_balance;
		this.cid = cid;
	}

	public Account(int a_id, String a_type, double a_balance, Customer cid) {
		super();
		this.a_id = a_id;
		this.a_type = a_type;
		this.a_balance = a_balance;
		this.cid = cid;
	}

	public String toString() {
		return "Account [a_id=" + a_id + ", a_type=" + a_type + ", a_balance=" + a_balance + ", cid=" + cid + "]";
	}
}
