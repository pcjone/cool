package com.cool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.SysMenuService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.SysMenuMapper;
import com.cool.exception.ServiceException;
import com.cool.model.SysMenu;
import com.cool.model.expand.SysMenuTree;
import com.github.pagehelper.PageInfo;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService{

	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Override
	public PageInfo<SysMenu> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(sysMenuMapper.queryForList(params));
	}

	@Override
	protected BaseMapper<SysMenu> getMapper() {
		return sysMenuMapper;
	}

	@Override
	public List<SysMenu> queryMenuListByUserId(Long userId) {
		return sysMenuMapper.queryMenuListByUserId(userId);
	}

	@Override
	public List<SysMenuTree> querySysMenuTree(Map<String, Object> params) {
		List<SysMenuTree> sysMenuTreeList = sysMenuMapper.queryMenuTree(params);
		return getChild(sysMenuTreeList,0l);
	}
	
	
	public List<SysMenuTree> getChild(List<SysMenuTree> sysMenuTreeList,Long parentId){
		List<SysMenuTree> returnSysMenu = new ArrayList<SysMenuTree>();
		//循环查询parentId相等的
		for(SysMenuTree tree : sysMenuTreeList) {
			if(tree.getParentId().equals(parentId)) {
				List<SysMenuTree> child = getChild(sysMenuTreeList,tree.getId());			
				if(child != null && child.size()>0) {
					tree.setChildSysMenuTree(child);
					tree.setHasChild(true);
				}else {
					tree.setHasChild(false);
				}
				returnSysMenu.add(tree);
			}
		}
		return returnSysMenu;
	}

	@Override
	public List<SysMenu> querySysMenuByUserId(Long userId) {
		return sysMenuMapper.querySysMenuByUserId(userId);
	}

}
