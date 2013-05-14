package br.com.wryel.horas.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.ProjetoFilter;

@Local
public interface ProjetoDAO extends DAO<Projeto, Long, ProjetoFilter> {
	
	public List<Projeto> listByCliente(Cliente cliente);
}
