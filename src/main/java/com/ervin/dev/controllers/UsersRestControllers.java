package com.ervin.dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ervin.dev.models.entity.User;
import com.ervin.dev.services.IUserService;

@RestController
@RequestMapping("/api")
public class UsersRestControllers {

	@Autowired
	private IUserService userService;

	public List<User> index() {
		return userService.findAll();
	}
}
