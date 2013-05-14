package br.com.wryel.horas.business;

import javax.ejb.Local;

import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.filter.ClienteFilter;

@Local
public interface ClienteBusiness extends Business<Cliente, Integer, ClienteFilter> {
	
}
