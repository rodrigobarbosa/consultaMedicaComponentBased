package br.com.consultemed.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.consultemed.models.AgendamentoConsulta;
import br.com.consultemed.services.AgendamentoConsultaService;
import br.com.consultemed.services.MedicoService;
import br.com.consultemed.services.PacienteService;
import lombok.Getter;
import lombok.Setter;

public class AgendamentoConsultaController {
	@Getter
	@Setter
	private List<AgendamentoConsulta> agendamentosConsultas;

	@Inject
	@Getter
	@Setter
	private AgendamentoConsulta agendamentoConsulta;

	@Getter
	@Setter
	private AgendamentoConsulta agendamentoConsultaEditar;

	@Inject
	private AgendamentoConsultaService service;

	public String editar() {
		this.agendamentoConsulta = this.agendamentoConsultaEditar;
		return "/pages/agendamentoConsultas/addAgendamentosConsultas.xhtml";
	}

	public String excluir() throws Exception {
		this.agendamentoConsulta = this.agendamentoConsultaEditar;
		this.service.deletarAgendamentoConsulta(this.agendamentoConsulta.getId());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
		return "/pages/agendamentoConsultas/agendamentosConsultas.xhtml?faces-redirect=true";
	}

	public String novoAgendamentoConsulta() {
		this.agendamentoConsulta = new AgendamentoConsulta();
		return "/pages/agendamentoConsultas/addAgendamentosConsultas.xhtml?faces-redirect=true";
	}

	public String addAgendamentoConsulta() {
		if (this.existeAgendamento(this.agendamentoConsulta.getId())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Agendamento existe!"));
			return null;
		}
		this.service.salvarAgendamentoConsulta(this.agendamentoConsulta);
		return "/pages/agendamentoConsultas/agendamentosConsultas.xhtml?faces-redirect=true";
	}

	public List<AgendamentoConsulta> listaAgendamentosConsultas() {
		this.agendamentosConsultas = this.service.listaAgendamentoConsulta();
		return agendamentosConsultas;
	}

	public boolean existeAgendamento(Long id) {
		return this.service.getAgendamentoConsultaById(id);
	}
}
