package br.com.wryel.horas.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.wryel.horas.business.BusinessException;
import br.com.wryel.horas.business.ProjetoBusiness;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.ProjetoFilter;
import br.com.wryel.horas.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProjetoController extends AbstractController<Projeto> {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProjetoBusiness projetoBusiness;

	@Inject
	private ProjetoFilter filter;
	
	public ProjetoController() {
		super(Projeto.class);
	}

	/**
	 * @return the projetoBusiness
	 */
	public ProjetoBusiness getProjetoBusiness() {
		return projetoBusiness;
	}

	/**
	 * @param projetoBusiness the projetoBusiness to set
	 */
	public void setProjetoBusiness(ProjetoBusiness projetoBusiness) {
		this.projetoBusiness = projetoBusiness;
	}

	/**
	 * @return the filter
	 */
	public ProjetoFilter getFilter() {
		return filter;
	}

	

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(ProjetoFilter filter) {
		this.filter = filter;
	}

	public String entrada() {
		return Navegacao.Projeto.ENTRADA;
	}
	
	public String listagem() {
		return Navegacao.Projeto.LISTAGEM;
	}
	
	public String pesquisar() {
		super.list = projetoBusiness.list(new ProjetoFilter());
		return nav(listagem());
	}
	
	public String adicionar() {
		FacesUtil.getInstance().getFlashScope().put(flahKey(), new Projeto());
		return nav(entrada()) + "?action=add";
	}
	
	public String salvarAdicionar() throws BusinessException {
		projetoBusiness.insert(getBean());
		return nav(listagem());
	}
}
