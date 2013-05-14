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
		FacesUtil.getInstance().getFlashScope().put(flahKey(), new Demanda());
		return nav(entrada()) + "? action=add";
	}
	
	public String salvarAdicionar() throws BusinessException {
		demandaBusiness.insert(getBean());
		return nav(listagem());
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
