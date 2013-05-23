package br.com.wryel.horas.entity.filter;

import javax.inject.Named;

import br.com.wryel.horas.entity.Cliente;

@Named
public class ClienteFilter extends EntityFilter<Cliente> {

	private static final long serialVersionUID = 1L;

	private Integer idEquals;

	private String nomeEqual;
	
	private String nomeLikeLeft;
	
	private String nomeLikeRight;
	
	/**
	 * @return the nomeEqual
	 */
	public String getNomeEqual() {
		return nomeEqual;
	}

	/**
	 * @param nomeEqual the nomeEqual to set
	 */
	public void setNomeEqual(String nomeEqual) {
		this.nomeEqual = nomeEqual;
	}

	/**
	 * @return the nomeLikeLeft
	 */
	public String getNomeLikeLeft() {
		return nomeLikeLeft;
	}

	/**
	 * @param nomeLikeLeft the nomeLikeLeft to set
	 */
	public void setNomeLikeLeft(String nomeLikeLeft) {
		this.nomeLikeLeft = nomeLikeLeft;
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
	 * @return the idEquals
	 */
	public Integer getIdEquals() {
		return idEquals;
	}

	/**
	 * @param idEquals the idEquals to set
	 */
	public void setIdEquals(Integer idEquals) {
		this.idEquals = idEquals;
	}
}
