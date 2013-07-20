package br.com.wryel.horas.entity.filter;

import javax.inject.Named;

import br.com.wryel.horas.entity.Usuario;

@Named
public class UsuarioFilter extends EntityFilter<Usuario> {

	private static final long serialVersionUID = 1L;
	
	private String emailEquals;

	private String nomeLikeRight;
	
	private String emailLikeRight;
	
	private String ativoEquals;
	
	private String tipoEquals;
	
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

	/**
	 * @return the nomeLikeRight
	 */
	public String getNomeLikeRight() {
		return nomeLikeRight;
	}

	/**
	 * @param nomeLikeRight the nomeLikeRight to set
	 */
	public void setNomeLikeRight(String nomeLikeRight) {
		this.nomeLikeRight = nomeLikeRight;
	}

	/**
	 * @return the emailLikeRight
	 */
	public String getEmailLikeRight() {
		return emailLikeRight;
	}

	/**
	 * @param emailLikeRight the emailLikeRight to set
	 */
	public void setEmailLikeRight(String emailLikeRight) {
		this.emailLikeRight = emailLikeRight;
	}

	/**
	 * @return the ativoEquals
	 */
	public String getAtivoEquals() {
		return ativoEquals;
	}

	/**
	 * @param ativoEquals the ativoEquals to set
	 */
	public void setAtivoEquals(String ativoEquals) {
		this.ativoEquals = ativoEquals;
	}

	/**
	 * @return the tipoEquals
	 */
	public String getTipoEquals() {
		return tipoEquals;
	}

	/**
	 * @param tipoEquals the tipoEquals to set
	 */
	public void setTipoEquals(String tipoEquals) {
		this.tipoEquals = tipoEquals;
	}
}
