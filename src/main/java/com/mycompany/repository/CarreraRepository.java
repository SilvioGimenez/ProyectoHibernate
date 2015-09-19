package com.mycompany.repository;
import java.util.List;

import javax.persistence.EntityManager;

import com.mycompany.entities.Carrera;


public class CarreraRepository {
	private EntityManager entityManager;
	
	public CarreraRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void save(Carrera carre) {
		this.entityManager.persist(carre);
		this.entityManager.flush();
	}
	
	public void eliminar(Carrera carre) {
		this.entityManager.remove(carre);
	}
	
	public void actualizar(Carrera carre){
		this.entityManager.merge(carre);
	
		
	}
	
	public Carrera search(Long ID){
		return this.entityManager.find(Carrera.class, ID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Carrera> getCarreras() {
		return this.entityManager.createNamedQuery("Carrera.findAll")
				.getResultList();
	}

}
