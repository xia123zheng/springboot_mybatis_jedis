package com.xz.springbootjedis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xz.springbootjedis.entity.User;

/**
 * 
 * @author vic
 * @desc User Mapper Dao
 */
@Repository
public interface UserDao {

	public List<User> getAll();
	
}
