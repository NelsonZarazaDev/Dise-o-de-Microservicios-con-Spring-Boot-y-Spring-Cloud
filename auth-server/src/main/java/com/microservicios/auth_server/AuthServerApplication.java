package com.microservicios.auth_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Lo comentado da el password encriptado
@SpringBootApplication
public class AuthServerApplication {
//public class AuthServerApplication implements CommandLineRunner {

//	@Autowired
//	private BCryptPasswordEncoder encoder;


    public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception{
//		System.out.println("Password:"+ encoder.encode("1234"));
//	}
}
