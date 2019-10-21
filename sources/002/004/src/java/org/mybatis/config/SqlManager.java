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
            // 1. It returns Reader which reads file through Resources and config.xml path.
			Reader reader = Resources.getResourceAsReader("mybatis/config/step012.xml");
            // 2. Create a SqlSessionFactory using a Reader object.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession open() {
        // 3. When a transaction occurs, a SqlSession is created through the SqlSessionFactory.
		return sqlSessionFactory.openSession();
	}
}