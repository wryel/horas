package br.com.wryel.horas.entity.filter;

import javax.inject.Named;

import br.com.wryel.horas.entity.Usuario;

@Named
public class UsuarioFilter extends EntityFilter<Usuario> {

	private static final long serialVersionUID = 1L;
	
	private String emailEquals;

	/**
	 * @return the emailEquals
	 */
	public String getEmailEquals() {
		return emailEquals;
	}

	/**
	 * @param emailEquals the emailEquals to set
	 */
	public void setEmailEquals(String emailEquals) {
		this.emailEquals = emailEquals;
	}
}
