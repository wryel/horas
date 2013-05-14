package br.com.wryel.horas.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.wryel.horas.dao.ProjetoDAO;
import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.ProjetoFilter;

@Stateless
public class ProjetoBusinessImpl extends BusinessImpl<Projeto, Long, ProjetoFilter, ProjetoDAO> implements ProjetoBusiness  {

	public ProjetoBusinessImpl() {
		super(Projeto.class);
	}

	@Override
	@EJB
	public void setDAO(ProjetoDAO entityDAO) {
		this.dao = entityDAO;
	}

	@Override
	public List<Projeto> listByCliente(Cliente cliente) {
		List<Projeto> projetos = dao.listByCliente(cliente);
		return projetos;
	}
}
