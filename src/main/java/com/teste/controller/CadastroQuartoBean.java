package com.teste.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teste.model.Quarto;
import com.teste.service.QuartoService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@Named
@ViewScoped
public class CadastroQuartoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Quarto quarto;
	
	@Inject
	private QuartoService quartoService;

	@PostConstruct
	public void inicializar() {
		log.info("init cadastro");
		limpar();
	}
	public void salvar() {

		try {

			Quarto qua = quartoService.salvar(quarto);
			log.info("hos: " + quarto.getNumero() + "-" /*+ qua.getTipoQuarto().getDescricao()*/);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Quarto n°" + qua.getNumero() + " salvo com sucesso.", null));

			this.limpar();

		} catch (Exception e) {

			e.printStackTrace();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Ocorreu um problema", null));
		}
	}

	public void limpar() {

		this.quarto = new Quarto();
	}
}