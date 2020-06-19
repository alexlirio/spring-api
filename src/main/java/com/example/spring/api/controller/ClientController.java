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
	
	@Autowired
	private ClientRepository clientRepository; //"repository" is used to "simple queries" in this layer
	
	@Autowired
	private RegisterClientService registerClientService; //"service" is used to "business rules"
	
//	//Example to use "EntityManager" in this layer, if necessary.
//	@PersistenceContext
//	private EntityManager em;
//	
//	//Example using "model(Client.class)" instead of "dto(ClientInputDto/ClientOutputDto.class)"
//	@GetMapping
//	public List<Client> list() {
//		return em.createQuery("FROM Client", Client.class).getResultList();
//	}
	
	//Example using "model(Client.class)" instead of "dto(ClientInputDto/ClientOutputDto.class)"
	@GetMapping
	public List<Client> list() {
		
//		//Example of "findByName" automatic
//		List<Client> clientList = clientRepository.findByName("Alex Lirio");
		
//		//Example of "findByNameContaining" automatic
//		List<Client> clientList = clientRepository.findByNameContaining("Gu");
		
		return clientRepository.findAll();
	}
	
	//Example using "model(Client.class)" instead of "dto(ClientInputDto/ClientOutputDto.class)" 
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> find(@PathVariable Long clientId) {
		Optional<Client> client = clientRepository.findById(clientId);
		
		if (client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//Example using "model(Client.class)" instead of "dto(ClientInputDto/ClientOutputDto.class)"
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@Valid @RequestBody Client client) {
		return registerClientService.save(client);
	}
	
	//Example using "model(Client.class)" instead of "dto(ClientInputDto/ClientOutputDto.class)"
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> update(@Valid @PathVariable Long clientId, @RequestBody Client client) {
		if (!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		client.setId(clientId);
		registerClientService.save(client);
		
		return ResponseEntity.ok(client);
	}
	
	//Example using "model(Client.class)" instead of "dto(ClientInputDto/ClientOutputDto.class)"
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> update(@PathVariable Long clientId) {
		if (!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		registerClientService.delete(clientId);
		
		return ResponseEntity.noContent().build();
	}

}
