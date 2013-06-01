package br.com.wryel.horas.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

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
			
			if (filter.getDemandaFilter().getProjetoFilter() != null) {
				
				if (filter.getDemandaFilter().getProjetoFilter().getIdEquals() != null) {
					
					wheres.add("a.demanda.projeto.id = :demandaProjetoId");
					
				}
				
				if (filter.getDemandaFilter().getProjetoFilter().getClienteFilter() != null) {
					
					if (filter.getDemandaFilter().getProjetoFilter().getClienteFilter().getIdEquals() != null) {
						
						wheres.add("a.demanda.projeto.cliente.id = :demandaProjetoClienteId");
						
					}
					
				}
				
			}
			
		}
		
		if (filter.getInicioGreatherOrEqualsThan() != null) {
			
			wheres.add("a.inicio >= :inicioGreatherOrEqualsThan");
			
		}
		
		if (filter.getFimLowerOrEqualsThan() != null) {
			
			wheres.add("a.fim <= :fimLowerOrEqualsThan");
			
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
			
			if (filter.getDemandaFilter().getProjetoFilter() != null) {
				
				if (filter.getDemandaFilter().getProjetoFilter().getIdEquals() != null) {
					
					query.setParameter("demandaProjetoId", filter.getDemandaFilter().getProjetoFilter().getIdEquals());
					
				}
				
				if (filter.getDemandaFilter().getProjetoFilter().getClienteFilter() != null) {
					
					if (filter.getDemandaFilter().getProjetoFilter().getClienteFilter().getIdEquals() != null) {
						
						query.setParameter("demandaProjetoClienteId", filter.getDemandaFilter().getProjetoFilter().getClienteFilter().getIdEquals());
						
					}
					
				}
				
			}
			
		}
		
		if (filter.getInicioGreatherOrEqualsThan() != null) {
			
			Date inicioGreatherOrEqualsThan = filter.getInicioGreatherOrEqualsThan();
			inicioGreatherOrEqualsThan = DateUtils.truncate(inicioGreatherOrEqualsThan, Calendar.DAY_OF_MONTH);
			
			query.setParameter("inicioGreatherOrEqualsThan", inicioGreatherOrEqualsThan, TemporalType.DATE);
			
		}
		
		if (filter.getFimLowerOrEqualsThan() != null) {
			
			Date fimLowerOrEqualsThan = filter.getFimLowerOrEqualsThan();
			fimLowerOrEqualsThan = DateUtils.truncate(fimLowerOrEqualsThan, Calendar.DAY_OF_MONTH);
			
			query.setParameter("fimLowerOrEqualsThan", fimLowerOrEqualsThan, TemporalType.DATE);
			
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
