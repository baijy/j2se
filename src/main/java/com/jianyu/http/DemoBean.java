package com.jianyu.http;

import java.io.Serializable;

public class DemoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idcard; // 证件号
	private String name; // 姓名

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
