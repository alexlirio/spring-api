package com.example.spring.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.domain.exception.BusinessException;
import com.example.spring.domain.model.Client;
import com.example.spring.domain.repository.ClientRepository;


@Service
public class RegisterClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client save(Client client) {
		Client clientSaved = clientRepository.findByEmail(client.getEmail());
		
		if (clientSaved != null && !clientSaved.equals(client)) {
			throw new BusinessException("Email already used.");
		}
		
		return clientRepository.save(client);
	}
	
	public void delete(Long clientId) {
		clientRepository.deleteById(clientId);
	}

}
