package org.mybatis.config;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class step003 {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
            // 1. It returns Reader which reads file through Resources and config.xml path.
			Reader reader = Resources.getResourceAsReader("mybatis/config/step014.xml");

            // 2. Properties Read
			Properties prop = new Properties();
			
			prop.load(ClassLoader.getSystemResourceAsStream("loadable properties file"));

            // 3. Create a SqlSessionFactory using a Reader object.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession open() {
        // 3. When a transaction occurs, a SqlSession is created through the SqlSessionFactory.
		return sqlSessionFactory.openSession();
	}
}