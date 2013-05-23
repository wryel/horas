package br.com.wryel.horas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.filter.ApontamentoFilter;
import br.com.wryel.horas.entity.filter.DemandaFilter;

@Stateless
public class ApontamentoDAOImpl extends DAOImpl<Apontamento, Integer, ApontamentoFilter> implements ApontamentoDAO {

	private static final String SELECT = "SELECT a FROM Apontamento a ";
	
	public ApontamentoDAOImpl() {
		super(Apontamento.class, ApontamentoFilter.class);
	}

	@Override
	protected String createSqlQuery(ApontamentoFilter filter) {
		
		StringBuilder sql = new StringBuilder(SELECT);
		
		List<String> wheres = new ArrayList<>();
		
		if (StringUtils.isNotEmpty(filter.getDescricaoLikeRight())) {
			
			wheres.add("a.descricao LIKE :descricaoLikeRight");
			
		}
		
		if (filter.getDemandaFilter() != null) {
			
			if (filter.getDemandaFilter().getIdEquals() != null) {
				
				wheres.add("a.demanda.id = :demandaIdEquals");
				
			}
			
		}
		
		if (!wheres.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(StringUtils.join(wheres, " AND "));
		}
		
		return sql.toString();
	}
	
	@Override
	protected void applyFilter(TypedQuery<?> query, ApontamentoFilter filter) {
		
		super.applyFilter(query, filter);
		
		if (StringUtils.isNotEmpty(filter.getDescricaoLikeRight())) {
			
			query.setParameter("descricaoLikeRight", filter.getDescricaoLikeRight() + "%");
			
		}
		
		if (filter.getDemandaFilter() != null) {
			
			if (filter.getDemandaFilter().getIdEquals() != null) {
				
				query.setParameter("demandaIdEquals", filter.getDemandaFilter().getIdEquals());
				
			}
			
		}
		
	}

	@Override
	public List<Apontamento> listApontamentosPorDemanda(Demanda demanda) {
		
		ApontamentoFilter apontamentoFilter = new ApontamentoFilter();
		
		apontamentoFilter.setDemandaFilter(new DemandaFilter());
		
		apontamentoFilter.getDemandaFilter().setIdEquals(demanda.getId());
		
		List<Apontamento> apontamentos = list(apontamentoFilter);
		
		return apontamentos;
	}
}
