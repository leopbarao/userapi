package com.leopbarao.userapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.leopbarao.userapi.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	@Transactional(readOnly = true)
	List<UserModel> findByEmail(String email);
}
