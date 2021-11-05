package com.ervin.dev.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ervin.dev.models.entity.User;

@Repository
public interface IUserDao extends CrudRepository<User, Long> {

}
