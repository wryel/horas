package br.com.wryel.horas.business;

import javax.ejb.Local;

import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.entity.filter.UsuarioFilter;

@Local
public interface UsuarioBusiness extends Business<Usuario, Integer, UsuarioFilter> {
	
}
