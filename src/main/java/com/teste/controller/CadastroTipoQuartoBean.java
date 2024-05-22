package com.teste.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teste.model.TipoQuarto;
import com.teste.service.TipoQuartoService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@Named
@ViewScoped
public class CadastroTipoQuartoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoQuarto tipoQuarto;
	
	@Inject
	private TipoQuartoService tipoQuartoService;

	@PostConstruct
	public void inicializar() {
		log.info("init cadastro");
		limpar();
	}
	public void salvar() {

		try {

			TipoQuarto tip = tipoQuartoService.salvar(tipoQuarto);
			log.info("hos: " + tipoQuarto.getCodigoTipoQuarto() + "-" + tip.getDescricao());

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Tipo de quarto " + tip.getDescricao() + " salvo com sucesso.", null));

			this.limpar();

		} catch (Exception e) {

			e.printStackTrace();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Ocorreu um problema", null));
		}
	}

	public void limpar() {

		this.tipoQuarto = new TipoQuarto();
	}
}