package cn.kgc.tangcco.menu.dao.impl;


import java.util.List;


import cn.kgc.tangcco.common.dao.BaseDao;
import cn.kgc.tangcco.menu.dao.MenuDao;
import cn.kgc.tangcco.menu.entity.Menu;
import cn.kgc.tangcco.user.dao.UserDao;
import cn.kgc.tangcco.user.dao.impl.UserDaoImpl;

public class MenuDaoImpl implements MenuDao {

	@Override
	public List<Menu> getAllMenus(String account) {
		String sql = "select * from menu where parentId is null";
		List<Menu> menuList =  BaseDao.queryList(sql, Menu.class);//一级菜单
		for (Menu menu : menuList) {
			int id = menu.getId();
			sql = "select * from menu where parentId=?";
			List<Menu> children = BaseDao.queryList(sql, Menu.class, id);
			menu.setChildren(children);//二级菜单
		}
		UserDao userDao = new UserDaoImpl();
		List<Menu> userMenuList = userDao.getMenusByUserAccount(account);
		for (Menu menu : menuList) {
			for (Menu userMenu : userMenuList) {
				if (menu.getId()==userMenu.getId()) {
					menu.setChecked(true);//选中一级菜单
				}
			}
		}
		
		
		for (Menu menu : menuList) {
			for (Menu subMenu : menu.getChildren()) {
				for (Menu userMenu : userMenuList) {
					for (Menu subUserMenu : userMenu.getChildren()) {
						if (subMenu.getId()==subUserMenu.getId()) {
							subMenu.setChecked(true);//选中二级菜单
						}
					}
				}
			}
		}
		return menuList;
	}
	
}
