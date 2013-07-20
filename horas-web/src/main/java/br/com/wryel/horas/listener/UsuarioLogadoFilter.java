package br.com.wryel.horas.listener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.wryel.horas.AppContext;
import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.util.SessionUtil;

@WebFilter(urlPatterns = {"*." + AppContext.JSF_EXTENSION})
public class UsuarioLogadoFilter implements Filter {
	
	private static final String LOGIN_PAGE = "login." + AppContext.JSF_EXTENSION;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		Usuario usuario = SessionUtil.getInstance().getUsuarioLogado();
		if (usuario == null) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
			HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/" + LOGIN_PAGE);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
