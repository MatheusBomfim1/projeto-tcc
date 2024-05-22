package com.teste.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.teste.dao.ReservaDAO;
import com.teste.model.Reserva;

@FacesConverter(forClass=Reserva.class)
public class ReservaConverter implements Converter<Object> {

	@Inject 
	private ReservaDAO reservaDAO;
	/*
	public UsuarioConverter() {
		this.usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
	}
	*/
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Reserva retorno = null;

		if (value != null && !value.isEmpty()) {
			retorno = this.reservaDAO.buscarPeloCodigo(Long.valueOf(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Reserva) value).getCodigoReserva();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}

}