package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.model.User;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Service
public class UserService
{

	@Autowired
	private UserDao userDao;

	@Autowired
	private CacheManager cacheManager;
	@Cacheable(value = "myCache", key = "#id")
	public List<User> find(String id)
	{
		
		
		Cache cache = cacheManager.getCache("myCache");
		List<User> list = userDao.test();
		//cache.put(new Element("key", list));
		
		
		return list;
	}

}