package br.com.wryel.horas.business;

import javax.ejb.Local;

import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.filter.DemandaFilter;

@Local
public interface DemandaBusiness extends Business<Demanda, Integer, DemandaFilter> {
	
}
