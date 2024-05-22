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
public class PesquisaTipoQuartoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<TipoQuarto> tipoQuartos = new ArrayList<>();
	private TipoQuarto tipoQuarto;
	
	@Inject
	private TipoQuartoService tipoQuartoService;
	
	@PostConstruct
	public void inicializar() {
		log.info("init pesquisa");
		this.tipoQuartos = tipoQuartoService.buscarTodos();
	}

	public void excluir() {

		try {
			
			tipoQuartoService.excluir(tipoQuarto);
			
			this.tipoQuartos = tipoQuartoService.buscarTodos();
			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Tipo de Quarto " + tipoQuarto.getDescricao() + " excluído com sucesso.", null));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um problema", null));
		}
	}
}

