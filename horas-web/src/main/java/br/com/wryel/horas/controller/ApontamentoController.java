package br.com.wryel.horas.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import br.com.wryel.horas.business.ApontamentoBusiness;
import br.com.wryel.horas.business.BusinessException;
import br.com.wryel.horas.business.DemandaBusiness;
import br.com.wryel.horas.business.ProjetoBusiness;
import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.ApontamentoFilter;
import br.com.wryel.horas.entity.filter.ClienteFilter;
import br.com.wryel.horas.entity.filter.DemandaFilter;
import br.com.wryel.horas.entity.filter.ProjetoFilter;
import br.com.wryel.horas.util.FacesUtil;
import br.com.wryel.horas.util.SessionUtil;

@ManagedBean
@ViewScoped
public class ApontamentoController extends AbstractController<Apontamento> {

	@EJB
	private ApontamentoBusiness apontamentoBusiness;
	
	@EJB
	private DemandaBusiness demandaBusiness;
	
	@EJB
	private ProjetoBusiness projetoBusiness;
	
	@Inject
	private ApontamentoFilter filter;
	
	private static final long serialVersionUID = 1L;

	public ApontamentoController() {
		super(Apontamento.class);
	}
	
	@Override
	@PostConstruct
	public void postConstruct() {
		super.postConstruct();
		filter.setDemandaFilter(new DemandaFilter());
		filter.getDemandaFilter().setProjetoFilter(new ProjetoFilter());
		filter.getDemandaFilter().getProjetoFilter().setClienteFilter(new ClienteFilter());
	}
	
	public String pesquisarDemandaParaLancarApontamento() {
		return Navegacao.Apontamento.PESQUISAR_DEMANDA_PARA_LANCAR_APONTAMENTO;
	}
	
	public String visualizarApontamentosDeDemanda() {
		return Navegacao.Apontamento.VISUALIZAR_APONTAMENTOS_DE_DEMANDA;
	}
	
	public String entrada() {
		return Navegacao.Apontamento.ENTRADA;
	}
	
	public String listagem() {
		return Navegacao.Apontamento.LISTAGEM;
	}
	
	public String editar() {
		
		ClienteController clienteController = FacesUtil.getInstance().getController(ClienteController.class);
		
		ProjetoController projetoController = FacesUtil.getInstance().getController(ProjetoController.class);
		
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		
		Apontamento apontamento = apontamentoBusiness.get(getBean().getId());
		
		FacesUtil.getInstance().getFlashScope().put(ACTION, ACTION_EDIT);
		FacesUtil.getInstance().getFlashScope().put(flashEntityKey(), apontamento);
		FacesUtil.getInstance().getFlashScope().put(demandaController.flashEntityKey(), apontamento.getDemanda());
		FacesUtil.getInstance().getFlashScope().put(projetoController.flashEntityKey(), apontamento.getDemanda().getProjeto());
		FacesUtil.getInstance().getFlashScope().put(clienteController.flashEntityKey(), apontamento.getDemanda().getProjeto().getCliente());
		
		return nav(entrada());
	}
	
	
	
	public String adicionar() {
		
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		
		Demanda demanda = demandaController.getBean();
		
		FacesUtil.getInstance().getFlashScope().put(ACTION, ACTION_ADD);
		FacesUtil.getInstance().getFlashScope().put(flashEntityKey(), new Apontamento());
		FacesUtil.getInstance().getFlashScope().put(demandaController.flashEntityKey(), demanda);
		
		return nav(entrada());
	}
	
	public String pesquisar() {
		
		ClienteController clienteController = FacesUtil.getInstance().getController(ClienteController.class);
		
		ProjetoController projetoController = FacesUtil.getInstance().getController(ProjetoController.class);

		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		
		if (demandaController.getBean() != null) {
			filter.getDemandaFilter().setIdEquals(demandaController.getBean().getId());
		} else {
			filter.getDemandaFilter().setIdEquals(null);
		}

		if (projetoController.getBean() != null) {
			filter.getDemandaFilter().getProjetoFilter().setIdEquals(projetoController.getBean().getId());
		} else {
			filter.getDemandaFilter().getProjetoFilter().setIdEquals(null);
		}
		
		if (clienteController.getBean() != null) {
			filter.getDemandaFilter().getProjetoFilter().getClienteFilter().setIdEquals(clienteController.getBean().getId());
		} else {
			filter.getDemandaFilter().getProjetoFilter().getClienteFilter().setIdEquals(null);
		}

		list = apontamentoBusiness.list(filter);
		
		return nav(listagem());
	}
	
	public String salvar() {
		
		try {
			
			if (ACTION_ADD.equals(getAction())) {
				
				DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);

				getBean().setDemanda(demandaController.getBean());
				
				getBean().setUsuario(SessionUtil.getInstance().getUsuarioLogado());
				
				apontamentoBusiness.insert(getBean());

				FacesUtil.getInstance().showInfo("registro.inserido");

			} else {
				
				apontamentoBusiness.update(getBean());
				
				FacesUtil.getInstance().showInfo("registro.atualizado");
				
			}
			
		} catch (BusinessException businessException) {
			
			FacesUtil.getInstance().showBusinessError(businessException.getMessage());
			
			return entrada();
			
		}
		
		return listagem();
	}
	
	public String excluir() {
		try {
			apontamentoBusiness.delete(getBean());
		} catch(BusinessException businessException) {
			FacesUtil.getInstance().showBusinessError(businessException.getMessage());
		}
		return listagem();
	}
	
	public void mudouClienteEvento(AjaxBehaviorEvent ajaxBehaviorEvent) {
		ClienteController clienteController = FacesUtil.getInstance().getController(ClienteController.class);
		ProjetoController projetoController = FacesUtil.getInstance().getController(ProjetoController.class);
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		Cliente cliente = clienteController.getBean();
		if (cliente != null) {
			List<Projeto> projetos = projetoBusiness.listByCliente(cliente);
			projetoController.setList(projetos);
		} else {
			projetoController.cleanBean();
			projetoController.cleanList();
		}
		demandaController.cleanBean();
		demandaController.cleanList();
	}
	
	public void mudouProjetoEvento(AjaxBehaviorEvent ajaxBehaviorEvent) {
		ProjetoController projetoController = FacesUtil.getInstance().getController(ProjetoController.class);
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		Projeto projeto = projetoController.getBean();
		if (projeto != null) {
			List<Demanda> demandas = demandaBusiness.listByProjeto(projeto);
			demandaController.cleanBean();
			demandaController.setList(demandas);
		} else {
			demandaController.cleanBean();
			demandaController.cleanList();
		}
	}

	/**
	 * @return the filter
	 */
	public ApontamentoFilter getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(ApontamentoFilter filter) {
		this.filter = filter;
	}
}
