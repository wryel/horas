package br.com.wryel.horas.dao;

import javax.ejb.Stateless;

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
		
		return sql.toString();
	}
}
