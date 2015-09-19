package com.mycompany.repository;
import java.util.List;

import javax.persistence.EntityManager;

import com.mycompany.entities.Matricula;


public class MatriculaRepository {
	private EntityManager entityManager;
	
	public MatriculaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void save(Matricula matri) {
		this.entityManager.persist(matri);
		this.entityManager.flush();
	}
	
	public void eliminar(Matricula matri) {
		this.entityManager.remove(matri);
	}
	
	public void actualizar(Matricula matri){
		this.entityManager.merge(matri);
	
	}
	
	public Matricula search(Long ID){
		return this.entityManager.find(Matricula.class, ID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Matricula> getMatriculas() {
		return this.entityManager.createNamedQuery("Matricula.findAll")
				.getResultList();
	}

	public Long getCountMatriculas() {
		return (Long) this.entityManager.createNamedQuery("Matricula.count")
				.getSingleResult();
	}

}
