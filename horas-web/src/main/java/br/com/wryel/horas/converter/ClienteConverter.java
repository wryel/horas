package br.com.wryel.horas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.business.ClienteBusiness;
import br.com.wryel.horas.business.ServiceLocator;
import br.com.wryel.horas.entity.Cliente;

public class ClienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		Cliente cliente = null;
		if (StringUtils.isNotBlank(value)) {
			ClienteBusiness clienteBusiness = ServiceLocator.getInstance().lookup(ClienteBusiness.class);
			cliente = clienteBusiness.get(Integer.valueOf(value));
		}
		return cliente;
	}

	@Override 
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		Cliente cliente = (Cliente) value;
		String id = null;
		if (cliente != null) {
			id = cliente.getId().toString();
		}
		return id;
	}
}