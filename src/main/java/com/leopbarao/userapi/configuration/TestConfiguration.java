package com.leopbarao.userapi.configuration;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.leopbarao.userapi.model.AddressModel;
import com.leopbarao.userapi.model.UserModel;
import com.leopbarao.userapi.model.enums.ProfileEnum;
import com.leopbarao.userapi.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Override
	public void run(String... args) throws Exception {

		//MockData
		AddressModel address = new AddressModel(null, "Rua São Benedito", "400", null, "Santo Amaro", "04735-000", "São Paulo", "SP");
		UserModel user = new UserModel(null, "Leonardo Barão", "leopbarao@gmail.com", pwdEncoder.encode("123456"), LocalDate.of(1988, 8, 26), address);

		user.addProfile(ProfileEnum.ADMIN);

		userRepo.save(user);
	}
}
