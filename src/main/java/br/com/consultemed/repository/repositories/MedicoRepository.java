/**
 * 
 */
package br.com.consultemed.repository.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.consultemed.models.Medico;
import br.com.consultemed.utils.JPAUtils;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class MedicoRepository {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();


	public List<Medico> listarMedicos() throws Exception {
		this.factory = emf.createEntityManager();
		List<Medico> medicos = new ArrayList<Medico>();
		try {
			factory.getTransaction().begin();
			Query query = this.factory.createQuery("SELECT object(m) FROM Medico as m");
			factory.getTransaction().commit();
			return query.getResultList();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return medicos;
	}

	public void salvarMedico(Medico medico) {
		this.factory = emf.createEntityManager();
		try {
			factory.getTransaction().begin();
			if (medico.getId() == null) {
				factory.persist(medico);
			} else {
				factory.merge(medico);
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
		Medico medico = new Medico();

		try {

			medico = factory.find(Medico.class, id);
			factory.getTransaction().begin();
			factory.remove(medico);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

	}

	public boolean getMedicoByCrm(String crm) {
		try {

			String jpql = "SELECT object(m) FROM Medico as m WHERE m.crm = :crm";
			Query query = this.factory.createQuery(jpql);
			query.setParameter("crm", crm);

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
