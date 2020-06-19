package com.example.spring.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.domain.exception.BusinessException;
import com.example.spring.domain.exception.EntityNotFoundException;
import com.example.spring.domain.model.Client;
import com.example.spring.domain.model.Comment;
import com.example.spring.domain.model.ServiceOrder;
import com.example.spring.domain.model.ServiceOrderStatus;
import com.example.spring.domain.repository.ClientRepository;
import com.example.spring.domain.repository.CommentRepository;
import com.example.spring.domain.repository.ServiceOrderRepository;


@Service
public class ManagementServiceOrderService {
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	public ServiceOrder create(ServiceOrder serviceOrder) {
		Client client = clientRepository.findById(serviceOrder.getClient().getId())
				.orElseThrow(() -> new BusinessException("Client not found")); //".orElseThrow()" example
		
		serviceOrder.setClient(client);
		serviceOrder.setStatus(ServiceOrderStatus.OPEN);
		serviceOrder.setOpenDate(OffsetDateTime.now());
		return serviceOrderRepository.save(serviceOrder);
	}
	
	public void close(Long serviceOrderId) {
		ServiceOrder serviceOrder = findServiceOrder(serviceOrderId);
		serviceOrder.close();
		serviceOrderRepository.save(serviceOrder);
	}
	
	public Comment addComment(Long serviceOrderId, String description) {
		ServiceOrder serviceOrder = findServiceOrder(serviceOrderId);
		
		Comment comment = new Comment();
		comment.setSendDate(OffsetDateTime.now());
		comment.setDescription(description);
		comment.setServiceOrder(serviceOrder);
		return commentRepository.save(comment);
	}
	
	private ServiceOrder findServiceOrder(Long serviceOrderId) {
		return serviceOrderRepository.findById(serviceOrderId)
				.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
	}
}
