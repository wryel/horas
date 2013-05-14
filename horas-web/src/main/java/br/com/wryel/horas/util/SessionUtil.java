package br.com.wryel.horas.util;

import br.com.wryel.horas.AppContext;
import br.com.wryel.horas.entity.Usuario;

public class SessionUtil {
	
	private static final SessionUtil INSTANCE = new SessionUtil();
	
	public static SessionUtil getInstance() {
		return INSTANCE;
	}
	
	public Usuario getUsuarioLogado() {
		Usuario usuario = (Usuario) FacesUtil.getInstance().getSession().getAttribute(AppContext.Session.USUARIO);
		return usuario;
	}
	
	public void destruirSessaoAtual() {
		FacesUtil.getInstance().getSession().invalidate();
	}
	
	private SessionUtil() {
		
	}
}
