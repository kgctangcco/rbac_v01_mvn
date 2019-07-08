package cn.kgc.tangcco.menu.dao;

import java.util.List;

import cn.kgc.tangcco.menu.entity.Menu;

public interface MenuDao {
	/**
	 * 查询所有菜单
	 * @return	菜单集合
	 */
	List<Menu> getAllMenus(String account);
}
