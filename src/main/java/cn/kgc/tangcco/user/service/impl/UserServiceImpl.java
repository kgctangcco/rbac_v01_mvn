package cn.kgc.tangcco.user.service.impl;

import java.util.List;

import cn.kgc.tangcco.common.utils.PropertyFactory;
import cn.kgc.tangcco.menu.entity.Menu;
import cn.kgc.tangcco.user.dao.UserDao;
import cn.kgc.tangcco.user.entity.User;
import cn.kgc.tangcco.user.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao = (UserDao) PropertyFactory.getInstance("userDao");

	@Override
	public User login(User user) {
		return userDao.login(user.getAccount(), user.getPassword());
	}

	@Override
	public List<Menu> getMenusByUserAccount(String account) {
		return userDao.getMenusByUserAccount(account);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}
