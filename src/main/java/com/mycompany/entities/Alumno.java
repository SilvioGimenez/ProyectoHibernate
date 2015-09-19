package com.mycompany.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "alumno")
@NamedQueries({
	@NamedQuery(name = "Alumno.findAll", query = "SELECT d FROM Alumno d"),
	@NamedQuery(name = "Alumno.count", query = "SELECT COUNT(d) FROM Alumno d")
})
public class Alumno implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alumno_seq")
	@SequenceGenerator(name = "alumno_seq", sequenceName = "alumno_seq", allocationSize=1)
	@Column(name = "id")
	private Long id;
	@Column(name = "nombres", length = 150, nullable = false )
	private String nombres;
	
	@Column(name = "apellidos", length = 150, nullable = false)
	private String apellidos;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_nac", nullable = false)
	private Date fechanacimiento;
	
	@Column(name = "genero", length = 50, nullable = false)
	private String genero;
	
	@Column(name = "telefono", length = 50, nullable = false)
	private String telefono;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String toString() {
        return ""+this.getId()+"";
    }
	
}
