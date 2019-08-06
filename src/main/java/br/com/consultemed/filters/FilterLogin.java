package br.com.consultemed.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class FilterLoggin
 */
//@WebFilter(filterName = "/filterLoggin", urlPatterns = {"/*"})
public class FilterLogin implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//RECUPERA A SESSAO
		//INVALIDA A SESSAO
		//VERIFICA SE O USUÁRIO QUE ESTÁ ACESSO O LOGIN, EXISTE E TEM AS CREDENCIAIS DE ACESSO AO SISTEMA
			//SE EXISTE E TEM ACESSO, COLOCA O USUÁRIO NA SESSÃO E DIRECIONA PARA A HOME DO SISTEMA
			//SE NÃO EXISTE, REDIRECIONA PARA A TELA DE LOGIN COM A MENSAGEM DE USUÁRIO INVÁLIDO, SENHA E OU LOGIN INVÁLIDOS 
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {
		
	}
}
