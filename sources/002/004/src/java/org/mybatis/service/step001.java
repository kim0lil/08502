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

    public List<TB_USER> selectAll() {

		SqlSession session = SqlManager.open();

		List<TB_USER> userList = new ArrayList<TB_USER>();

		try {
			// 4. Run the query and process the query through SqlSession's commit rollback statement.
			userList = session.selectList("mybatis.mapper.step007.select_TB_USER");

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
}