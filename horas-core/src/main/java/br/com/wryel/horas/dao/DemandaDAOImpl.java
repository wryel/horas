package br.com.wryel.horas.dao;

import javax.ejb.Stateless;

import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.filter.DemandaFilter;

@Stateless
public class DemandaDAOImpl extends DAOImpl<Demanda, Integer, DemandaFilter> implements DemandaDAO {

	private static final String SELECT = "SELECT d FROM Demanda d ";
	
	public DemandaDAOImpl() {
		super(Demanda.class, DemandaFilter.class);
	}

	@Override
	protected String createSqlQuery(DemandaFilter filter) {
		
		StringBuffer sql = new StringBuffer(SELECT);
		
		return sql.toString();
	}
}
