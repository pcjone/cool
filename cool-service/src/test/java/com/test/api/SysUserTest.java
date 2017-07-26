package com.test.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cool.api.SysUserService;
import com.cool.model.SysUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring-config.xml")
public class SysUserTest {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Test
	public void test() {
		SysUser user = sysUserService.queryDBById(1l);
	}

}
