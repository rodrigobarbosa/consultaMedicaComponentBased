package br.com.consultemed.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.consultemed.models.Usuario;

/**
 * Servlet Filter implementation class FilterLoggin
 */
//@WebFilter(filterName = "/filterLoggin", urlPatterns = { "/*" })
public class FilterLogin implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String url = req.getServletPath();

		Usuario usuarioLogado = (Usuario)session.getAttribute("usuario");
		
		if(usuarioLogado == null && !url.equalsIgnoreCase("/login.xhtml")) {
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}
