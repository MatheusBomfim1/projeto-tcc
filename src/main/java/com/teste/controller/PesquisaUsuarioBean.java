package com.teste.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teste.model.Usuario;
import com.teste.service.UsuarioService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios = new ArrayList<>();
	private Usuario usuario;
	
	@Inject
	private UsuarioService usuarioService;
	
	@PostConstruct
	public void inicializar() {
		log.info("init pesquisa");
		this.usuarios = usuarioService.buscarTodos();
	}

	public void excluir() {

		try {
			
			usuarioService.excluir(usuario);
			
			this.usuarios = usuarioService.buscarTodos();
			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Usuario " + usuario.getNome() + " excluído com sucesso.", null));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um problema", null));
		}
	}
}
