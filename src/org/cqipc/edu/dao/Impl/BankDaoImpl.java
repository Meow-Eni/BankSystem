package org.cqipc.edu.dao.Impl;
import java.util.List;

import org.cqipc.edu.bean.Bank;
import org.cqipc.edu.dao.BankDao;

public class BankDaoImpl 
extends BaseDao<BankDao> implements BankDao{
	public BankDaoImpl() {
		this.setMapper(BankDao.class);
	}
	
	@Override
	public List<Bank> selectBankAll() {
		return this.getMapper().selectBankAll();
	}

	@Override
	public List<Bank> selectBankById(String b_name) {
		return this.getMapper().selectBankById(b_name);
	}

	@Override
	public int addBank(Bank b) {
		int count=this.getMapper().addBank(b);
		this.sqlSession.commit();
		return count;
	}

	@Override
	public int modifyBank(Bank b) {
		int count=this.getMapper().modifyBank(b);
		this.sqlSession.commit();
		return count;
	}
	
}