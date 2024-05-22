package com.teste.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.teste.dao.UsuarioDAO;
import com.teste.model.Usuario;

import lombok.extern.log4j.Log4j;

@Log4j
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	public Usuario salvar(Usuario usuario) {

		log.info("Service salvar/alterar");

		return this.usuarioDAO.salvar(usuario);
	}
	
	public void excluir(Usuario usuario) {

		log.info("Service excluir");

		this.usuarioDAO.excluir(usuario);
	}
	
	/*
	 * Buscas
	 */
	
	public List<Usuario> buscarTodos() {

		log.info("Service buscar todos");

		return this.usuarioDAO.buscarTodos();
	}
}
