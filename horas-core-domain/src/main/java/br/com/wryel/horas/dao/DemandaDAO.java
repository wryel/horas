package br.com.wryel.horas.dao;

import javax.ejb.Local;

import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.filter.DemandaFilter;

@Local
public interface DemandaDAO extends DAO<Demanda, Integer, DemandaFilter> {
	
}
