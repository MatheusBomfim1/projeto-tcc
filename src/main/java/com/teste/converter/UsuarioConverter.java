package com.teste.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.teste.dao.UsuarioDAO;
import com.teste.model.Usuario;

/**
 * @author murakamiadmin
 *
 */
@FacesConverter(forClass=Usuario.class)
public class UsuarioConverter implements Converter<Object> {

	@Inject 
	private UsuarioDAO usuarioDAO;
	/*
	public UsuarioConverter() {
		this.usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
	}
	*/
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;

		if (value != null && !value.isEmpty()) {
			retorno = this.usuarioDAO.buscarPeloCodigo(Long.valueOf(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Usuario) value).getId();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}

}