package br.com.wryel.horas.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.wryel.horas.dao.ApontamentoDAO;
import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.entity.filter.ApontamentoFilter;

@Stateless
public class ApontamentoBusinessImpl extends BusinessImpl<Apontamento, Integer, ApontamentoFilter, ApontamentoDAO> implements ApontamentoBusiness {

	public ApontamentoBusinessImpl() {
		super(Apontamento.class);
	}

	@EJB
	@Override
	public void setDAO(ApontamentoDAO entityDAO) {
		this.dao = entityDAO;
	}	
}
