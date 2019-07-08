package cn.kgc.tangcco.menu.dao;

import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;

import cn.kgc.tangcco.common.utils.PropertyFactory;
import cn.kgc.tangcco.menu.entity.Menu;

public class MenuDaoTest {
	MenuDao menuDao = (MenuDao) PropertyFactory.getInstance("menuDao");
	
	@Test
	public void getAllMenus() throws Exception {
		String account = "student02";
		List<Menu> allMenus = menuDao.getAllMenus(account);
		Gson gson = new Gson();
		String jsonString  = gson.toJson(allMenus);
		System.out.println(jsonString);
	}
}
