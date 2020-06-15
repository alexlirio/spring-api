package com.example.spring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.Client;

@RestController
public class ClientController {
	
	@GetMapping("/client")
	public List<Client> list() {
		var client1 = new Client();
		client1.setId(1L);
		client1.setName("Alex Lirio");
		client1.setEmail("alex@mail.com");
		client1.setPhone("21 99999-8888");
		
		var client2 = new Client();
		client2.setId(1L);
		client2.setName("Patricia");
		client2.setEmail("patricia@mail.com");
		client2.setPhone("21 88888-7777");
		
		return Arrays.asList(client1, client2);
	}

}
