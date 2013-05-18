package br.com.wryel.horas.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.wryel.horas.dao.DemandaDAO;
import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.filter.DemandaFilter;

@Stateless
public class DemandaBusinessImpl extends BusinessImpl<Demanda, Long, DemandaFilter, DemandaDAO> implements DemandaBusiness {

	public DemandaBusinessImpl() {
		super(Demanda.class);
	}

	@EJB
	@Override
	public void setDAO(DemandaDAO entityDAO) {
		this.dao = entityDAO;
	}
}
