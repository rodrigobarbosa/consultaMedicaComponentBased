package br.com.consultemed.repository.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.consultemed.models.Funcionario;
import br.com.consultemed.utils.JPAUtils;

public class FuncionarioRepository {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();

	public List<Funcionario> listaFuncionarios() {
		Query query = this.factory.createQuery("SELECT object(f) FROM Funcionario as f");
		return query.getResultList();
	}

	public Collection<Funcionario> listarFuncionarios() throws Exception {
		this.factory = emf.createEntityManager();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			factory.getTransaction().begin();
			TypedQuery<Funcionario> query = factory.createNamedQuery("Funcionario.findAll", Funcionario.class);
			funcionarios = query.getResultList();
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return funcionarios;
	}

	public void salvarFuncionario(Funcionario funcionario) {
		this.factory = emf.createEntityManager();
		try {
			factory.getTransaction().begin();
			if (funcionario.getId() == null) {
				factory.persist(funcionario);
			} else {
				factory.merge(funcionario);
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
		this.factory = emf.createEntityManager();
		Funcionario funcionario = new Funcionario();

		try {

			funcionario = factory.find(Funcionario.class, id);
			factory.getTransaction().begin();
			factory.remove(funcionario);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

	}

	public boolean getFuncionarioByEmail(String email) {

		String jpql = "SELECT object(f) FROM Funcionario as f WHERE f.email = :email";
		Query query = this.factory.createQuery(jpql);
		query.setParameter("email", email);

		if (query.getSingleResult() != null) {
			return true;
		} else {
			return false;
		}

	}
}
