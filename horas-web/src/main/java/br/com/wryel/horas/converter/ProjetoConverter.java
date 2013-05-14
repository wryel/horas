package br.com.wryel.horas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.business.ProjetoBusiness;
import br.com.wryel.horas.business.ServiceLocator;
import br.com.wryel.horas.entity.Projeto;

public class ProjetoConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		Projeto projeto = null;
		if (StringUtils.isNotBlank(value)) {
			ProjetoBusiness projetoBusiness = ServiceLocator.getInstance().lookup(ProjetoBusiness.class);
			projeto = projetoBusiness.get(Long.valueOf(value));
		}
		return projeto;
	}

	@Override 
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		Projeto projeto = (Projeto) value;
		String id = null;
		if (projeto != null && projeto.getId() != null) {
			id = projeto.getId().toString();
		}
		return id;
	}
}
