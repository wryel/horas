package br.com.wryel.horas.business;

import java.util.List;

import javax.ejb.Local;

import br.com.wryel.horas.dto.Somatoria;
import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.filter.ApontamentoFilter;

@Local
public interface ApontamentoBusiness extends Business<Apontamento, Integer, ApontamentoFilter> {
	
	public List<Apontamento> listaPorDemanda(Demanda demanda);
	
	public Somatoria somar(ApontamentoFilter apontamentoFilter);
}
