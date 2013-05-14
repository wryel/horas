package br.com.wryel.horas.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.wryel.horas.AppContext;
import br.com.wryel.horas.business.BusinessException;
import br.com.wryel.horas.business.ClienteBusiness;
import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.filter.ClienteFilter;
import br.com.wryel.horas.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ClienteController extends AbstractController<Cliente> implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ClienteBusiness clienteBusiness;

	private ClienteFilter filter = new ClienteFilter();
	
	public ClienteController() {
		super(Cliente.class);
	}

	/**
	 * @return the filter
	 */
	public ClienteFilter getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(ClienteFilter filter) {
		this.filter = filter;
	}



	public String entrada() {
		return Navegacao.Cliente.ENTRADA;
	}
	
	public String listagem() {
		return Navegacao.Cliente.LISTAGEM;
	}
	
	public String adicionar() {
		FacesUtil.getInstance().getFlashScope().put(AppContext.ACAO, AppContext.ADICIONAR);
		FacesUtil.getInstance().getFlashScope().put(flahKey(), new Cliente());
		return nav(entrada());
	}
	
	public String pesquisar() {
		list = clienteBusiness.list(filter);
		return nav(listagem());
	}
	
	public String salvarAdicionar() throws BusinessException {
		clienteBusiness.insert(bean);
		return nav(listagem());
	}
	
	public String salvarEditar() throws BusinessException {
		clienteBusiness.update(bean);
		return nav(listagem());
	}
	
	public String excluir() throws BusinessException {
		clienteBusiness.delete(bean);
		return nav(listagem());
	}
}
