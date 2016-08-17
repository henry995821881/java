package com.app.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.winter.fromwork.Action;
import org.winter.fromwork.ActionMethod;

import org.winter.fromwork.Autowired;


import com.app.service.DtService;

@Action
public class Dt0002Action {
	
	@Autowired(beanId="dtServiceImpl")
	private DtService dtService;
	
	
	
	@ActionMethod(url="/queryItem1.do")
	public String queryItem(HttpServletRequest request,HttpServletResponse response,Map data){
	
		
		
		return "/winter/queryItem.jsp";
		
	}

}
