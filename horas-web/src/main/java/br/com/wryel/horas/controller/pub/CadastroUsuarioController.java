package br.com.wryel.horas.controller.pub;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.wryel.horas.business.BusinessException;
import br.com.wryel.horas.business.UsuarioBusiness;
import br.com.wryel.horas.controller.AbstractController;
import br.com.wryel.horas.controller.Navegacao;
import br.com.wryel.horas.entity.CadastroUsuario;
import br.com.wryel.horas.util.FacesUtil;

@ViewScoped
@ManagedBean
public class CadastroUsuarioController extends AbstractController<CadastroUsuario> implements Serializable {

	public CadastroUsuarioController() {
		super(CadastroUsuario.class);
	}
	
	@PostConstruct
	@Override
	public void postConstruct() {
		super.postConstruct();
		setBean(new CadastroUsuario());
	}

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsuarioBusiness usuarioBusiness;
	
	public String cadastro() {
		return Navegacao.Usuario.CADASTRO_PUBLICO;
	}
	
	public String cadastrar() {
		
		try {
			
			usuarioBusiness.cadastrar(getBean());
			
			FacesUtil.getInstance().showInfo("cadastroUsuario.sucesso");
			
		} catch (BusinessException businessException) {

			FacesUtil.getInstance().showBusinessError(businessException.getMessage());
			
			return nav(cadastro());
		}
		
		return nav(Navegacao.LOGIN);
	}

}
