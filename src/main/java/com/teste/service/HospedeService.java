package com.teste.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.teste.dao.HospedeDAO;
import com.teste.model.Hospede;

import lombok.extern.log4j.Log4j;

@Log4j
public class HospedeService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private HospedeDAO hospedeDAO;
	
	public Hospede salvar(Hospede hospede) {

		log.info("Service salvar/alterar");

		return this.hospedeDAO.salvar(hospede);
	}
	
	public void excluir(Hospede hospede) {

		log.info("Service excluir");

		this.hospedeDAO.excluir(hospede);
	}
	
	/*
	 * Buscas
	 */
	
	public List<Hospede> buscarTodos() {

		log.info("Service buscar todos");

		return this.hospedeDAO.buscarTodos();
	}
}

