package br.com.wryel.horas.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import br.com.wryel.horas.business.BusinessException;
import br.com.wryel.horas.business.DemandaBusiness;
import br.com.wryel.horas.business.ProjetoBusiness;
import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.DemandaFilter;
import br.com.wryel.horas.exception.HorasRuntimeException;
import br.com.wryel.horas.util.FacesUtil;

@ManagedBean
@ViewScoped
public class DemandaController extends AbstractController<Demanda> {

	private static final long serialVersionUID = 1L;

	@EJB
	private DemandaBusiness demandaBusiness;
	
	@EJB 
	private ProjetoBusiness projetoBusiness;
	
	@Inject
	private DemandaFilter filter;
	
	public DemandaController() {
		super(Demanda.class);
	}
	
	public String entrada() {
		return Navegacao.Demanda.ENTRADA;
	}
	
	public String listagem() {
		return Navegacao.Demanda.LISTAGEM;
	}
	
	public String pesquisar() {
		list = demandaBusiness.list(getFilter());
		return nav(listagem());
	}
	
	public String adicionar() {
		FacesUtil.getInstance().getFlashScope().put(ACTION, ACTION_ADD);
		FacesUtil.getInstance().getFlashScope().put(flashEntityKey(), new Demanda());
		return nav(entrada());
	}
	
	public String salvar() throws BusinessException {
		
		ProjetoController projetoController = FacesUtil.getInstance().getController(ProjetoController.class);
		
		Projeto projeto = projetoController.getBean();
		
		getBean().setProjeto(projeto);
		
		try {
			
			if (ACTION_ADD.equals(getAction())) {
				
				demandaBusiness.insert(getBean());
				
				FacesUtil.getInstance().showInfo("registro.inserido");
				
			} else if (ACTION_EDIT.equals(getAction())) {
				
				demandaBusiness.update(getBean());
				
				FacesUtil.getInstance().showInfo("registro.atualizado");
				
			} else {
				
				throw new HorasRuntimeException();
				
			}

		} catch (BusinessException businessException) {
			FacesUtil.getInstance().showBusinessError(businessException.getMessage());
			return nav(entrada());
		}		
		
		return nav(listagem());
	}
	
	public String editar() {
		
		ClienteController clienteController = FacesUtil.getInstance().getController(ClienteController.class);
		
		ProjetoController projetoController = FacesUtil.getInstance().getController(ProjetoController.class);
		
		FacesUtil.getInstance().getFlashScope().put(ACTION, ACTION_EDIT);
		
		FacesUtil.getInstance().getFlashScope().put(flashEntityKey(), getBean());
		
		FacesUtil.getInstance().getFlashScope().put(projetoController.flashEntityKey(), getBean().getProjeto());
		
		FacesUtil.getInstance().getFlashScope().put(clienteController.flashEntityKey(), getBean().getProjeto().getCliente());
		
		List<Projeto> projetos = projetoBusiness.listByCliente(getBean().getProjeto().getCliente());
		
		FacesUtil.getInstance().getFlashScope().put(projetoController.flashListKey(), projetos);		
		
		return nav(entrada());
	}

	/**
	 * @return the filter
	 */
	public DemandaFilter getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(DemandaFilter filter) {
		this.filter = filter;
	}
	
	public String deletar() throws BusinessException {
		try {
			demandaBusiness.delete(bean);
			FacesUtil.getInstance().showInfo("registro.deletado");
			getList().remove(bean);
		} catch (BusinessException businessException) {
			FacesUtil.getInstance().showBusinessError(businessException.getMessage());
		}
		return nav(listagem());
	}
	
	public void mudouClienteEvento(AjaxBehaviorEvent ajaxBehaviorEvent) {
		ClienteController clienteController = FacesUtil.getInstance().getController(ClienteController.class);
		ProjetoController projetoController = FacesUtil.getInstance().getController(ProjetoController.class);
		Cliente cliente = clienteController.getBean();
		if (cliente != null) {
			List<Projeto> projetos = projetoBusiness.listByCliente(cliente);
			projetoController.setList(projetos);
		} else {
			clienteController.cleanList();
			projetoController.cleanList();
		}
	}
}
