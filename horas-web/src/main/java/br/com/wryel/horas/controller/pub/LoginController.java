package br.com.wryel.horas.controller.pub;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.wryel.horas.business.UsuarioBusiness;
import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.util.FacesUtil;
import br.com.wryel.horas.util.SessionUtil;

@ViewScoped
@ManagedBean
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String LOGIN = "/login";
	
	public static final String INDEX = "/index";
	
	@EJB
	private UsuarioBusiness usuarioBusiness;
	
	private Usuario usuario = new Usuario();
	
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String login() {
		Usuario usuarioLogado = usuarioBusiness.login(usuario.getEmail(), usuario.getSenha());
		if (usuarioLogado != null) {
			SessionUtil.getInstance().setUsuarioLogado(usuarioLogado);
			return INDEX;
		}
		FacesUtil.getInstance().showError("usuario.invalido");
		return LOGIN;
	}
	
	public String logout() {
		SessionUtil.getInstance().destruirSessaoAtual();
		return LOGIN;
	}
}
