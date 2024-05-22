package com.teste.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.teste.dao.TipoQuartoDAO;
import com.teste.model.TipoQuarto;

@FacesConverter(forClass=TipoQuarto.class)
public class TipoQuartoConverter implements Converter<Object> {

	@Inject 
	private TipoQuartoDAO tipoQuartoDAO;
	/*
	public UsuarioConverter() {
		this.usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
	}
	*/
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoQuarto retorno = null;

		if (value != null && !value.isEmpty()) {
			retorno = this.tipoQuartoDAO.buscarPeloCodigo(Long.valueOf(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((TipoQuarto) value).getCodigoTipoQuarto();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}

}