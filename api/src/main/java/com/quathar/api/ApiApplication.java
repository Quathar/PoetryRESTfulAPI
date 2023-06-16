package com.quathar.api;

import com.quathar.api.data.entity.User;
import com.quathar.api.data.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <h1>API Application</h1>
 *
 * @since 2023-01-XX
 * @version 1.0
 * @author Q
 */
@SpringBootApplication
public class ApiApplication {

	// <<-FIELD->>
	private final UserService _userService;

	// <<-CONSTRUCTOR->>
	public ApiApplication(UserService userService) {
		_userService = userService;
	}

	// <<-METHODS->>
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertTestUser(PasswordEncoder passwordEncoder) {
		_userService.deleteAll();

		User user = new User();
		user.setUsername("Alpha");
		user.setEncryptedPassword(passwordEncoder.encode("1234"));

		_userService.create(user);
		return args -> {};
	}

}
