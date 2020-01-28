package org.cqipc.edu.dao.Impl;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BaseDao<T> {
	private SqlSessionFactory sqlSessionFactory;
	protected SqlSession sqlSession;
	private Class<T> mapper;		
	public BaseDao() {
		getSqlSessionFactory();
		sqlSession=sqlSessionFactory.openSession(true);
	}
	public T getMapper() {
		return sqlSession.getMapper(mapper);
	}
	public void setMapper(Class<T> mapper) {
		this.mapper=mapper;
	}
	private void getSqlSessionFactory() {
		InputStream is=null;
		try {
			is=Resources.getResourceAsStream("MyBatis.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
	}
}
