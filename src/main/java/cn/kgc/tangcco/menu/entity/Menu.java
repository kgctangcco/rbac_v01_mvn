package cn.kgc.tangcco.menu.entity;

import java.util.List;

import lombok.Data;

@Data
public class Menu {
	private Integer id;
	private String title,url;
	private Boolean checked;
	List<Menu> children;
}
