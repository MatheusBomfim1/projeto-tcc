package com.teste.model;

import java.io.Serializable;

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
public class Quarto implements Serializable {

	private static final long serialVersionUID = 82375949344894033L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numero;

	@NotBlank(message="O nome é obrigatório")
	private Integer andar;
	private String status;
	@ManyToOne
	@JoinColumn(name="codigoTipoQuarto")
	private TipoQuarto tipoQuarto;
	
}