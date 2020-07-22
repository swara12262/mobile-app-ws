package com.app.ws;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.ws.io.entity.UserEntity;

//UserEntity needs to be persisted and long is the type of Id in userEntity
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
