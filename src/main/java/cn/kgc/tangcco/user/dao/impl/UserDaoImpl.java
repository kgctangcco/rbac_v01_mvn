package cn.kgc.tangcco.user.dao.impl;

import java.util.List;


import cn.kgc.tangcco.common.dao.BaseDao;
import cn.kgc.tangcco.menu.entity.Menu;
import cn.kgc.tangcco.user.dao.UserDao;
import cn.kgc.tangcco.user.entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(String account, String password) {
		String sql = "select * from user where account=? and password=?";
		return BaseDao.queryEntity(sql, User.class, account,password);
	}

	@Override
	public List<Menu> getMenusByUserAccount(String account) {
		String sql = "select m.* from menu m,role_menu rm,user u,user_role ur where u.userId=ur.userId and ur.roleId=rm.roleId and rm.menuId=m.id and account=? and m.parentId is null";
		List<Menu> menuList =  BaseDao.queryList(sql, Menu.class, account);
		for (Menu menu : menuList) {
			int id = menu.getId();
			sql = "select m.* from menu m,role_menu rm,user u,user_role ur where u.userId=ur.userId and ur.roleId=rm.roleId and rm.menuId=m.id and account=? and m.parentId =?";
			List<Menu> children = BaseDao.queryList(sql, Menu.class,account, id);
			menu.setChildren(children);
		}
		return menuList;
	}
	
	@Override
	public List<User> getUsers() {
		String sql = "select u.* from user u,role r,user_role ur where u.userId=ur.userId and r.roleId=ur.roleId and r.roleName!='系统管理员'";
		return BaseDao.queryList(sql, User.class);
	}
}
