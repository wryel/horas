package br.com.wryel.horas.util;

import javax.servlet.http.HttpSession;

import br.com.wryel.horas.AppContext;
import br.com.wryel.horas.entity.Usuario;

public final class SessionUtil {
	
	private static final SessionUtil INSTANCE = new SessionUtil();
	
	public static SessionUtil getInstance() {
		return INSTANCE;
	}
	
	public Usuario getUsuarioLogado() {
		Usuario usuario = (Usuario) FacesUtil.getInstance().getSession().getAttribute(AppContext.Session.USUARIO);
		return usuario;
	}
	
	public void setUsuarioLogado(Usuario usuario) {
		HttpSession httpSession = FacesUtil.getInstance().getSession();
		httpSession.setAttribute(AppContext.Session.USUARIO, usuario);
	}
	
	public void destruirSessaoAtual() {
		FacesUtil.getInstance().getSession().invalidate();
	}
	
	private SessionUtil() {
		
	}
}
