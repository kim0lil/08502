package org.spring.mybatis.service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.spring.mybatis.domain.TB_SHOP;

public class ShopService {
	
	static SqlSessionFactory factory;
	
	static {
		Reader r;
		try {
			r = Resources.getResourceAsReader("mybatis/config/mybatis-configuration.xml");
			factory = new SqlSessionFactoryBuilder().build(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    // insert
	public int insert(TB_SHOP shop) {
		
		SqlSession session =  factory.openSession();
		int result = 0;
		
		try {

			result = session.insert("mapperSyntax.insert",shop);
			session.commit();
		}catch(Exception e) {
			session.rollback();
		}
		return result;
		
	}
	
    // update
	public int update(TB_SHOP shop) {
		SqlSession session =  factory.openSession();
		int result = 0;
		
		try {
			
			result = session.update("mapperSyntax.update",shop);
			session.commit();
		}catch(Exception e) {
			session.rollback();
		}
		return result;
	}
	
    // select One
	public TB_SHOP selectOne(TB_SHOP shop) {
		SqlSession session =  factory.openSession();
		
		return session.selectOne("mapperSyntax.selectOne", shop);
	}
	
    // select All
	public List<TB_SHOP> selectAll() {
		SqlSession session =  factory.openSession();
		
		return session.selectList("mapperSyntax.selectAll");
	}
	
    // delete
	public int delete(TB_SHOP shop) {
		SqlSession session =  factory.openSession();
		int result = 0;
		
		try {
			
			result = session.update("mapperSyntax.delete",shop);
			session.commit();
		}catch(Exception e) {
			session.rollback();
		}
		return result;
	}
	
    // test
	public static void main(String[] args) {
		
		ShopService service = new ShopService();
		
		TB_SHOP s1 = new TB_SHOP(99, "Shopper", "Korea", "V");
		TB_SHOP s2 = new TB_SHOP(98, "Centiago", "Korea", "V");
		
		service.insert(s1);
		service.insert(s2);
		
		// insert test
		System.out.println(service.selectAll().size() == 2);
		
		s1.setShopName("La Newyork");
		
		service.update(s1);
		
		TB_SHOP s1_1 = service.selectOne(s1);
		
		// update test
		System.out.println(
				   s1_1.getShopNo() == s1.getShopNo()
				&& s1_1.getShopName().equals(s1.getShopName())
				&& s1_1.getShopLocation().equals(s1.getShopLocation())
				&& s1_1.getShopStatus().equals(s1.getShopStatus())
		);

		// delete test
		service.delete(s1);
		service.delete(s2);
		
		System.out.println(service.selectAll().size() == 0);
	}
}
