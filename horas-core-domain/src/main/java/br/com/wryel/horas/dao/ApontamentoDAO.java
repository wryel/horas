package br.com.wryel.horas.dao;

import javax.ejb.Local;

import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.entity.filter.ApontamentoFilter;

@Local
public interface ApontamentoDAO extends DAO<Apontamento, Integer, ApontamentoFilter> {
	
}
