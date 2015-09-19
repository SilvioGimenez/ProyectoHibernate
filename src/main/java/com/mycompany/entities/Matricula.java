package com.mycompany.entities;
import java.io.Serializable;
import java.util.Date;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "matricula")
@NamedQueries({
	@NamedQuery(name = "Matricula.findAll", query = "SELECT d FROM Matricula d"),
	@NamedQuery(name = "Matricula.count", query = "SELECT COUNT(d) FROM Matricula d")
})
public class Matricula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "id_alumno")
	private Alumno alumno;
	
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	@OneToOne
	@JoinColumn(name = "id_carrera")
	private Carrera carrera;
	
	
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_matricula", nullable = false)
	private Date fechamatricula;
	
	@Column(name = "monto", length = 4, nullable = false)
	private int monto;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechamatricula() {
		return fechamatricula;
	}
	public void setFechamatricula(Date fechamatricula) {
		this.fechamatricula = fechamatricula;
	}
	public int getMonto() {
		return monto;
	}
	public void setMonto(int periodolectivo) {
		this.monto = periodolectivo;
	}
	public String toString() {
        return ""+this.getId()+"";
    }
	
}
