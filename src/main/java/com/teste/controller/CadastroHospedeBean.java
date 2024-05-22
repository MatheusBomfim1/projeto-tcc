package com.teste.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teste.model.Hospede;
import com.teste.service.HospedeService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@Named
@ViewScoped
public class CadastroHospedeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Hospede hospede;
	
	@Inject
	private HospedeService hospedeService;

	@PostConstruct
	public void inicializar() {
		log.info("init cadastro");
		limpar();
	}
	public void salvar() {

		try {

			Hospede hos = hospedeService.salvar(hospede);
			log.info("hos: " + hospede.getId() + "-" + hos.getNome());

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Hospede " + hos.getNome() + " salvo com sucesso.", null));

			this.limpar();

		} catch (Exception e) {

			e.printStackTrace();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Ocorreu um problema", null));
		}
	}

	public void limpar() {

		this.hospede = new Hospede();
	}
}