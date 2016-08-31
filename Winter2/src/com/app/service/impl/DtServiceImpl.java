package com.app.service.impl;




import java.util.HashMap;
import java.util.List;


import org.winter.framework.annotation.Autowired;
import org.winter.framework.annotation.Service;
import org.winter.framework.component.ApplicationBeanFactory;

import com.app.bean.DaiDhpTaisyosya;
import com.app.dao.DaiDhpTaisyosyaDao;
import com.app.service.DtService;



@Service("dtService")
public class DtServiceImpl implements DtService{


	@Autowired(beanId="daiDhpTaisyosyaDao")
	private DaiDhpTaisyosyaDao  dhpTaisyosyaDao;
	
	@Override
	public List<DaiDhpTaisyosya> getDhpTaisyosyaList() {
		
	
		
		
		HashMap<String, Object> data = new HashMap<>();
		data.put("sex", "1");
		data.put("dhpArrayCd", new String[]{"0","A","B","E"});
		List<DaiDhpTaisyosya> dhpTaisyosyaList = dhpTaisyosyaDao.getDhpTaisyosyaList(data);

		return dhpTaisyosyaList;
	
		
	}

}
