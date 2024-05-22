package com.teste.controller;

import java.io.Serializable;
import java.util.Date;
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
public class CadastroHospedagemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Hospedagem hospedagem;
	private List<Date> range;
	@Inject
	private HospedagemService hospedagemService;

	@PostConstruct
	public void inicializar() {
		log.info("init cadastro");
		limpar();
	}
	public void converter() {
		hospedagem.setCheckIn(range.get(0));
		hospedagem.setCheckOut(range.get(range.size() - 1));
	}
	public void salvar() {

		try {
			converter();
			Hospedagem hos = hospedagemService.salvar(hospedagem);
			log.info("hos: " + hospedagem.getCodigoReserva() + "-" + hos.getHospede().getNome());

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Hospedagem " + hos.getCodigoReserva() + " salvo com sucesso.", null));

			this.limpar();

		} catch (Exception e) {

			e.printStackTrace();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Ocorreu um problema", null));
		}
	}

	public void limpar() {

		this.hospedagem = new Hospedagem();
	}
}