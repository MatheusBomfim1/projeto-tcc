package com.teste.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.teste.dao.HospedagemDAO;
import com.teste.model.Hospedagem;

@FacesConverter(forClass=Hospedagem.class)
public class HospedagemConverter implements Converter<Object> {

	@Inject 
	private HospedagemDAO hospedagemDAO;
	/*
	public UsuarioConverter() {
		this.usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
	}
	*/
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Hospedagem retorno = null;

		if (value != null && !value.isEmpty()) {
			retorno = this.hospedagemDAO.buscarPeloCodigo(Long.valueOf(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Hospedagem) value).getCodigoReserva();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}

}