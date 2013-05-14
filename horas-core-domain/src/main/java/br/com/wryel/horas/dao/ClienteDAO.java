package br.com.wryel.horas.dao;

import javax.ejb.Local;

import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.filter.ClienteFilter;

@Local
public interface ClienteDAO extends DAO<Cliente, Integer, ClienteFilter> {
	
}
