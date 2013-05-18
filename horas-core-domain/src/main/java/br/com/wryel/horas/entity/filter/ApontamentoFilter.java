package br.com.wryel.horas.entity.filter;

import br.com.wryel.horas.entity.Apontamento;

public class ApontamentoFilter extends EntityFilter<Apontamento> {

	private static final long serialVersionUID = 1L;
	
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
}