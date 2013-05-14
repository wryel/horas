package br.com.wryel.horas.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.wryel.horas.business.ApontamentoBusiness;
import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ApontamentoController extends AbstractController<Apontamento> {

	@EJB
	private ApontamentoBusiness apontamentoBusiness;
	
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
		FacesUtil.getInstance().getFlashScope().put(flahKey(), new Apontamento());
		return nav(entrada());
	}
	
	public String visualizarApontamentos() {
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		FacesUtil.getInstance().getFlashScope().put(demandaController.flahKey(), demandaController.getBean());
		return nav(visualizarApontamentosDeDemanda());
	}
	
	public String pesquisarDemandas() {
		DemandaController demandaController = FacesUtil.getInstance().getController(DemandaController.class);
		demandaController.pesquisar();
		return nav(pesquisarDemandaParaLancarApontamento());
	}
}
