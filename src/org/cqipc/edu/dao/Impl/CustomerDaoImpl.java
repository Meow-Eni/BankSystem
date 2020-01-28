package org.cqipc.edu.dao.Impl;

import java.util.List;

import org.cqipc.edu.bean.Bank;
import org.cqipc.edu.bean.Customer;
import org.cqipc.edu.dao.CustomerDao;

public class CustomerDaoImpl 
extends BaseDao<CustomerDao> implements CustomerDao {
	public CustomerDaoImpl() {
		this.setMapper(CustomerDao.class);
	}
	@Override
	public List<Customer> selectCustomerAll() {
		return this.getMapper().selectCustomerAll();
	}

	@Override
	public int addCustomer(Customer c) {
		int count=this.getMapper().addCustomer(c);
		this.sqlSession.commit();
		return count;
	}
	@Override
	public List<Customer> selectCustomerBid(int b_id) {
		return this.getMapper().selectCustomerBid(b_id);
	}
	@Override
	public Bank selectCustomerBank(String b_name) {
		return this.getMapper().selectCustomerBank(b_name);
	}
	@Override
	public List<Customer> selectCustomerByname(String b_name) {
		
		return this.getMapper().selectCustomerByname(b_name);
	}
	@Override
	public int modifyCustomer(String c_name, String c_tel, String c_addr, int b_id, int c_id) {
		int count=this.getMapper().modifyCustomer(c_name, c_tel, c_addr, b_id, c_id);
		this.sqlSession.commit();
		return count;
	}
	@Override
	public List<Bank> selectBankAll() {
	
  return this.getMapper().selectBankAll();
	}
}
