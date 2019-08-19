package br.com.consultemed.repository.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.consultemed.models.Paciente;
import br.com.consultemed.utils.JPAUtils;

public class PacienteRepository {

	EntityManagerFactory em = JPAUtils.getEntityManagerFactory();
	EntityManager factory = em.createEntityManager();

	public List<Paciente> listaPacientes() {
		Query query = this.factory.createQuery("SELECT object(p) FROM Paciente as p");
		return query.getResultList();
	}

	public Collection<Paciente> listarPacientes() throws Exception {
		this.factory = em.createEntityManager();
		List<Paciente> pacientes = new ArrayList<Paciente>();
		try {
			factory.getTransaction().begin();
			TypedQuery<Paciente> query = factory.createNamedQuery("Paciente.findAll", Paciente.class);
			pacientes = query.getResultList();
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return pacientes;
	}

	public void salvarPaciente(Paciente paciente) {
		this.factory = em.createEntityManager();
		try {
			factory.getTransaction().begin();
			if (paciente.getId() == null) {
				factory.persist(paciente);
			} else {
				factory.merge(paciente);
			}
			factory.getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();

		} finally {
			factory.close();
		}
	}

	public void deleteById(Long id) throws Exception {
		this.factory = em.createEntityManager();
		Paciente paciente = new Paciente();

		try {

			paciente = factory.find(Paciente.class, id);
			factory.getTransaction().begin();
			factory.remove(paciente);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

	}

	public Paciente getPacienteByEmail(String email) {
		this.factory = em.createEntityManager();
		Query query = factory.createQuery("SELECT P FROM Paciente P Where P.email = :email");
		query.setParameter("email", email);
		return (Paciente) query.getSingleResult();

	}
}
