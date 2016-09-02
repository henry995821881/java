package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public class UserDao
{
	private List<User> userlist;
	private User user;

	public UserDao()
	{
		userlist = new ArrayList<User>();
		user = new User();
		user.setId(0);
		userlist.add(user);
		user = new User();
		user.setId(1);
		userlist.add(user);
		user = new User();
		user.setId(2);
		userlist.add(user);
		user = new User();
		user.setId(3);
		userlist.add(user);
	}

	public List<User> test()
	{
		return userlist;
	}

}