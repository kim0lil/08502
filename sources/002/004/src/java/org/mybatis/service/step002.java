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

public class step002 {

	private String mapperId = "mybatis.mapper.step008";

    public List<TB_USER> selectAll() {

		SqlSession session = SqlManager.open();

		List<TB_USER> userList = new ArrayList<TB_USER>();

		try {
			// 4. Run the query and process the query through SqlSession's commit rollback statement.
			userList = session.selectList(mapperId+".selectAll_TB_USER");

			// Commit on normal shutdown
			session.commit();
		}catch(Exception e) {
			
			// Rollback on error
			session.rollback();
		} finally {
			session.close();
		}

		return userList;
    }

	public TB_USER selectOne(int userId) {

		SqlSession session = SqlManager.open();
		TB_USER user = null;
		try {
			
			user = session.selectOne(mapperId+".selectOne_TB_USER", userId);
			
			// Commit on normal shutdown
			session.commit();
		}catch(Exception e) {
			
			// Rollback on error
			session.rollback();
		} finally {
			session.close();
		}
		
		return user;
	}
	
	public int insert(TB_USER tbUser) {
		SqlSession session = SqlManager.open();
		int resultCount = 0;
		try {
			
			resultCount = session.insert(mapperId+".insert_TB_USER", tbUser);
			
			// Commit on normal shutdown
			session.commit();
		}catch(Exception e) {
			
			// Rollback on error
			session.rollback();
		} finally {
			session.close();
		}
		
		return resultCount;
	}
	
	public int update(TB_USER tbUser) {
		SqlSession session = SqlManager.open();
		int resultCount = 0;
		try {
			
			resultCount = session.update(mapperId+".update_TB_USER", tbUser);
			
			// Commit on normal shutdown
			session.commit();
		}catch(Exception e) {
			
			// Rollback on error
			session.rollback();
		} finally {
			session.close();
		}
		
		return resultCount;
	}
	
	public int deleteOne(int userId) {
		SqlSession session = SqlManager.open();
		int resultCount = 0;
		try {
			
			resultCount = session.delete(mapperId+".deleteOne_TB_USER", userId);
			
			// Commit on normal shutdown
			session.commit();
		}catch(Exception e) {
			
			// Rollback on error
			session.rollback();
		} finally {
			session.close();
		}
		
		return resultCount;
	}
	
	public int deleteAll() {
		SqlSession session = SqlManager.open();
		int resultCount = 0;
		try {
			
			resultCount = session.delete(mapperId+".deleteALL_TB_USER");
			
			// Commit on normal shutdown
			session.commit();
		}catch(Exception e) {
			
			// Rollback on error
			session.rollback();
		} finally {
			session.close();
		}
		
		return resultCount;
	}
}