package br.com.wryel.horas.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.wryel.horas.business.BusinessException;
import br.com.wryel.horas.business.ProjetoBusiness;
import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.ClienteFilter;
import br.com.wryel.horas.entity.filter.ProjetoFilter;
import br.com.wryel.horas.exception.HorasRuntimeException;
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
	
	@Override
	@PostConstruct
	public void postConstruct() {
		super.postConstruct();
		filter.setClienteFilter(new ClienteFilter());
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
		
		ClienteController clienteController = FacesUtil.getInstance().getController(ClienteController.class);
		
		if (clienteController.getBean() != null) {
			
			Cliente cliente = clienteController.getBean();
			
			filter.getClienteFilter().setIdEquals(cliente.getId());
			
		} else {
			
			filter.getClienteFilter().setIdEquals(null);
			
		}

		list = projetoBusiness.list(filter);
		
		return nav(listagem());
	}
	
	public String adicionar() {
		
		FacesUtil.getInstance().getFlashScope().put(ACTION, ACTION_ADD);
		
		FacesUtil.getInstance().getFlashScope().put(flashEntityKey(), new Projeto());
		
		return nav(entrada());
	}
	
	public String editar() {
		
		FacesUtil.getInstance().getFlashScope().put(ACTION, ACTION_EDIT);
		
		FacesUtil.getInstance().getFlashScope().put(flashEntityKey(), getBean());
		
		ClienteController clienteController = FacesUtil.getInstance().getController(ClienteController.class);
		
		FacesUtil.getInstance().getFlashScope().put(clienteController.flashEntityKey(), getBean().getCliente());
		
		return nav(entrada());
	}
	
	public String deletar() {
		try {
			projetoBusiness.delete(getBean());
			FacesUtil.getInstance().showInfo("registro.deletado");	
			getList().remove(getBean());			
		} catch (BusinessException businessException) {
			FacesUtil.getInstance().showBusinessError(businessException.getMessage());
		}
		return nav(listagem());
	}
	
	public String salvar() {
		
		ClienteController clienteController = FacesUtil.getInstance().getController(ClienteController.class);
		
		Cliente cliente = clienteController.getBean();
		
		getBean().setCliente(cliente);
		
		try {
			
			if (ACTION_ADD.equals(getAction())) {
				
				projetoBusiness.insert(getBean());
				
				FacesUtil.getInstance().showInfo("registro.inserido");
				
			} else if (ACTION_EDIT.equals(getAction())) {
				
				projetoBusiness.update(getBean());
				
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
}
