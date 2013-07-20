package br.com.wryel.horas.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.wryel.horas.business.UsuarioBusiness;
import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.util.FacesUtil;

@ViewScoped
@ManagedBean
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioBusiness usuarioBusiness;
	
	private Usuario usuario = new Usuario();
	
	public String login() {
		Usuario usuarioLogado = usuarioBusiness.login(usuario.getEmail(), usuario.getSenha());
		return "";
	}
	
	public String logout() {
		return "";
	}
}
