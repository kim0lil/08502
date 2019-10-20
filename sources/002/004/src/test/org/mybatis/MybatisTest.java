package org.mybatis;

import org.junit.Assert;
import org.junit.Test;
import org.mybatis.service.step001;

public class MybatisTest {
    	
	@Test
	public void test() {
		step001 service = new step001();
        
		Assert.assertSame(service.selectAll().size(), 0);
	}
}