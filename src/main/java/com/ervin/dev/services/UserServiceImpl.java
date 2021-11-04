package com.ervin.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ervin.dev.models.dao.IUserDao;
import com.ervin.dev.models.entity.User;

public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public void delete(Long id) {
		userDao.deleteById(id);

	}

}
