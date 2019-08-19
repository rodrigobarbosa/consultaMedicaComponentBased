package br.com.consultemed.repository.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.consultemed.models.AgendamentoConsulta;
import br.com.consultemed.utils.JPAUtils;

public class AgendamentoConsultaRepository {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();

	public List<AgendamentoConsulta> listaAgendamentosConsultas() {
		Query query = this.factory.createQuery("SELECT object(a) FROM AgendamentoConsulta as a");
		return query.getResultList();
	}

	public Collection<AgendamentoConsulta> listarAgendamentosConsultas() throws Exception {
		this.factory = emf.createEntityManager();
		List<AgendamentoConsulta> agendamentosConsultas = new ArrayList<AgendamentoConsulta>();
		try {
			factory.getTransaction().begin();
			TypedQuery<AgendamentoConsulta> query = factory.createNamedQuery("AgendamentoConsulta.findAll", AgendamentoConsulta.class);
			agendamentosConsultas = query.getResultList();
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return agendamentosConsultas;
	}

	public void salvarAgendamentoConsulta(AgendamentoConsulta agendamentoConsulta) {
		this.factory = emf.createEntityManager();
		try {
			factory.getTransaction().begin();
			if (agendamentoConsulta.getId() == null) {
				factory.persist(agendamentoConsulta);
			} else {
				factory.merge(agendamentoConsulta);
			}
			factory.getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();

		} finally {
			factory.close();
		}
	}

	public void deletarAgendamentoConsultaById(Long id) throws Exception {
		this.factory = emf.createEntityManager();
		AgendamentoConsulta agendamentoConsulta = new AgendamentoConsulta();

		try {

			agendamentoConsulta = factory.find(AgendamentoConsulta.class, id);
			factory.getTransaction().begin();
			factory.remove(agendamentoConsulta);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

	}

	public boolean getAgendamentoConsultaById(Long id) {
		try {

			String jpql = "SELECT object(a) FROM Funcionario as a WHERE f.id = :id";
			Query query = this.factory.createQuery(jpql);
			query.setParameter("id", id);

			if (query.getSingleResult() != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}
}
