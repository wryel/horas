package br.com.wryel.horas.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.collections.CollectionUtils;

import br.com.wryel.horas.dao.ProjetoDAO;
import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.ProjetoFilter;

@Stateless
public class ProjetoBusinessImpl extends BusinessImpl<Projeto, Long, ProjetoFilter, ProjetoDAO> implements ProjetoBusiness  {

	@EJB
	private DemandaBusiness demandaBusiness;
	
	public ProjetoBusinessImpl() {
		super(Projeto.class);
	}
	
	@Override
	protected boolean validateInsert(Projeto projeto) throws BusinessException {
		if (projeto.getCliente() == null) {	
			throw new BusinessException("Não é possivel ter um projeto sem um cliente associado");
		}		
		return super.validateInsert(projeto);
	}
	
	@Override
	protected boolean validateUpdate(Projeto projeto) throws BusinessException {
		if (projeto.getCliente() == null) {	
			throw new BusinessException("Não é possivel ter um projeto sem um cliente associado");
		}
		return super.validateUpdate(projeto);
	}
	
	@Override
	public void delete(Projeto projeto) throws BusinessException {
		Projeto projetoParaDeletar = dao.get(projeto.getId());
		List<Demanda> demandas = demandaBusiness.listByProjeto(projetoParaDeletar);
		if (CollectionUtils.isNotEmpty(demandas)) {
			throw new BusinessException("Existem demandas cadastradas para o projeto " + projeto.getNome());
		}
		super.delete(projetoParaDeletar);
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
