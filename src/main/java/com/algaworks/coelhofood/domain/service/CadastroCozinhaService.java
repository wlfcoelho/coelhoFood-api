package com.algaworks.coelhofood.domain.service;

import com.algaworks.coelhofood.domain.exception.CidadeNaoEncontradoException;
import com.algaworks.coelhofood.domain.exception.CozinhaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.coelhofood.domain.exception.EntidadeEmUsoException;
import com.algaworks.coelhofood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.coelhofood.domain.model.Cozinha;
import com.algaworks.coelhofood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	public static final String MSG_COZINHA_EM_USO
			= "Cozinha de código %d não pode ser removida, pois está em uso";
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar (Cozinha cozinha) {

		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir (Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException( 
					String.format(MSG_COZINHA_EM_USO, cozinhaId));
		}
	}

	public Cozinha buscarOuFalhar (Long cozinhaId){
		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new CidadeNaoEncontradoException(cozinhaId));
	}
}
