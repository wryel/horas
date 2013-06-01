package br.com.wryel.horas.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.wryel.horas.dao.UsuarioDAO;
import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.entity.filter.UsuarioFilter;

@Stateless
public class UsuarioBusinessImpl extends BusinessImpl<Usuario, Integer, UsuarioFilter, UsuarioDAO> implements UsuarioBusiness {

	public UsuarioBusinessImpl() {
		super(Usuario.class);
	}

	@EJB
	@Override
	public void setDAO(UsuarioDAO entityDAO) {
		super.dao = entityDAO;
	}	
}
