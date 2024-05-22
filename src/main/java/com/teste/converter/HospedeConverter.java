package com.teste.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.teste.dao.HospedeDAO;
import com.teste.model.Hospede;

@FacesConverter(forClass=Hospede.class)
public class HospedeConverter implements Converter<Object> {

	@Inject 
	private HospedeDAO hospedeDAO;
	/*
	public UsuarioConverter() {
		this.usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
	}
	*/
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Hospede retorno = null;

		if (value != null && !value.isEmpty()) {
			retorno = this.hospedeDAO.buscarPeloCodigo(Long.valueOf(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Hospede) value).getId();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}

}