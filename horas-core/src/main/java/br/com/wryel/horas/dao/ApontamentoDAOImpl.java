package br.com.wryel.horas.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.entity.filter.ApontamentoFilter;

@Stateless
public class ApontamentoDAOImpl extends DAOImpl<Apontamento, Integer, ApontamentoFilter> implements ApontamentoDAO {

	private static final String SELECT = "SELECT a FROM Apontamento a ";
	
	public ApontamentoDAOImpl() {
		super(Apontamento.class, ApontamentoFilter.class);
	}

	@Override
	protected String createSqlQuery(ApontamentoFilter filter) {
		
		StringBuilder sql = new StringBuilder(SELECT);
		
		return sql.toString();
	}
	
	@Override
	protected void applyFilter(TypedQuery<?> query, ApontamentoFilter filter) {
		
		super.applyFilter(query, filter);
		
	}
}
