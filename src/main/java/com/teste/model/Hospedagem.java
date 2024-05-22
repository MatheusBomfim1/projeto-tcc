package com.teste.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Hospedagem implements Serializable {

	private static final long serialVersionUID = 82375949344894033L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigoReserva;

	@NotBlank(message="O nome é obrigatório")
	private Date checkIn;
	private Date checkOut;
	private String tipoPagamento;
	private String funcionarioResp;
	@ManyToOne
	@JoinColumn(name="numeroQuarto")
	private Quarto quarto;
	@ManyToOne
	@JoinColumn(name="nomeHospede")
	private Hospede hospede;
}