package com.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.winter.fromwork.Autowired;

import com.app.bean.DaiDhpTaisyosya;
import com.app.dao.DaiDhpTaisyosyaDao;
import com.app.service.DtService;

public class DtServiceImpl implements DtService{

 @Autowired(beanId="sqlSessionFactoryId")
 private SqlSessionFactory sessionFactory;
	
	
	@Override
	public List<DaiDhpTaisyosya> getDhpTaisyosyaList() {
		
		
		SqlSession session = sessionFactory.openSession();
		DaiDhpTaisyosyaDao  dhpTaisyosyaDao = session.getMapper(DaiDhpTaisyosyaDao.class);
		HashMap<String, Object> data = new HashMap<>();
		data.put("sex", "1");
		data.put("dhpArrayCd", new String[]{"0","A","B","E"});
		List<DaiDhpTaisyosya> dhpTaisyosyaList = dhpTaisyosyaDao.getDhpTaisyosyaList(data);
		session.close();
		
		
		
		return dhpTaisyosyaList;
	}

}
