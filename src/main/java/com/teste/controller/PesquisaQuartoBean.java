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
public class PesquisaQuartoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Quarto> quartos = new ArrayList<>();
	private Quarto quarto;
	
	@Inject
	private QuartoService quartoService;
	
	Long teste = (long) 1;
	
	@PostConstruct
	public void inicializar() {
		log.info("init pesquisa");
		this.quartos = quartoService.buscarTodos();
	}

	public void excluir() {

		try {
			
			quartoService.excluir(quarto);
			
			this.quartos = quartoService.buscarTodos();
			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Quarto n°" + quarto.getNumero() + " excluído com sucesso.", null));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um problema", null));
		}
	}
}
