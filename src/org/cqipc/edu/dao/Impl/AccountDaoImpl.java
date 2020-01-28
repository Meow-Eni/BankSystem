package org.cqipc.edu.dao.Impl;

import java.util.List;

import org.cqipc.edu.bean.Account;
import org.cqipc.edu.bean.Customer;
import org.cqipc.edu.dao.AccountDao;
import org.cqipc.edu.dao.CustomerDao;

public class AccountDaoImpl extends BaseDao<AccountDao>implements AccountDao {
	public AccountDaoImpl() {
		this.setMapper(AccountDao.class);
	}
	@Override
	public List<Account> AccountAll() {
		
		return this.getMapper().AccountAll();
	}

	@Override
	public int addAccount(Account a) {
		int count=this.getMapper().addAccount(a);
		this.sqlSession.commit();
		return count;
		
	}

	@Override
	public Customer selectAccountBank(int b_id) {
	
		return this.getMapper().selectAccountBank(b_id);
	}

	@Override
	public List<Account> selectAccountcid(int c_id) {
		return this.getMapper().selectAccountcid(c_id);
	}

	@Override
	public int modifyAccount(Account a) {
		int count=this.getMapper().modifyAccount(a);
		this.sqlSession.commit();
		return count;
	}

	@Override
	public int modifyBalance(double balance, int a_id) {
		int count=this.getMapper().modifyBalance(balance, a_id);
		this.sqlSession.commit();
		return count;
	}

	@Override
	public double selectAccountid(int a_id) {
		return this.getMapper().selectAccountid(a_id);
	}

	@Override
	public boolean remit(double repositBalance, double getMoneyBalance, double number, int parseInt, int parseInt2) {
		
		return false;
	}

}
