package com.teste.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.teste.model.Hospedagem;
import com.teste.util.jpa.Transactional;

public class HospedagemDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public Hospedagem salvar(Hospedagem hospedagem) throws PersistenceException {
		try {
			QuartoDAO quartoDAO = new QuartoDAO();
			quartoDAO.mudarStatus(hospedagem.getQuarto().getNumero(), "Ocupado");
			return manager.merge(hospedagem);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	public void excluir(Hospedagem hospedagem) throws PersistenceException {

		try {
			Hospedagem h = manager.find(Hospedagem.class, hospedagem.getCodigoReserva());
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

	public Hospedagem buscarPeloCodigo(Long codigoReserva) {
		return manager.find(Hospedagem.class, codigoReserva);
	}

	@SuppressWarnings("unchecked")
	public List<Hospedagem> buscarTodos() {
		
		String query="select h from Hospedagem h";
		
		Query q = manager.createQuery(query);
		
		return q.getResultList();
	}

}
