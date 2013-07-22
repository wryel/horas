package br.com.wryel.horas.controller.pub;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import br.com.wryel.horas.util.FacesUtil;

@SessionScoped
@ManagedBean
public class I18nController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locale locale;

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public void eventoSetarLocaleAtualNaRequest(ComponentSystemEvent componentSystemEvent) {
		if (locale == null) {
			locale = FacesUtil.getInstance().getDefaultApplicationLocale();
		}
		FacesUtil.getInstance().setCurrentLocale(locale);
	}
	
	public void eventoMudarIdioma(ActionEvent actionEvent) {
		String idioma = (String) actionEvent.getComponent().getAttributes().get("idioma");
		Locale locale = new Locale(idioma);
		setLocale(locale);
	}
}
