package com.mycompany.utils;


import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.mycompany.entities.Alumno;
import com.mycompany.entities.Carrera;
import com.mycompany.entities.Matricula;


public class GenerateTables {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("controlalumnos");

		EntityManager manager = factory.createEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		Alumno alu = new Alumno();
		alu.setNombres("Eduardo");
		alu.setApellidos("Espinola Rodriguez");
		Date fecha = new Date();
		alu.setFechanacimiento(fecha);  
		alu.setGenero("Masculino");
		alu.setTelefono("0983-130870");
		
		Carrera mat = new Carrera();
		mat.setDescripcion("Analisis de Sistemas");
		
		Matricula matri = new Matricula();
		matri.setAlumno(alu);
		matri.setCarrera(mat);
		matri.setFechamatricula(fecha);
		matri.setMonto(150000);
		manager.persist(alu);
		manager.persist(mat);
		manager.persist(matri);
		
		
		trx.commit();
	}

		
	}

