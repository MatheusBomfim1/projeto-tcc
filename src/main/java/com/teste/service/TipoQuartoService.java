package com.teste.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.teste.dao.TipoQuartoDAO;
import com.teste.model.TipoQuarto;

import lombok.extern.log4j.Log4j;

@Log4j
public class TipoQuartoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoQuartoDAO tipoQuartoDAO;
	
	public TipoQuarto salvar(TipoQuarto tipoQuarto) {

		log.info("Service salvar/alterar");

		return this.tipoQuartoDAO.salvar(tipoQuarto);
	}
	
	public void excluir(TipoQuarto tipoQuarto) {

		log.info("Service excluir");

		this.tipoQuartoDAO.excluir(tipoQuarto);
	}
	
	/*
	 * Buscas
	 */
	
	public List<TipoQuarto> buscarTodos() {

		log.info("Service buscar todos");

		return this.tipoQuartoDAO.buscarTodos();
	}
}

