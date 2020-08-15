package com.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.ws.io.entity.UserEntity;


//UserEntity needs to be persisted and long is the type of Id in userEntity

//CrudRepository is removed cause it doesnt support pagination and added PagingAndSortingReposirtory
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String id);
	
}
