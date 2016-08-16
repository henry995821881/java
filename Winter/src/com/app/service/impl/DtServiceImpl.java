package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.winter.fromwork.Autowired;

import com.app.dao.HelloDao;
import com.app.dao.impl.HelloDaoImpl;
import com.app.service.DtService;

public class DtServiceImpl implements DtService{

	
	@Autowired(beanId="helloDaoImpl")
	private HelloDao dao;
	
	@Override
	public List<String> getKekan() {
		
		
		int a = 9/0;
		
		return dao.getKikanList();
	}

}
