package com.teste.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.teste.model.TipoQuarto;
import com.teste.util.jpa.Transactional;

public class TipoQuartoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public TipoQuarto salvar(TipoQuarto tipoQuarto) throws PersistenceException {
		try {
			return manager.merge(tipoQuarto);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	public void excluir(TipoQuarto tipoQuarto) throws PersistenceException {

		try {
			TipoQuarto tq = manager.find(TipoQuarto.class, tipoQuarto.getCodigoTipoQuarto());
			manager.remove(tq);
			manager.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		} 
	}

	/*
	 * Buscas
	 */

	public TipoQuarto buscarPeloCodigo(Long id) {
		return manager.find(TipoQuarto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TipoQuarto> buscarTodos() {
		
		String query="select tq from TipoQuarto tq";
		
		Query q = manager.createQuery(query);
		
		return q.getResultList();
	}
}
