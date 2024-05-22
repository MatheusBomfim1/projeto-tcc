package com.teste.controller;

import java.io.Serializable;

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
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	@Inject
	private UsuarioService usuarioService;

	@PostConstruct
	public void inicializar() {
		log.info("init cadastro");
		limpar();
	}
	public void salvar() {

		try {

			Usuario usu = usuarioService.salvar(usuario);
			log.info("usu: " + usuario.getId() + "-" + usu.getNome());

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Usuario " + usu.getNome() + " salvo com sucesso.", null));

			this.limpar();

		} catch (Exception e) {

			e.printStackTrace();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Ocorreu um problema", null));
		}
	}

	public void limpar() {

		this.usuario = new Usuario();
	}
}
