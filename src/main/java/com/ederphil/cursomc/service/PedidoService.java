package com.ederphil.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederphil.cursomc.domain.Pedido;
import com.ederphil.cursomc.repository.PedidoRepository;
import com.ederphil.cursomc.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Integer id) {
		
		Optional<Pedido> retorno = pedidoRepository.findById(id);
		
		return retorno.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}
	
	
}
