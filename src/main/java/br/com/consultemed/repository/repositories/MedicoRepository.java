/**
 * 
 */
package br.com.consultemed.repository.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import br.com.consultemed.models.Medico;
import br.com.consultemed.utils.JPAUtils;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class MedicoRepository {

	EntityManagerFactory em = JPAUtils.getEntityManagerFactory();
	EntityManager factory = em.createEntityManager();

	public List<Medico> listarMedicos() throws Exception {
		this.factory = em.createEntityManager();
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
		this.factory = em.createEntityManager();
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
		this.factory = em.createEntityManager();
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

	public Medico getMedicoByCrm(String crm) {
		this.factory = em.createEntityManager();
		Query query = factory.createQuery("SELECT M FROM Medico M Where M.crm = :crm");
		query.setParameter("crm", crm);
		return (Medico) query.getSingleResult();
	}

}
