package br.com.wryel.horas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.entity.filter.UsuarioFilter;

@Stateless
public class UsuarioDAOImpl extends DAOImpl<Usuario, Integer, UsuarioFilter> implements UsuarioDAO {

	private static final String SELECT = "SELECT u FROM Usuario u ";
	
	public UsuarioDAOImpl() {
		super(Usuario.class, UsuarioFilter.class);
	}

	@Override
	protected String createSqlQuery(UsuarioFilter filter) {
		
		StringBuffer sql = new StringBuffer(SELECT);
		
		List<String> wheres = new ArrayList<>();
		
		if (StringUtils.isNotBlank(filter.getEmailEquals())) {	
			wheres.add("u.email = :emailEquals");			
		}
		
		if (StringUtils.isNotBlank(filter.getEmailLikeRight())) {
			wheres.add("u.email LIKE :emailLikeRight");
		}
		
		if (StringUtils.isNotBlank(filter.getNomeLikeRight())) {
			wheres.add("u.nome LIKE :nomeLikeRight");
		}
		
		if (StringUtils.isNotEmpty(filter.getAtivoEquals())) {
			wheres.add("u.ativo = :ativoEquals");
		}
		
		if (StringUtils.isNotEmpty(filter.getTipoEquals())) {
			wheres.add("u.tipo = :tipoEquals");
		}
		
		if (StringUtils.isNotEmpty(filter.getTipoEquals())) {
			wheres.add("u.senha = :senhaEquals");
		}
		
		if (CollectionUtils.isNotEmpty(wheres)) {
			sql.append(" WHERE ");
			sql.append(StringUtils.join(wheres, " AND "));
		}
		
		return sql.toString();
	}
	
	@Override
	protected void applyFilter(TypedQuery<?> query, UsuarioFilter filter) {
		
		super.applyFilter(query, filter);
		
		if (StringUtils.isNotBlank(filter.getEmailEquals())) {
			query.setParameter("emailEquals", filter.getEmailEquals().trim());
		}
		
		if (StringUtils.isNotBlank(filter.getEmailLikeRight())) {
			query.setParameter("emailLikeRight", filter.getEmailLikeRight().trim() + "%");
		}
		
		if (StringUtils.isNotBlank(filter.getNomeLikeRight())) {
			query.setParameter("nomeLikeRight", filter.getNomeLikeRight().trim() + "%");
		}
		
		if (StringUtils.isNotEmpty(filter.getAtivoEquals())) {
			query.setParameter("ativoEquals", filter.getAtivoEquals());
		}
		
		if (StringUtils.isNotEmpty(filter.getTipoEquals())) {
			query.setParameter("tipoEquals", filter.getTipoEquals());
		}
		
		if (StringUtils.isNotEmpty(filter.getTipoEquals())) {
			query.setParameter("senhaEquals", filter.getSenhaEquals());
		}
	}
}
