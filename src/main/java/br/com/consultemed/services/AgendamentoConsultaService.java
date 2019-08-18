package br.com.consultemed.services;

import java.util.List;

import javax.inject.Inject;

import br.com.consultemed.models.AgendamentoConsulta;
import br.com.consultemed.repository.repositories.AgendamentoConsultaRepository;

public class AgendamentoConsultaService {
	
	@Inject
	private AgendamentoConsultaRepository dao;
	
	public List<AgendamentoConsulta> listaAgendamentoConsulta(){
		return this.dao.listaAgendamentosConsultas();
	}
	
	public void salvarAgendamentoConsulta(AgendamentoConsulta agendamentoConsulta) {
		this.dao.salvarAgendamentoConsulta(agendamentoConsulta);
	}
	
	public void deletarAgendamentoConsulta(Long id) throws Exception {
		this.dao.deleteById(id);
	}
	
	public boolean getAgendamentoConsultaById(Long id) {
		if(this.dao.getAgendamentoConsultaById(id)) {
			return true;
		}else {
			return false;
		}
	}
}
