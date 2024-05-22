package com.teste.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teste.model.Reserva;
import com.teste.service.ReservaService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@Named
@ViewScoped
public class CadastroReservaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Reserva reserva;
	
	@Inject
	private ReservaService reservaService;

	@PostConstruct
	public void inicializar() {
		log.info("init cadastro");
		limpar();
	}
	public void salvar() {

		try {

			Reserva res = reservaService.salvar(reserva);
			log.info("hos: " + reserva.getCodigoReserva() + "-" + res.getCodigoReserva());

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Reserva " + res.getCodigoReserva() + " feita com sucesso.", null));

			this.limpar();

		} catch (Exception e) {

			e.printStackTrace();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Ocorreu um problema", null));
		}
	}

	public void limpar() {

		this.reserva = new Reserva();
	}
}