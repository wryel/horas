package br.com.wryel.horas.business;

import javax.ejb.Local;

import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.entity.filter.ApontamentoFilter;

@Local
public interface ApontamentoBusiness extends Business<Apontamento, Integer, ApontamentoFilter> {
	
}
