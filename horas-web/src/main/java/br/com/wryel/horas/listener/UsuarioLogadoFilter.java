package br.com.wryel.horas.listener;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.richfaces.resource.ResourceHandlerImpl;

import br.com.wryel.horas.AppContext;
import br.com.wryel.horas.entity.Usuario;

@WebFilter(urlPatterns = {"*." + AppContext.JSF_EXTENSION})
public class UsuarioLogadoFilter implements Filter {
	
	private static final String LOGIN_PAGE = "login." + AppContext.JSF_EXTENSION;
	
	private static final String INDEX_PAGE = "index." + AppContext.JSF_EXTENSION;
	
	private static final String SIGNUP_PAGE = "cadastro." + AppContext.JSF_EXTENSION;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		Usuario usuario = (Usuario) httpServletRequest.getSession().getAttribute(AppContext.Session.USUARIO);
		if (!httpServletRequest.getRequestURI().contains(ResourceHandler.RESOURCE_IDENTIFIER) && 
				!httpServletRequest.getRequestURI().contains(ResourceHandlerImpl.RICHFACES_RESOURCE_IDENTIFIER)) {
			if (usuario == null && httpServletRequest.getRequestURI().endsWith(SIGNUP_PAGE)) {
				
			} else if (usuario == null && !httpServletRequest.getRequestURI().endsWith(LOGIN_PAGE)) {
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/" + LOGIN_PAGE);
			} else if (usuario != null && httpServletRequest.getRequestURI().endsWith(LOGIN_PAGE)) {
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/" + INDEX_PAGE);
			}			
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
