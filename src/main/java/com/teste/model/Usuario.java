package com.teste.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author murakamiadmin
 *
 */
@Getter
@Setter
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 82375949344894033L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="O nome é obrigatório")
	private String nome;	
	
}
