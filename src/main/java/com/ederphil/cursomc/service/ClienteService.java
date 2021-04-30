package com.ederphil.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ederphil.cursomc.domain.Cliente;
import com.ederphil.cursomc.dto.ClienteDTO;
import com.ederphil.cursomc.repository.ClienteRepository;
import com.ederphil.cursomc.service.exception.DataIntegrityException;
import com.ederphil.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscar(Integer id) {

		Optional<Cliente> retorno = clienteRepository.findById(id);

		return retorno.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	public Cliente atualizar(Cliente cliente) {

		Cliente novoCliente = buscar(cliente.getId());
		updateData(novoCliente, cliente);
		return clienteRepository.save(novoCliente);

	}

	private void updateData(Cliente novoCliente, Cliente cliente) {

		novoCliente.setNome(cliente.getNome());
		novoCliente.setEmail(cliente.getEmail());

	}

	public void deletar(Integer id) {

		buscar(id);
		try {
			clienteRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar uma Cliente que possui Produtos.");
		}

	}

	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);

		return clienteRepository.findAll(pageRequest);

	}

	public Cliente fromDTO(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);

	}

	public Cliente inserir(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

}
