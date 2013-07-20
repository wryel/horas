package br.com.wryel.horas.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.wryel.horas.business.BusinessException;
import br.com.wryel.horas.business.UsuarioBusiness;
import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.entity.filter.UsuarioFilter;
import br.com.wryel.horas.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

	@EJB
	private UsuarioBusiness usuarioBusiness;
	
	@Inject
	private UsuarioFilter filter;
	
	private static final long serialVersionUID = 1L;

	public UsuarioController() {
		super(Usuario.class);
	}
	
	/**
	 * @return the filter
	 */
	public UsuarioFilter getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(UsuarioFilter filter) {
		this.filter = filter;
	}

	public String listagem() {
		return Navegacao.Usuario.LISTAGEM;
	}
	
	public String entrada() {
		return Navegacao.Usuario.ENTRADA;
	}
	
	public String adicionar() {
		
		FacesUtil.getInstance().getFlashScope().put(flashEntityKey(), new Usuario());
		FacesUtil.getInstance().getFlashScope().put(ACTION, ACTION_ADD);
		
		return entrada();
	}
	
	public String editar() {
		
		FacesUtil.getInstance().getFlashScope().put(flashEntityKey(), getBean());
		FacesUtil.getInstance().getFlashScope().put(ACTION, ACTION_EDIT);
		
		return entrada();
	}
	
	public String deletar() {
		
		try {
			
			usuarioBusiness.delete(getBean());
			
			FacesUtil.getInstance().showInfo("registro.deletado");

			list = usuarioBusiness.list(filter);
			
		} catch (BusinessException businessException) {
		
			FacesUtil.getInstance().showBusinessError(businessException.getMessage());
			
		}
		
		return nav(listagem());
	}
	
	public String pesquisar() {
		list = usuarioBusiness.list(getFilter());
		return nav(listagem());
	}
	
	public String salvar() {
		
		try {
			
			if (ACTION_ADD.equals(getAction())) {

				usuarioBusiness.insert(getBean());
				
				FacesUtil.getInstance().showInfo("registro.inserido");
				
			} else {
				
				usuarioBusiness.update(getBean());
				
				FacesUtil.getInstance().showInfo("registro.atualizado");
				
			}
			
		} catch (BusinessException businessException) {
			FacesUtil.getInstance().showBusinessError(businessException.getMessage());
			return nav(entrada());
		}
		
		return nav(listagem());
	}
}
