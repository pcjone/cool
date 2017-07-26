package com.test.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cool.api.SysMenuService;
import com.cool.model.expand.SysMenuTree;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring-config-Test.xml")
public class SysMenuTest {
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Test
	public void test() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", 1);
		params.put("enable", 1);
		//顶级目录
		params.put("parentId", 0);
		sysMenuService.querySysMenuTree(params);
		List<SysMenuTree> tree = sysMenuService.querySysMenuTree(params);
		System.out.println("---------------------------");
		for(SysMenuTree t : tree) {
			if(t.isHasChild()) {
				for(SysMenuTree l : t.getChildSysMenuTree()) {
					System.out.println(l.getMenuName());
				}
			}else {
				System.out.println(t.getMenuName());
			}
		}
		System.out.println("---------------------------");
	}
}
