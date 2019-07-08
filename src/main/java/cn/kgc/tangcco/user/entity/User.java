package cn.kgc.tangcco.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	private Integer userId;
	private String account,password,nickname;
	
	public User(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}
	
}
