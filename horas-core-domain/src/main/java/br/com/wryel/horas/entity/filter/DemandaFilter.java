package br.com.wryel.horas.entity.filter;

import javax.inject.Named;

import br.com.wryel.horas.entity.Demanda;

@Named
public class DemandaFilter extends EntityFilter<Demanda> {

	private static final long serialVersionUID = 1L;

	private String nomeLikeRight;
	
	private Long idEquals;

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
	public Long getIdEquals() {
		return idEquals;
	}

	/**
	 * @param idEquals the idEquals to set
	 */
	public void setIdEquals(Long idEquals) {
		this.idEquals = idEquals;
	}
}
