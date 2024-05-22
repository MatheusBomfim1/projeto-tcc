package com.teste.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.teste.dao.QuartoDAO;
import com.teste.model.Quarto;

import lombok.extern.log4j.Log4j;

@Log4j
public class QuartoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private QuartoDAO quartoDAO;
	
	public Quarto salvar(Quarto quarto) {

		log.info("Service salvar/alterar");

		return this.quartoDAO.salvar(quarto);
	}
	
	public void excluir(Quarto quarto) {

		log.info("Service excluir");

		this.quartoDAO.excluir(quarto);
	}
	
	/*
	 * Buscas
	 */
	
	public List<Quarto> buscarTodos() {

		log.info("Service buscar todos");

		return this.quartoDAO.buscarTodos();
	}
}

