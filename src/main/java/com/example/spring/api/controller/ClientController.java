package com.example.spring.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.domain.model.Client;
import com.example.spring.domain.repository.ClientRepository;
import com.example.spring.domain.service.RegisterClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	//Example 2
//	@PersistenceContext
//	private EntityManager em;
	
	//"repository" here is to "simple queries"
	@Autowired
	private ClientRepository clientRepository;
	
	//"service" here is to "business rules"
	@Autowired
	private RegisterClientService registerClientService;
	
	@GetMapping
	public List<Client> list() {
		
		//Example 1
//		var client1 = new Client();
//		client1.setId(1L);
//		client1.setName("Alex Lirio");
//		client1.setEmail("alex@mail.com");
//		client1.setPhone("21 99999-8888");
//		
//		var client2 = new Client();
//		client2.setId(1L);
//		client2.setName("Patricia");
//		client2.setEmail("patricia@mail.com");
//		client2.setPhone("21 88888-7777");
//		
//		return Arrays.asList(client1, client2);
		
		//Example 2
//		return em.createQuery("FROM Client", Client.class).getResultList();
		
		//Example "findByName" with automatic implemented
		List<Client> l = clientRepository.findByName("Alex Lirio");
		
		//Example "findByNameContaining" automatic implemented
		l = clientRepository.findByNameContaining("Gu");
		
		return clientRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> find(@PathVariable Long clientId) {
		Optional<Client> client = clientRepository.findById(clientId);
		
		if (client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@Valid @RequestBody Client client) {
		return registerClientService.save(client);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> update(@Valid @PathVariable Long clientId, @RequestBody Client client) {
		if (!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		client.setId(clientId);;
		registerClientService.save(client);
		
		return ResponseEntity.ok(client);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> update(@PathVariable Long clientId) {
		if (!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		registerClientService.delete(clientId);
		
		return ResponseEntity.noContent().build();
	}

}
