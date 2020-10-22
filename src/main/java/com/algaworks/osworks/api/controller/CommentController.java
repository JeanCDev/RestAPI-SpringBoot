package com.algaworks.osworks.api.controller;

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

import com.algaworks.osworks.api.model.Comment;
import com.algaworks.osworks.api.model.CommentInputModel;
import com.algaworks.osworks.api.model.CommentModel;
import com.algaworks.osworks.domain.exception.NotFoundEntityException;
import com.algaworks.osworks.domain.model.ServiceOrder;
import com.algaworks.osworks.domain.repository.ServiceOrderRepository;
import com.algaworks.osworks.domain.service.ServiceOrderService;

@RestController
@RequestMapping("/ordens-servico/{serviceOrderId}/comentarios")
public class CommentController {
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@GetMapping
	public List<CommentModel> list(@PathVariable Long serviceOrderId){
		
		ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
				.orElseThrow(() -> new NotFoundEntityException("Ordem de serviço não encontrada!"));
		
		return toCollectionModel(serviceOrder.getComments());
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommentModel addComment(@PathVariable Long serviceOrderId, 
			@Valid @RequestBody CommentInputModel commentInputModel) {
		
		Comment comment = serviceOrderService.addComment(serviceOrderId, 
				commentInputModel.getDescription());

		return toModel(comment);
		
	}
	
	private CommentModel toModel(Comment comment) {
		
		return modelMapper.map(comment, CommentModel.class);
		
	}
	
	public List<CommentModel> toCollectionModel(List<Comment> comments){
		
		return comments.stream().map(comment -> toModel(comment))
				.collect(Collectors.toList());
		
	}
	
}
