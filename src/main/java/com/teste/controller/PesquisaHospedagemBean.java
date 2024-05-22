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

import com.teste.model.Hospedagem;
import com.teste.service.HospedagemService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@Named
@ViewScoped
public class PesquisaHospedagemBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Hospedagem> hospedagens = new ArrayList<>();
	private Hospedagem hospedagem;
	
	@Inject
	private HospedagemService hospedagemService;
	
	@PostConstruct
	public void inicializar() {
		log.info("init pesquisa");
		this.hospedagens = hospedagemService.buscarTodos();
	}

	public void excluir() {

		try {
			
			hospedagemService.excluir(hospedagem);
			
			this.hospedagens = hospedagemService.buscarTodos();
			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Hospedagem " + hospedagem.getCodigoReserva() + " excluída com sucesso.", null));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um problema", null));
		}
	}
}
