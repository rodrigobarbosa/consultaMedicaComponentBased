package br.com.consultemed.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import br.com.consultemed.models.AgendamentoConsulta;
import br.com.consultemed.models.Medico;
import br.com.consultemed.models.Paciente;
import br.com.consultemed.repository.repositories.AgendamentoConsultaRepository;

public class AgendamentoConsultaService {
	
	@Inject
	private AgendamentoConsultaRepository dao;
	
	@Inject
	private PacienteService pacienteService;

	@Inject
	private MedicoService medicoService;
	
	public List<AgendamentoConsulta> listaAgendamentoConsulta(){
		return this.dao.listaAgendamentosConsultas();
	}
	
	public void salvarConsulta(AgendamentoConsulta consulta) {
		
		Paciente p = pacienteService.getPacienteByEmail(consulta.getPacientes().getEmail());
		Medico m = medicoService.getMedicoByCrm(consulta.getMedicos().getCrm());
		
		if(!Objects.isNull(p) || !Objects.isNull(m)) {
			ZoneId defaultZoneId = ZoneId.systemDefault();
			Instant instant = consulta.getDataAgendamentoConsulta().toInstant();
			LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
			consulta.setMedicos(m);
			consulta.setPacientes(p);
			
			dao.salvarAgendamentoConsulta(consulta);
		}
		
	}
	
	public void salvarAgendamentoConsulta(AgendamentoConsulta agendamentoConsulta) {
		this.dao.salvarAgendamentoConsulta(agendamentoConsulta);
	}
	
	public void deletarAgendamentoConsulta(Long id) throws Exception {
		this.dao.deletarAgendamentoConsultaById(id);
	}

}
