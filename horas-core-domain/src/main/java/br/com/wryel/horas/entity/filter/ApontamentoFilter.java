package br.com.wryel.horas.entity.filter;

import javax.inject.Named;

import br.com.wryel.horas.entity.Apontamento;

@Named
public class ApontamentoFilter extends EntityFilter<Apontamento> {

	private static final long serialVersionUID = 1L;
	
	private String descricaoLikeRight;
	
	private DemandaFilter demandaFilter;

	/**
	 * @return the demandaFilter
	 */
	public DemandaFilter getDemandaFilter() {
		return demandaFilter;
	}

	/**
	 * @param demandaFilter the demandaFilter to set
	 */
	public void setDemandaFilter(DemandaFilter demandaFilter) {
		this.demandaFilter = demandaFilter;
	}

	/**
	 * @return the descricaoLikeRight
	 */
	public String getDescricaoLikeRight() {
		return descricaoLikeRight;
	}

	/**
	 * @param descricaoLikeRight the descricaoLikeRight to set
	 */
	public void setDescricaoLikeRight(String descricaoLikeRight) {
		this.descricaoLikeRight = descricaoLikeRight;
	}
}