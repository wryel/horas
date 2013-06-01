package br.com.wryel.horas.entity.filter;

import javax.inject.Named;

import br.com.wryel.horas.entity.Projeto;

@Named
public class ProjetoFilter extends EntityFilter<Projeto> {

	private static final long serialVersionUID = 1L;

	private Long idEquals;
	
	private String nomeLikeRight;

	private ClienteFilter clienteFilter;

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
	 * @return the clienteFilter
	 */
	public ClienteFilter getClienteFilter() {
		return clienteFilter;
	}

	/**
	 * @param clienteFilter the clienteFilter to set
	 */
	public void setClienteFilter(ClienteFilter clienteFilter) {
		this.clienteFilter = clienteFilter;
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
