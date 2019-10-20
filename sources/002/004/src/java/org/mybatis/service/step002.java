package org.mybatis.service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.domain.TB_USER;
import org.mybatis.config.SqlManager;

public class step001 {

	private String mapperId = "mybatis.mapper.step008";

    public List<TB_USER> selectAll() {

		SqlSession session = SqlManager.open();

		List<TB_USER> userList = new ArrayList<TB_USER>();

		try {
			// 4. 쿼리를 실행하고 SqlSession의 commit rollback 구문을 통하여 쿼리를 처리 합니다.
			userList = session.selectList(mapperId+".select_TB_USER");

			// 정상 종료 시 commit
			session.commit();
		}catch(Exception e) {
			
			// 에러 발생 시 rollback
			session.rollback();
		}

		return userList;
    }

	public TB_USER selectOne(int userId) {

		SqlSession session = SqlManager.open();
		TB_USER user = null;
		try {
			
			user = session.selectOne(mapperId+".selectOne_TB_USER");
			
			// 정상 종료 시 commit
			session.commit();
		}catch(Exception e) {
			
			// 에러 발생 시 rollback
			session.rollback();
		}
		
		return user;
	}
	
	public int insert(TB_USER tbUser) {
		SqlSession session = SqlManager.open();
		int resultCount = 0;
		try {
			
			resultCount = session.insert(mapperId+".insert_TB_USER", tbUser);
			
			// 정상 종료 시 commit
			session.commit();
		}catch(Exception e) {
			
			// 에러 발생 시 rollback
			session.rollback();
		}
		
		return resultCount;
	}
	
	public int update(TB_USER tbUser) {
		SqlSession session = SqlManager.open();
		int resultCount = 0;
		try {
			
			resultCount = session.update(mapperId+".update_TB_USER", tbUser);
			
			// 정상 종료 시 commit
			session.commit();
		}catch(Exception e) {
			
			// 에러 발생 시 rollback
			session.rollback();
		}
		
		return resultCount;
	}
	
	public int deleteOne(int userId) {
		SqlSession session = SqlManager.open();
		int resultCount = 0;
		try {
			
			resultCount = session.delete(mapperId+".deleteOne_TB_USER", userId);
			
			// 정상 종료 시 commit
			session.commit();
		}catch(Exception e) {
			
			// 에러 발생 시 rollback
			session.rollback();
		}
		
		return resultCount;
	}
	
	public int deleteAll() {
		SqlSession session = SqlManager.open();
		int resultCount = 0;
		try {
			
			resultCount = session.delete(mapperId+".deleteALL_TB_USER");
			
			// 정상 종료 시 commit
			session.commit();
		}catch(Exception e) {
			
			// 에러 발생 시 rollback
			session.rollback();
		}
		
		return resultCount;
	}
}