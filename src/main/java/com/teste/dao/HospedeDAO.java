package com.teste.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.teste.model.Hospede;
import com.teste.util.jpa.Transactional;

public class HospedeDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public Hospede salvar(Hospede hospede) throws PersistenceException {
		try {
			return manager.merge(hospede);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	public void excluir(Hospede hospede) throws PersistenceException {

		try {
			Hospede h = manager.find(Hospede.class, hospede.getId());
			manager.remove(h);
			manager.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		} 
	}

	/*
	 * Buscas
	 */

	public Hospede buscarPeloCodigo(Long id) {
		return manager.find(Hospede.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Hospede> buscarTodos() {
		
		String query="select h from Hospede h";
		
		Query q = manager.createQuery(query);
		
		return q.getResultList();
	}

}
