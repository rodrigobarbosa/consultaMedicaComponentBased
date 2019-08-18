package br.com.consultemed.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQueries({ @NamedQuery(name = "AgendamentoConsulta.findAll", query = "SELECT a FROM AgendamentoConsulta a")})
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "TB_AGENDAMENTO_CONSULTA")
public class AgendamentoConsulta {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Getter
	@Setter
	@Column(name = "MEDICOS")
	public Medico medicos;
	
	@Getter
	@Setter
	@Column(name = "PACIENTES")
	public Paciente pacientes;
	
	@Getter
	@Setter
	@Column(name = "DATAAGENDAMENTOCONSULTA")
	public Date dataAgendamentoConsulta;
}
