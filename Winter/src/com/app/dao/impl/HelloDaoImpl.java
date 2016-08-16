package com.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.HelloDao;

public class HelloDaoImpl  implements HelloDao{

	private String bString = "66";

	public List<String> getKikanList() {

		List<String> list = new ArrayList<>();

		list.add("hhh");
		list.add("df");
		list.add("ffsd");
		list.add("sfwe");
		list.add("dfre");
		list.add(bString);

		return list;
	}
}
