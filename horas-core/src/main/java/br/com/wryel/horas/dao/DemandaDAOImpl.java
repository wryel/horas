package br.com.wryel.horas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.DemandaFilter;
import br.com.wryel.horas.entity.filter.ProjetoFilter;

@Stateless
public class DemandaDAOImpl extends DAOImpl<Demanda, Long, DemandaFilter> implements DemandaDAO {

	private static final String SELECT = "SELECT d FROM Demanda d ";
	
	public DemandaDAOImpl() {
		super(Demanda.class, DemandaFilter.class);
	}

	@Override
	protected String createSqlQuery(DemandaFilter filter) {
		
		StringBuffer sql = new StringBuffer(SELECT);

		List<String> wheres = new ArrayList<>();
		
		if (filter.getProjetoFilter() != null) {
			
			if (filter.getProjetoFilter().getIdEquals() != null) {
				
				wheres.add("d.projeto.id = :projetoIdEquals");
				
			}
			
		}
		
		if (filter.getProjetoFilter().getClienteFilter() != null) {
			
			if (filter.getProjetoFilter().getClienteFilter().getIdEquals() != null) {
				
				wheres.add("d.projeto.cliente.id = :projetoClienteIdEquals");
				
			}
			
		}
		
		if (!wheres.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(StringUtils.join(wheres, " AND "));
		}
		
		return sql.toString();
	}
	
	@Override
	protected void applyFilter(TypedQuery<?> query, DemandaFilter filter) {
	
		super.applyFilter(query, filter);
	
		if (filter.getProjetoFilter() != null) {
			
			if (filter.getProjetoFilter().getIdEquals() != null) {
				
				query.setParameter("projetoIdEquals", filter.getProjetoFilter().getIdEquals());
				
			}
			
		}
		
		if (filter.getProjetoFilter().getClienteFilter() != null) {
			
			if (filter.getProjetoFilter().getClienteFilter().getIdEquals() != null) {
				
				query.setParameter("projetoClienteIdEquals", filter.getProjetoFilter().getClienteFilter().getIdEquals());
				
			}
			
		}
		
	}

	@Override
	public List<Demanda> listByProjeto(Projeto projeto) {
		
		DemandaFilter demandaFilter = new DemandaFilter();
		demandaFilter.setProjetoFilter(new ProjetoFilter());
		demandaFilter.getProjetoFilter().setIdEquals(projeto.getId());
	
		List<Demanda> demandas = list(demandaFilter);
		
		return demandas;
	}

}
