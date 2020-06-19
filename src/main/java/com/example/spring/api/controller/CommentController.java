package com.example.spring.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.api.dto.CommentInputDto;
import com.example.spring.api.dto.CommentOutputDto;
import com.example.spring.domain.exception.EntityNotFoundException;
import com.example.spring.domain.model.Comment;
import com.example.spring.domain.model.ServiceOrder;
import com.example.spring.domain.repository.ServiceOrderRepository;
import com.example.spring.domain.service.ManagementServiceOrderService;

@RestController
@RequestMapping("/service-order/{serviceOrderId}/comment")
public class CommentController {
	
	@Autowired
	private ManagementServiceOrderService managementServiceOrderService;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<CommentOutputDto> list(@PathVariable Long serviceOrderId) {
		ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
				.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		
		return toCommentOutputDtoList(serviceOrder.getCommentList());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommentOutputDto add(@PathVariable Long serviceOrderId, @Valid @RequestBody CommentInputDto commentInputDto) {
		Comment comment = managementServiceOrderService.addComment(serviceOrderId, commentInputDto.getDescription());
				
		return toCommentOutputDto(comment);
	}
	
	private CommentOutputDto toCommentOutputDto(Comment comment) {
		return modelMapper.map(comment, CommentOutputDto.class);
	}
	
	private List<CommentOutputDto> toCommentOutputDtoList(List<Comment> commentList) {
		return commentList.stream()
				.map(comment -> toCommentOutputDto(comment))
				.collect(Collectors.toList());
	}
	
}
