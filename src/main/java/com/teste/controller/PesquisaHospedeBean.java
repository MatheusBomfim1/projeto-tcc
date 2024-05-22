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
public class PesquisaHospedeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Hospede> hospedes = new ArrayList<>();
	private Hospede hospede;
	
	@Inject
	private HospedeService hospedeService;
	
	@PostConstruct
	public void inicializar() {
		log.info("init pesquisa");
		this.hospedes = hospedeService.buscarTodos();
	}

	public void excluir() {

		try {
			
			hospedeService.excluir(hospede);
			
			this.hospedes = hospedeService.buscarTodos();
			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Usuario " + hospede.getNome() + " excluído com sucesso.", null));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um problema", null));
		}
	}
}
