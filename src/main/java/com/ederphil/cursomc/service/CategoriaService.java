package com.ederphil.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederphil.cursomc.domain.Categoria;
import com.ederphil.cursomc.repository.CategoriaRepository;
import com.ederphil.cursomc.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Integer id) {

		Optional<Categoria> retorno = categoriaRepository.findById(id);

		return retorno.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}

	public Categoria inserir(Categoria categoria) {

		categoria.setId(null);

		return categoriaRepository.save(categoria);

	}

}
