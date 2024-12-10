package com.sam.blog;

import com.sam.blog.config.AppConstants;
import com.sam.blog.entities.Role;
import com.sam.blog.repositories.RoleRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogWebAppApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepositories roleRepositories;

	public static void main(String[] args) {
		SpringApplication.run(BlogWebAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("Umair123"));
		try {
			Role role1 = new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ADMIN_USER");

			Role role2 = new Role();
			role2.setId(AppConstants.NORMAL_USER);
			role2.setName("NORMAL_USER");

			List<Role> roles = List.of(role1, role2);
			List<Role> res = this.roleRepositories.saveAll(roles);
			res.forEach(savedRole -> {
				System.out.println(savedRole.getName());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
