package com.example.spring.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.api.dto.ServiceOrderOutputDto;
import com.example.spring.api.dto.ServiceOrderInputDto;
import com.example.spring.domain.model.ServiceOrder;
import com.example.spring.domain.repository.ServiceOrderRepository;
import com.example.spring.domain.service.ManagementServiceOrderService;

@RestController
@RequestMapping("/service-order")
public class ServiceOrderController {
	
	@Autowired
	private ManagementServiceOrderService managementServiceOrderService;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper; //"@Bean" configured in "ModelMapperConfig"
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceOrderOutputDto create(@Valid @RequestBody ServiceOrderInputDto serviceOrderInputDto) {
		ServiceOrder serviceOrder = toEntity(serviceOrderInputDto);
		return toServiceOrderOutputDto(managementServiceOrderService.create(serviceOrder));
	}
	
	@GetMapping
	public List<ServiceOrderOutputDto> list() {
		return toServiceOrderOutputDtoList(serviceOrderRepository.findAll());
	}
	
	@GetMapping("/{serviceOrderId}")
	public ResponseEntity<ServiceOrderOutputDto> find(@PathVariable Long serviceOrderId) {
		Optional<ServiceOrder> serviceOrder = serviceOrderRepository.findById(serviceOrderId);
		
		if (serviceOrder.isPresent()) {
			ServiceOrderOutputDto serviceOrderOutputDto = toServiceOrderOutputDto(serviceOrder.get());
			return ResponseEntity.ok(serviceOrderOutputDto);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{serviceOrderId}/close")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void close(@PathVariable Long serviceOrderId) {
		managementServiceOrderService.close(serviceOrderId);
	}
	
	private ServiceOrderOutputDto toServiceOrderOutputDto(ServiceOrder serviceOrder) {
		return modelMapper.map(serviceOrder, ServiceOrderOutputDto.class);
	}
	
	private List<ServiceOrderOutputDto> toServiceOrderOutputDtoList(List<ServiceOrder> serviceOrderList) {
		return serviceOrderList.stream()
				.map(serviceOrder -> toServiceOrderOutputDto(serviceOrder))
				.collect(Collectors.toList());
	}
	
	private ServiceOrder toEntity(ServiceOrderInputDto serviceOrderInputDto) {
		return modelMapper.map(serviceOrderInputDto, ServiceOrder.class);
	}
}
