package org.mybatis.config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlManager {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
            // 1. Resources와 config.xml 경로를 통하여 파일을 읽어 들이는 Reader를 반환 받습니다.
			Reader reader = Resources.getResourceAsReader("mybatis/config/step012.xml");
            // 2. Reader 객체를 사용하여 SqlSessionFactory를 만듭니다.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession open() {
        // 3. 트랜젝션 발생 시에 SqlSessionFactory를 통하여 SqlSession을 생성합니다.
		return sqlSessionFactory.openSession();
	}
}