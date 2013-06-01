package br.com.wryel.horas.listener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wryel.horas.AppContext;
import br.com.wryel.horas.entity.Usuario;

@WebFilter(urlPatterns = "*.faces")
@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener, Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpSessionListenerImpl.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		LOGGER.debug("Sessão criada");
		httpSessionEvent.getSession().setMaxInactiveInterval(AppContext.Session.TIME_OUT);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		Usuario usuario = (Usuario) httpSessionEvent.getSession().getAttribute(AppContext.Session.USUARIO);
		if (usuario != null) {
			LOGGER.debug("Sessão destruida com usuario logado: "  + usuario);			
		} else {
			LOGGER.debug("Sessão destruida sem usuario logado");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		Usuario usuario = new Usuario();
		usuario.setId(1);
		HttpServletRequest httpServletRequest = (HttpServletRequest) arg0;
		httpServletRequest.getSession().setAttribute(AppContext.Session.USUARIO, usuario);
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
