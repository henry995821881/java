package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.UserService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

@Controller
public class UserController
{

	@Autowired
	private UserService userService;
	@Autowired
	private CacheManager cacheManager;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test()
	{
	
	
	
	
	
	System.out.println(cacheManager.getCache("myCache").get("hello"));
		
	
		userService.find("hello");
		return "hello";
	}

}