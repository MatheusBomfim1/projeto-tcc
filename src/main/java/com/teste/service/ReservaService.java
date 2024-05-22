package com.teste.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.teste.dao.ReservaDAO;
import com.teste.model.Reserva;

import lombok.extern.log4j.Log4j;

@Log4j
public class ReservaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReservaDAO reservaDAO;
	
	public Reserva salvar(Reserva reserva) {

		log.info("Service salvar/alterar");

		return this.reservaDAO.salvar(reserva);
	}
	
	public void excluir(Reserva reserva) {

		log.info("Service excluir");

		this.reservaDAO.excluir(reserva);
	}
	
	/*
	 * Buscas
	 */
	
	public List<Reserva> buscarTodos() {

		log.info("Service buscar todos");

		return this.reservaDAO.buscarTodos();
	}
}

