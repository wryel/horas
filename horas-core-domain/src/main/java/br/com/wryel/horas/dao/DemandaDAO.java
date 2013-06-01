package br.com.wryel.horas.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.DemandaFilter;

@Local
public interface DemandaDAO extends DAO<Demanda, Long, DemandaFilter> {
	
	public List<Demanda> listByProjeto(Projeto projeto);
}
