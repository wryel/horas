package br.com.wryel.horas.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.collections.CollectionUtils;

import br.com.wryel.horas.dao.DemandaDAO;
import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.DemandaFilter;

@Stateless
public class DemandaBusinessImpl extends BusinessImpl<Demanda, Long, DemandaFilter, DemandaDAO> implements DemandaBusiness {

	@EJB
	private ApontamentoBusiness apontamentoBusiness;
	
	public DemandaBusinessImpl() {
		super(Demanda.class);
	}
	
	@Override
	protected boolean validateInsert(Demanda demanda) throws BusinessException {
		if (demanda.getProjeto() == null) {
			throw new BusinessException("Uma demanda deve estar associada a um projeto");
		}
		return true;
	}
	
	@Override
	protected boolean validateUpdate(Demanda demanda) throws BusinessException {
		if (demanda.getProjeto() == null) {
			throw new BusinessException("Uma demanda deve estar associada a um projeto");
		}		
		return true;
	}

	@Override
	public void delete(Demanda demanda) throws BusinessException {
		Demanda demandaParaDeletar = dao.get(demanda.getId());
		List<Apontamento> apontamentos = apontamentoBusiness.listaPorDemanda(demandaParaDeletar);
		if (CollectionUtils.isNotEmpty(apontamentos)) {
			throw new BusinessException("Existem apontamentos para a demanda " + demandaParaDeletar.getNome());
		}
		super.delete(demandaParaDeletar);
	}
	
	@EJB
	@Override
	public void setDAO(DemandaDAO entityDAO) {
		this.dao = entityDAO;
	}

	@Override
	public List<Demanda> listByProjeto(Projeto projeto) {
		List<Demanda> demandas = dao.listByProjeto(projeto);
		return demandas;
	}
}
