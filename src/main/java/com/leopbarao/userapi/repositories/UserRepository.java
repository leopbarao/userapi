package com.leopbarao.userapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leopbarao.userapi.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
