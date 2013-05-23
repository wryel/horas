package br.com.wryel.horas.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.wryel.horas.business.ApontamentoBusiness;
import br.com.wryel.horas.business.BusinessException;
import br.com.wryel.horas.business.DemandaBusiness;
import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.filter.ApontamentoFilter;
import br.com.wryel.horas.entity.filter.DemandaFilter;
import br.com.wryel.horas.util.FacesUtil;
import br.com.wryel.horas.util.SessionUtil;

@ManagedBean
@ViewScoped
public class ApontamentoController extends AbstractController<Apontamento> {

	@EJB
	private ApontamentoBusiness apontamentoBusiness;
	
	@EJB
	private DemandaBusiness demandaBusiness;
	
	@Inject
	private ApontamentoFilter filter;
	
	private static final long serialVersionUID = 1L;

	public ApontamentoController() {
		super(Apontamento.class);
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
	
	public String editar() {
		
		Apontamento apontamento = apontamentoBusiness.get(getBean().getId());
		
		FacesUtil.getInstance().getFlashScope().put(ACTION, ACTION_EDIT);
		FacesUtil.getInstance().getFlashScope().put(flashEntityKey(), apontamento);
		
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		
		FacesUtil.getInstance().getFlashScope().put(demandaController.flashEntityKey(), apontamento.getDemanda());
		
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
	
	public String visualizarApontamentos() {
		
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		ApontamentoController apontamentoController = FacesUtil.getInstance().getController(ApontamentoController.class);
		
		List<Apontamento> apontamentos = apontamentoBusiness.listApontamentosPorDemanda(demandaController.getBean());
	
		FacesUtil.getInstance().getFlashScope().put(demandaController.flashEntityKey(), demandaController.getBean());
		FacesUtil.getInstance().getFlashScope().put(apontamentoController.flashListKey(), apontamentos);
		
		return nav(visualizarApontamentosDeDemanda());
	}
	
	public String pesquisarDemandas() {
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		demandaController.pesquisar();
		return nav(pesquisarDemandaParaLancarApontamento());
	}
	
	public String salvarEditar() throws BusinessException {
		
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		
		Demanda demanda = demandaBusiness.get(demandaController.getBean().getId());
		
		getBean().setDemanda(demanda);

		apontamentoBusiness.update(getBean());
		
		FacesUtil.getInstance().showInfo("registro.atualizado");
		
		FacesUtil.getInstance().getFlashScope().put(flashListKey(), apontamentoBusiness.listApontamentosPorDemanda(demanda));
		
		return nav(visualizarApontamentosDeDemanda()); 
		
	}
	
	public String salvarAdicionar() throws BusinessException {
		
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		
		Demanda demanda = demandaController.getBean();
		
		getBean().setDemanda(demanda);
		
		getBean().setUsuario(SessionUtil.getInstance().getUsuarioLogado());
		
		apontamentoBusiness.insert(getBean());
		
		FacesUtil.getInstance().showInfo("registro.inserido");
		
		FacesUtil.getInstance().getFlashScope().put(demandaController.flashEntityKey(), demanda);
		
		return nav(visualizarApontamentosDeDemanda());
	}
	
	public String pesquisarApontamentosEmDemanda() {

		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
	
		DemandaFilter demandaFilter = new DemandaFilter();
		demandaFilter.setIdEquals(demandaController.getBean().getId());
		
		filter.setDemandaFilter(demandaFilter);
		
		List<Apontamento> apontamentos = apontamentoBusiness.list(filter);
		
		setList(apontamentos);
		
		return nav(visualizarApontamentosDeDemanda());
	}
	
	public String voltarParaVisualizacaoDeApontamentosDeDemanda() {
		
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		
		Demanda demanda = demandaController.getBean();
		
		FacesUtil.getInstance().getFlashScope().put(demandaController.flashEntityKey(), demanda);
		
		List<Apontamento> apontamentos = apontamentoBusiness.listApontamentosPorDemanda(demanda);
		
		FacesUtil.getInstance().getFlashScope().put(flashListKey(), apontamentos);
		
		return nav(visualizarApontamentosDeDemanda());
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
