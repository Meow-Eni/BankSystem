package org.cqipc.edu.dao.Impl;

import java.util.List;

import org.cqipc.edu.bean.Detail;
import org.cqipc.edu.dao.CustomerDao;
import org.cqipc.edu.dao.DetailDao;

public class DetailDaoImpl extends BaseDao<DetailDao>implements DetailDao{
	public DetailDaoImpl() {
		this.setMapper(DetailDao.class);
	}
	public List<Detail> DetailAll() {
		
		return this.getMapper().DetailAll();
	}
}