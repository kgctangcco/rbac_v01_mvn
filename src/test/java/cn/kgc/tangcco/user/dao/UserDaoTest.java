package cn.kgc.tangcco.user.dao;

import java.util.List;

import org.junit.Test;

import cn.kgc.tangcco.common.utils.PropertyFactory;
import cn.kgc.tangcco.menu.entity.Menu;
import cn.kgc.tangcco.user.entity.User;

public class UserDaoTest {
	UserDao userDao = (UserDao) PropertyFactory.getInstance("userDao");
	@Test
	public void login() {
		String account="student02";
		String password="123456";
		User user = userDao.login(account, password);
		System.out.println(user);
	}
	@Test
	public void getMenusByUserAccount() {
		String account="student02";
		List<Menu> menuList = userDao.getMenusByUserAccount(account);
		System.out.println(menuList);
		
		
	}
	@Test
	public void getUsers() {
		List<User> users = userDao.getUsers();
		System.out.println(users);
	}
}
