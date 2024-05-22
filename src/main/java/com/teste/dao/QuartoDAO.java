package com.teste.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.teste.model.Quarto;
import com.teste.util.jpa.Transactional;

public class QuartoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public Quarto salvar(Quarto quarto) throws PersistenceException {
		try {
			return manager.merge(quarto);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	public void excluir(Quarto quarto) throws PersistenceException {

		try {
			Quarto qu = manager.find(Quarto.class, quarto.getNumero());
			manager.remove(qu);
			manager.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		} 
	}

	/*
	 * Buscas
	 */

	public Quarto buscarPeloCodigo(Long id) {
		return manager.find(Quarto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Quarto> buscarTodos() {
		
		String query="select qu from Quarto qu";
		
		Query q = manager.createQuery(query);
		
		return q.getResultList();
	}
	
	public String updateStatus() {
		return null;
	}
	
	public void mudarStatus(Long id, String status) {
		Quarto quarto = buscarPeloCodigo(id);
		quarto.setStatus(status);
	}
	
}
