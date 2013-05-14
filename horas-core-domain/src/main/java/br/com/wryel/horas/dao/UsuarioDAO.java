package br.com.wryel.horas.dao;

import javax.ejb.Local;

import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.entity.filter.UsuarioFilter;

@Local
public interface UsuarioDAO extends DAO<Usuario, Integer, UsuarioFilter> {
	
}
