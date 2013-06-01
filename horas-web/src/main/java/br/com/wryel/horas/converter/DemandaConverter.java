package br.com.wryel.horas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.business.DemandaBusiness;
import br.com.wryel.horas.business.ServiceLocator;
import br.com.wryel.horas.entity.Demanda;

public class DemandaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		Demanda demanda = null;
		if (StringUtils.isNotBlank(value)) {
			DemandaBusiness demandaBusiness = ServiceLocator.getInstance().lookup(DemandaBusiness.class);
			demanda = demandaBusiness.get(Long.valueOf(value));
		}
		return demanda;
	}

	@Override 
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		Demanda demanda = (Demanda) value;
		String id = null;
		if (demanda != null) {
			id = demanda.getId().toString();
		}
		return id;
	}
}
