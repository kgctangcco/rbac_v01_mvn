package cn.kgc.tangcco.user.service;

import java.util.List;

import cn.kgc.tangcco.menu.entity.Menu;
import cn.kgc.tangcco.user.entity.User;

public interface UserService {
	/**
	 * 登录
	 * 
	 * @param user 用户对象
	 * @return 用户对象
	 */
	User login(User user);

	/**
	 * 查询某个用户的菜单列表
	 * 
	 * @param account 账号
	 * @return 菜单集合
	 */
	List<Menu> getMenusByUserAccount(String account);

	/**
	 * 查询系统中的所有普通用户（非系统管理员）
	 * 
	 * @return 普通用户集合
	 */
	List<User> getUsers();
}
