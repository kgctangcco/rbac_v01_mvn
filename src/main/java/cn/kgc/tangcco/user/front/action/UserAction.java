package cn.kgc.tangcco.user.front.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.kgc.tangcco.common.servlet.BaseServlet;
import cn.kgc.tangcco.common.utils.PropertyFactory;
import cn.kgc.tangcco.menu.entity.Menu;
import cn.kgc.tangcco.user.entity.User;
import cn.kgc.tangcco.user.service.UserService;

@WebServlet(urlPatterns = "/front/user.action", asyncSupported = true)
public class UserAction<V> extends BaseServlet {

	private static final long serialVersionUID = -8502141244733152930L;
	private UserService userService = (UserService) PropertyFactory.getInstance("userService");

	public void login(HttpServletRequest request, HttpServletResponse response, String json) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Gson gson = new Gson();
		User user = gson.fromJson(json, User.class);
		user = userService.login(user);
		if (user != null) {
			map.put("loginStatus", "loginSuccess");
			map.put("user", user);
		} else {
			map.put("loginStatus", "loginFailed");
		}
		print(request, response, gson.toJson(map));
	}
	public void login(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Gson gson = new Gson();
		User user = new User(request.getParameter("account"), request.getParameter("password"));
		user = userService.login(user);
		if (user != null) {
			map.put("loginStatus", "loginSuccess");
			map.put("user", user);
		} else {
			map.put("loginStatus", "loginFailed");
		}
		print(request, response, gson.toJson(map));
	}
	public void getMenusByUserAccount(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Gson gson = new Gson();
		List<Menu> menus = userService.getMenusByUserAccount(request.getParameter("account"));
		for (Menu menu : menus) {
			System.out.println(menu);
		}
		if (menus.size() > 0) {
			map.put("getMenusByUserAccountStatus", "getMenusByUserAccountSuccess");
			map.put("menus", menus);
		} else {
			map.put("getMenusByUserAccountStatus", "getMenusByUserAccountFailed");
		}
		print(request, response, gson.toJson(map));
	}
}
