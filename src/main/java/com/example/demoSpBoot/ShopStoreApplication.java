package com.example.demoSpBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demoSpBoot.model.users;
import com.example.demoSpBoot.repository.UsersRepository;

@SpringBootApplication
public class ShopStoreApplication{

	public static void main(String[] args) {
		SpringApplication.run(ShopStoreApplication.class, args);
	}
//	@Autowired
//    UsersRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
//        users user = new users();
//        user.setManhanvien("loda");
//        user.setPassword(passwordEncoder.encode("loda"));
//        userRepository.save(user);
//        System.out.println(user);
//    }

}
