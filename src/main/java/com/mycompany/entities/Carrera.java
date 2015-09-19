package com.mycompany.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "carrera")
@NamedQueries({
	@NamedQuery(name = "Carrera.findAll", query = "SELECT d FROM Carrera d"),
	@NamedQuery(name = "Carrera.count", query = "SELECT COUNT(d) FROM Carrera d")
})
public class Carrera implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "descripcion", length = 80,nullable = false)
	private String descripcion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString() {
        return ""+this.getId()+"";
    }
	
	
}
