package com.teste.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.teste.model.Reserva;
import com.teste.util.jpa.Transactional;

public class ReservaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public Reserva salvar(Reserva reserva) throws PersistenceException {
		try {
			return manager.merge(reserva);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	public void excluir(Reserva reserva) throws PersistenceException {

		try {
			Reserva r = manager.find(Reserva.class, reserva.getCodigoReserva());
			manager.remove(r);
			manager.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		} 
	}

	/*
	 * Buscas
	 */

	public Reserva buscarPeloCodigo(Long id) {
		return manager.find(Reserva.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> buscarTodos() {
		
		String query="select r from Hospede r";
		
		Query q = manager.createQuery(query);
		
		return q.getResultList();
	}

}
