package com.ederphil.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederphil.cursomc.domain.Cliente;
import com.ederphil.cursomc.repository.ClienteRepository;
import com.ederphil.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository categoriaRepository;
	
	public Cliente buscar(Integer id) {
		
		Optional<Cliente> retorno = categoriaRepository.findById(id);
		
		return retorno.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}
	
	
}
