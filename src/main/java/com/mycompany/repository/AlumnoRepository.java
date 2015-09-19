package com.mycompany.repository;
import java.util.List;

import javax.persistence.EntityManager;

import com.mycompany.entities.Alumno;


public class AlumnoRepository {
	private EntityManager entityManager;
	
	public AlumnoRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void save(Alumno alu) {
		this.entityManager.persist(alu);
		this.entityManager.flush();
	}
	
	public void eliminar(Alumno alu) {
		this.entityManager.remove(alu);
	}
	
	public void actualizar(Alumno alu){
		this.entityManager.merge(alu);
	
		
	}
	
	public Alumno search(Long ID){
		return this.entityManager.find(Alumno.class, ID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Alumno> getAlumnos() {
		return this.entityManager.createNamedQuery("Alumno.findAll")
				.getResultList();
	}

	
}
