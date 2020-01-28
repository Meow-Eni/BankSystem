package org.cqipc.edu.bean;

public class Detail {
	private int d_id;
	private int a_id;
	private String d_name;
	private double d_money;
	private String d_date;
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public double getD_money() {
		return d_money;
	}
	public void setD_money(double d_money) {
		this.d_money = d_money;
	}
	public String getD_date() {
		return d_date;
	}
	public void setD_date(String d_date) {
		this.d_date = d_date;
	}
	public Detail() {
		
	}
	public Detail(int d_id, int a_id, String d_name, double d_money, String d_date) {
		this.d_id = d_id;
		this.a_id = a_id;
		this.d_name = d_name;
		this.d_money = d_money;
		this.d_date = d_date;
	}
	public Detail(int d_id, String d_name, double d_money, String d_date) {
		this.d_id = d_id;
		this.d_name = d_name;
		this.d_money = d_money;
		this.d_date = d_date;
	}
	@Override
	public String toString() {
		return "Detail [d_id=" + d_id + ", a_id=" + a_id + ", d_name=" + d_name + ", d_money=" + d_money + ", d_date="
				+ d_date + "]";
	}	
}
