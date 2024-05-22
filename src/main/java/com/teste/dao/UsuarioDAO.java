package com.teste.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.teste.model.Usuario;
import com.teste.util.jpa.Transactional;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public Usuario salvar(Usuario usuario) throws PersistenceException {
		try {
			return manager.merge(usuario);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	public void excluir(Usuario usuario) throws PersistenceException {

		try {
			Usuario u = manager.find(Usuario.class, usuario.getId());
			manager.remove(u);
			manager.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		} 
	}

	/*
	 * Buscas
	 */

	public Usuario buscarPeloCodigo(Long id) {
		return manager.find(Usuario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodos() {
		
		String query="select u from Usuario u";
		
		Query q = manager.createQuery(query);
		
		return q.getResultList();
	}

}
