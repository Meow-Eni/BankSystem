package org.cqipc.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cqipc.edu.bean.Bank;
import org.cqipc.edu.bean.Customer;

public interface CustomerDao {
	public List<Customer> selectCustomerAll();
	public int addCustomer(Customer c);
	public List<Customer> selectCustomerBid(int b_id);
	public List<Customer> selectCustomerByname(String b_name);
	public Bank selectCustomerBank(String b_name);
	public int modifyCustomer(@Param("c_name")String c_name,@Param("c_tel")String c_tel,
			@Param("c_addr")String c_addr,@Param("b_id")int b_id,@Param("c_id")int c_id);
	public List<Bank> selectBankAll();
	
}