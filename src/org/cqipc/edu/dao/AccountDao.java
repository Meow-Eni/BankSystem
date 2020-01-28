package org.cqipc.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cqipc.edu.bean.Account;
import org.cqipc.edu.bean.Bank;
import org.cqipc.edu.bean.Customer;

public interface AccountDao {
	public List<Account> AccountAll();
	public List<Account> selectAccountcid(int c_id);
	public double selectAccountid(int a_id);
	public int addAccount(Account a);
	public int modifyAccount(Account a);
	public int modifyBalance(@Param("a_balance")double balance,@Param("a_id")int a_id);
	public Customer selectAccountBank(int b_id);
	public boolean remit(double repositBalance, double getMoneyBalance, double number, int parseInt, int parseInt2);
}