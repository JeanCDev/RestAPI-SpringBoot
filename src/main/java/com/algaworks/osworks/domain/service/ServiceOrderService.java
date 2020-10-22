package com.algaworks.osworks.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.algaworks.osworks.api.model.Comment;
import com.algaworks.osworks.api.model.CommentModel;
import com.algaworks.osworks.domain.exception.BusinessException;
import com.algaworks.osworks.domain.exception.NotFoundEntityException;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.model.ServiceOrder;
import com.algaworks.osworks.domain.model.ServiceOrderStatus;
import com.algaworks.osworks.domain.repository.ClientRepository;
import com.algaworks.osworks.domain.repository.CommentRepository;
import com.algaworks.osworks.domain.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService {

	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public ServiceOrder create(ServiceOrder serviceOrder) {
		
		Client client = clientRepository.findById(serviceOrder.getClient().getId())
				.orElseThrow(() -> new NotFoundEntityException("Cliente não encontrado!"));
		
		serviceOrder.setClient(client);
		
		serviceOrder.setStatus(ServiceOrderStatus.OPEN);
		serviceOrder.setOpenDate(OffsetDateTime.now());
		
		
		return serviceOrderRepository.save(serviceOrder);
		
	}
	
	public Comment addComment(Long serviceOrderId, String description) {
		
		ServiceOrder serviceOrder = search(serviceOrderId);
		
		Comment comment = new Comment();
		
		comment.setSendDate(OffsetDateTime.now());
		comment.setDescription(description);
		comment.setServiceOrder(serviceOrder);
		
		return commentRepository.save(comment);
		
	}
	
	public void endServiceOrder(Long serviceOrderId) {
		
		ServiceOrder serviceOrder = search(serviceOrderId);
		
		serviceOrder.finish();
		
		serviceOrderRepository.save(serviceOrder);
		
	}

	private ServiceOrder search(Long serviceOrderId) {
		return serviceOrderRepository.findById(serviceOrderId)
				.orElseThrow(() -> new NotFoundEntityException("Ordem de serviço não encontrada!"));
	}
	
}
