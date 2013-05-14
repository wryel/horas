package br.com.wryel.horas.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.wryel.horas.dao.ClienteDAO;
import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.filter.ClienteFilter;

@Stateless
public class ClienteBusinessImpl extends BusinessImpl<Cliente, Integer, ClienteFilter, ClienteDAO> implements ClienteBusiness {
	
	public ClienteBusinessImpl() {
		super(Cliente.class);
	}

	@EJB
	@Override
	public void setDAO(ClienteDAO entityDAO) {
		this.dao = entityDAO;
	}
}
