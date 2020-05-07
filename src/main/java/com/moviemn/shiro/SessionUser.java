package com.moviemn.shiro;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SessionUser {

	private Integer id;
	private String username;
	private String nickname;




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
