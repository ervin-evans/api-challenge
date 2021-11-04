package com.ervin.dev.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ervin.dev.models.entity.User;

public interface IUserDao extends CrudRepository<User, Long> {

}
