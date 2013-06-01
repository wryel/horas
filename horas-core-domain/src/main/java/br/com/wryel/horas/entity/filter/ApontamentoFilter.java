package br.com.wryel.horas.entity.filter;

import java.util.Date;

import javax.inject.Named;

import br.com.wryel.horas.entity.Apontamento;

@Named
public class ApontamentoFilter extends EntityFilter<Apontamento> {

	private static final long serialVersionUID = 1L;
	
	private String descricaoLikeRight;
	
	private Date inicioGreatherOrEqualsThan;
	
	private Date fimLowerOrEqualsThan;
	
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

	/**
	 * @return the inicioGreatherOrEqualsThan
	 */
	public Date getInicioGreatherOrEqualsThan() {
		return inicioGreatherOrEqualsThan;
	}

	/**
	 * @param inicioGreatherOrEqualsThan the inicioGreatherOrEqualsThan to set
	 */
	public void setInicioGreatherOrEqualsThan(Date inicioGreatherOrEqualsThan) {
		this.inicioGreatherOrEqualsThan = inicioGreatherOrEqualsThan;
	}

	/**
	 * @return the fimLowerOrEqualsThan
	 */
	public Date getFimLowerOrEqualsThan() {
		return fimLowerOrEqualsThan;
	}

	/**
	 * @param fimLowerOrEqualsThan the fimLowerOrEqualsThan to set
	 */
	public void setFimLowerOrEqualsThan(Date fimLowerOrEqualsThan) {
		this.fimLowerOrEqualsThan = fimLowerOrEqualsThan;
	}
}