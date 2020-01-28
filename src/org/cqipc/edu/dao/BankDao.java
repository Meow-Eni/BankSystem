package org.cqipc.edu.dao;

import java.util.List;

import org.cqipc.edu.bean.Bank;

public interface BankDao {
	public List<Bank> selectBankAll();
	public List<Bank> selectBankById(String b_name);
	public int addBank(Bank b);
	public int modifyBank(Bank b);
}