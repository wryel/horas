package br.com.wryel.horas.business;

import java.util.List;

import javax.ejb.Local;

import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.ProjetoFilter;

@Local
public interface ProjetoBusiness extends Business<Projeto, Long, ProjetoFilter> {

	public List<Projeto> listByCliente(Cliente cliente);
}
