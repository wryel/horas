package br.com.wryel.horas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.ClienteFilter;
import br.com.wryel.horas.entity.filter.ProjetoFilter;

@Stateless
public class ProjetoDAOImpl extends DAOImpl<Projeto, Long, ProjetoFilter> implements ProjetoDAO {

	private static final String SELECT = "SELECT p FROM Projeto p ";
	
	public ProjetoDAOImpl() {
		super(Projeto.class, ProjetoFilter.class);
	}

	@Override
	protected String createSqlQuery(ProjetoFilter filter) {
		
		StringBuffer sql = new StringBuffer(SELECT);
		
		List<String> wheres = new ArrayList<>();
		
		if (filter.getClienteFilter() != null) {
			
			sql.append(" JOIN p.cliente c ");
			
			if (filter.getClienteFilter().getIdEquals() != null) {
				
				wheres.add("c.id = :clienteIdEquals");
				
			}
			
		}
		
		if (!wheres.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(StringUtils.join(wheres, " AND "));
		}
		
		return sql.toString();
	}
	
	@Override
	protected void applyFilter(TypedQuery<?> query, ProjetoFilter filter) {
		
		super.applyFilter(query, filter);
		
		if (filter.getClienteFilter() != null) {
			
			if (filter.getClienteFilter().getIdEquals() != null) {
				
				query.setParameter("clienteIdEquals", filter.getClienteFilter().getIdEquals());
				
			}
			
		}
		
	}

	@Override
	public List<Projeto> listByCliente(Cliente cliente) {
		
		ProjetoFilter projetoFilter = new ProjetoFilter();
		projetoFilter.setClienteFilter(new ClienteFilter());
		projetoFilter.getClienteFilter().setIdEquals(cliente.getId());
		
		List<Projeto> projetos = list(projetoFilter);
		
		return projetos;
	}
}
