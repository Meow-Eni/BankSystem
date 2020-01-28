package org.cqipc.edu.bean;

public class Customer {
	private int c_id;
	private String c_name;
	private String c_tel;
	private String c_addr;
	private String c_createDate;
	private Bank b_id;
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_tel() {
		return c_tel;
	}
	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}
	public String getC_addr() {
		return c_addr;
	}
	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}
	public String getC_createdate() {
		return c_createDate;
	}
	public void setC_createdate(String c_createdate) {
		this.c_createDate = c_createdate;
	}
	public Bank getB_id() {
		return b_id;
	}
	public void setB_id(Bank b_id) {
		this.b_id = b_id;
	}
	public Customer() {
	}
	public Customer(int c_id, String c_name, String c_tel, String c_addr, String c_createdate, Bank b_id) {
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_tel = c_tel;
		this.c_addr = c_addr;
		this.c_createDate = c_createdate;
		this.b_id = b_id;
	}
	public Customer(String c_name, String c_tel, String c_addr, String c_createdate, Bank b_id) {
		this.c_name = c_name;
		this.c_tel = c_tel;
		this.c_addr = c_addr;
		this.c_createDate = c_createdate;
		this.b_id = b_id;
	}
	@Override
	public String toString() {
		return "Customer [c_id=" + c_id + ", c_name=" + c_name + ", c_tel=" + c_tel + ", c_addr=" + c_addr
				+ ", c_createdate=" + c_createDate + ", b_id=" + b_id + "]";
	}
}
