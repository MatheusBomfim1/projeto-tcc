package com.teste.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.teste.dao.HospedagemDAO;
import com.teste.model.Hospedagem;

import lombok.extern.log4j.Log4j;

@Log4j
public class HospedagemService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private HospedagemDAO hospedagemDAO;
	
	public Hospedagem salvar(Hospedagem hospedagem) {

		log.info("Service salvar/alterar");

		return this.hospedagemDAO.salvar(hospedagem);
	}
	
	public void excluir(Hospedagem hospedagem) {

		log.info("Service excluir");

		this.hospedagemDAO.excluir(hospedagem);
	}
	
	/*
	 * Buscas
	 */
	
	public List<Hospedagem> buscarTodos() {

		log.info("Service buscar todos");

		return this.hospedagemDAO.buscarTodos();
	}
}

