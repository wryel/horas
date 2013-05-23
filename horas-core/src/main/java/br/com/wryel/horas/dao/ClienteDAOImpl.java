package br.com.wryel.horas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.filter.ClienteFilter;

@Stateless
public class ClienteDAOImpl extends DAOImpl<Cliente, Integer, ClienteFilter> implements ClienteDAO {

	private static final String SELECT = "SELECT c FROM Cliente c ";
	
	public ClienteDAOImpl() {
		super(Cliente.class, ClienteFilter.class);
	}

	@Override
	protected String createSqlQuery(ClienteFilter filter) {
		
		StringBuffer sql = new StringBuffer(SELECT);
		
		List<String> wheres = new ArrayList<>();
		
		if (StringUtils.isNotBlank(filter.getNomeEqual())) {
			wheres.add("c.nome = :nomeEqual");
		}
		
		if (StringUtils.isNotBlank(filter.getNomeLikeLeft())) {
			wheres.add("c.nome LIKE :nomeLikeLeft");
		}
		
		if (StringUtils.isNotBlank(filter.getNomeLikeRight())) {
			wheres.add("c.nome LIKE :nomeLikeRight");
		}
		
		if (!wheres.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(StringUtils.join(wheres, " AND "));
		}
		
		return sql.toString();
	}
	
	@Override
	protected void applyFilter(TypedQuery<?> query, ClienteFilter filter) {
		
		super.applyFilter(query, filter);
		
		if (StringUtils.isNotBlank(filter.getNomeEqual())) {
			query.setParameter("nomeEqual", filter.getNomeEqual().trim());
		}
		
		if (StringUtils.isNotBlank(filter.getNomeLikeLeft())) {	
			query.setParameter("nomeLikeLeft", "%" + filter.getNomeLikeLeft());
		}
		
		if (StringUtils.isNotBlank(filter.getNomeLikeRight())) {	
			query.setParameter("nomeLikeRight", filter.getNomeLikeRight() + "%");
		}
	}
}
