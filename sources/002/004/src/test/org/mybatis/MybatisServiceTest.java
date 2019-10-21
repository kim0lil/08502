package org.mybatis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.domain.TB_USER;
import org.mybatis.service.step002;

public class MybatisServiceTest {
    	
	@Before
	public void init() {
		
		step002 service = new step002();
		
		service.deleteAll();
	}
	
	@Test
	public void selectAllTest() {
		
		step002 service = new step002();
		
		Assert.assertSame(service.selectAll().size(), 0);
	}
	
	@Test 
	public void selectOneTest() {
		
		step002 service = new step002();
		
		TB_USER Hanry   = new TB_USER(1, "Hanry", "1", "G", "Korea");
		
		service.insert(Hanry);

		Assert.assertSame(service.selectAll().size(), 1);
		
		isEq(Hanry, service.selectOne(Hanry.getUserId()));
	}
	
	public void isEq(TB_USER obj, TB_USER target) {

		Assert.assertEquals(obj.getUserId()      , target.getUserId()      );
		Assert.assertEquals(obj.getUserName()    , target.getUserName()    );
		Assert.assertEquals(obj.getUserRole()    , target.getUserRole()    );
		Assert.assertEquals(obj.getUserGrade()   , target.getUserGrade()   );
		Assert.assertEquals(obj.getUserLocation(), target.getUserLocation());
	}

	@Test
	public void insertTest() {
		step002 service = new step002();
		
		TB_USER Luice   = new TB_USER(1, "Luice", "1", "G", "Korea");
		TB_USER Josua   = new TB_USER(2, "Josua", "2", "G", "LA");
		TB_USER Hanry   = new TB_USER(3, "Hanry", "1", "G", "Korea");
		
		Assert.assertSame(service.selectAll().size(), 0);
		
		service.insert(Luice);
		service.insert(Josua);
		service.insert(Hanry);

		Assert.assertSame(service.selectAll().size(), 3);
	}
	
	@Test
	public void updateTest() {

		step002 service = new step002();
		
		TB_USER Luice   = new TB_USER(1, "Luice", "1", "G", "Korea");
		
		service.insert(Luice);

		isEq(Luice, service.selectOne(Luice.getUserId()));
		
		Luice.setUserName("Luice Kelly");
		
		service.update(Luice);

		isEq(Luice, service.selectOne(Luice.getUserId()));
	}

	@Test
	public void deleteOneTest() {

		step002 service = new step002();

		TB_USER Luice   = new TB_USER(1, "Luice", "1", "G", "Korea");
		TB_USER Josua   = new TB_USER(2, "Josua", "2", "G", "LA");
		TB_USER Hanry   = new TB_USER(3, "Hanry", "1", "G", "Korea");
		
		Assert.assertSame(service.selectAll().size(), 0);
		
		service.insert(Luice);
		service.insert(Josua);
		service.insert(Hanry);

		Assert.assertSame(service.selectAll().size(), 3);
		
		service.deleteOne(Luice.getUserId());

		Assert.assertSame(service.selectAll().size(), 2);
	}
	/*
	@Test
	public void deleteAllTest() {

		step002 service = new step002();
		
		TB_USER Luice   = new TB_USER(1, "Luice", "1", "G", "Korea");
		TB_USER Josua   = new TB_USER(2, "Josua", "2", "G", "LA");
		TB_USER Hanry   = new TB_USER(3, "Hanry", "1", "G", "Korea");
		
		Assert.assertSame(service.selectAll().size(), 0);
		
		service.insert(Luice);
		service.insert(Josua);
		service.insert(Hanry);
		
		Assert.assertSame(service.selectAll().size(), 3);
		
		service.deleteAll();
		
		Assert.assertSame(service.selectAll().size(), 0);
	}
*/
}