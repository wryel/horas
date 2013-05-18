package br.com.wryel.horas.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.wryel.horas.business.ApontamentoBusiness;
import br.com.wryel.horas.business.BusinessException;
import br.com.wryel.horas.business.DemandaBusiness;
import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.util.FacesUtil;
import br.com.wryel.horas.util.SessionUtil;

@ManagedBean
@ViewScoped
public class ApontamentoController extends AbstractController<Apontamento> {

	@EJB
	private ApontamentoBusiness apontamentoBusiness;
	
	@EJB
	private DemandaBusiness demandaBusiness;
	
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
	
	public String adicionar() {
		
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		
		Demanda demanda = demandaController.getBean();
		
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
	
	public String salvarAdicionar() throws BusinessException {
		
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		
		Demanda demanda = demandaController.getBean();
		
		getBean().setDemanda(demanda);
		getBean().setUsuario(SessionUtil.getInstance().getUsuarioLogado());
		
		apontamentoBusiness.insert(getBean());
		
		FacesUtil.getInstance().showInfo("apontamento.salvo");
		
		FacesUtil.getInstance().getFlashScope().put(demandaController.flashEntityKey(), demanda);
		
		return nav(visualizarApontamentosDeDemanda());
	}
}
