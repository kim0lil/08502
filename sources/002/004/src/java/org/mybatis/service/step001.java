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
			// 4. 쿼리를 실행하고 SqlSession의 commit rollback 구문을 통하여 쿼리를 처리 합니다.
			userList = session.selectList("mybatis.mapper.step007.select_TB_USER");

			// 정상 종료 시 commit
			session.commit();
		}catch(Exception e) {
			
			// 에러 발생 시 rollback
			session.rollback();
		}

		return userList;
    }
}