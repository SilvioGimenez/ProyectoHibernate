package com.mycompany.managedbean;


import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import com.mycompany.entities.Alumno;
import com.mycompany.entities.Carrera;
import com.mycompany.entities.Matricula;
import com.mycompany.repository.AlumnoRepository;
import com.mycompany.repository.CarreraRepository;
import com.mycompany.repository.MatriculaRepository;



@ManagedBean
public class MatriculaBean {

	@ManagedProperty(value = "#{entityManager}")
	private EntityManager entityManager;

	private Matricula matri = new Matricula();
	private Carrera carrera = new Carrera();
	private Alumno alumno = new Alumno();
	private List<Matricula> matris;
	private List<Carrera> carreras;
	private List<Alumno> alumnos;
	private Long menor;
	private Long mayor;
	private Long menoralumno;
	private Long mayoralumno;
	private Long menorcarrera;
	private Long mayorcarrera;
	private int nrosel;
	private String genero;
	private String operador;
	
	public void guardar() {
		CarreraRepository carrerarepository = new CarreraRepository(this.entityManager);
		
		String [] opciones = {"Ing. en Informática","Ing. en Marketing","Ing. en Sistemas de Producción","Lic. en Análisis de Sistemas", "Ing en Energia", "Ing en Electrónica", "Ing. en Electricidad", "Ing. en Ciencias de los Materiales", "Ing. en Aeronáutica", "Lic. en Ciencias Atmosféricas"};
		for(int y=0;y<=9;y++){
			String car = opciones[y];
			carrera.setDescripcion(car);
			carrerarepository.save(this.carrera);
			this.carrera = new Carrera();
		}
		
		for(int x=1;x<=this.nrosel;x++){
			AlumnoRepository alumnoRepository = new AlumnoRepository(this.entityManager);
			String [] nombres = {"Juan","David","Maria","Mariela","Mariza","Alberto","Aldo","Julio","Adela","Sebastian","Carolina","Romina","Lucia","Antonio","Pedro","Laura","Alicia","Pablo","Vicente","Carmen","Leticia","Karen","Rosana","Fernando","Ariel","Osvaldo"};
			int nro = (int)(Math.random()*(25-0+1)+0);
			String [] apellidos1 = {"Gonzalez","Benitez","Martinez","Lopez","Gimenez","Vera","Duarte","Villalba","Ramirez","Fernandez","Gomez","Acosta","Rojas","Ortiz","Cáceres"};
			int nro1 = (int)(Math.random()*(14-0+1)+0);
			String [] apellidos2 = {"Rodriguez","Nuñez","Ayala","Baez","Galeano","Ferreira","Cabrera","Romero","Franco","Sosa","Espinola","Britez","Cardozo","Torres","Caballero"};
			int nro2 = (int)(Math.random()*(14-0+1)+0);
			String nomatrapado = nombres[nro];
			if((nomatrapado=="Maria")||(nomatrapado=="Mariela")||(nomatrapado=="Mariza")
					||(nomatrapado=="Adela")||(nomatrapado=="Carolina")||(nomatrapado=="Romina")
					||(nomatrapado=="Lucia")||(nomatrapado=="Laura")||(nomatrapado=="Alicia")
					||(nomatrapado=="Carmen")||(nomatrapado=="Leticia")||(nomatrapado=="Karen")
					||(nomatrapado=="Rosana")){
				 genero = "Femenino";
			}else{
				 genero = "Masculino";
			}
			long mayordeedad = (long)((Math.random()*(30-18+1)+18)*365*86400000);
			Date fechaatrapada = (new Date(System.currentTimeMillis()- mayordeedad));
			String [] operadora = {"0971-","0972-","0973-","0974-","0975-","0981-","0982-","0983-","0984-","0985-","0991-","0992-","0993-","0994-"};
			int nro3 = (int)(Math.random()*(13-0+1)+0);
			String [] telefono = {"123456","654321","456123","615243","361452","162534","146324","132465","634125","614253","531426","352416"};
			int nro4 = (int)(Math.random()*(11-0+1)+0);
			
			this.alumno = new Alumno();
			alumno.setNombres(nombres[nro]);
			alumno.setApellidos(apellidos1[nro1]+" "+apellidos2[nro2]);
			alumno.setFechanacimiento(fechaatrapada);
			alumno.setGenero(genero);
			alumno.setTelefono(operadora[nro3]+telefono[nro4]);
			
			
			CarreraRepository carrerarepo = new CarreraRepository(this.entityManager);
			this.carreras = carrerarepo.getCarreras();
			for(int i=0;i < carreras.size();i++){
				if(i==0){
					operador = String.valueOf(carreras.get(i));
					menorcarrera = Long.valueOf(operador);
					mayorcarrera = Long.valueOf(operador);
				}else{
					String nrouno = String.valueOf(carreras.get(i));
					long nrodos = Long.valueOf(nrouno);
					if(nrodos<menorcarrera){
						menorcarrera = nrodos;
						
					}
					if(nrodos>mayorcarrera){
						mayorcarrera = nrodos;
					}
					
				}
				
				
			}
			long nrocarrera = (long)(Math.random()*(mayorcarrera-menorcarrera+1)+menorcarrera);
			CarreraRepository carreraRepository = new CarreraRepository(this.entityManager);
			long y = nrocarrera;
			carrera = carreraRepository.search(y);
				
			int monto = (int)(Math.random()*(700000-500000+1)+500000);
			
			Date fechamatricula = new Date();
			
			MatriculaRepository matriculaRepository = new MatriculaRepository(this.entityManager);
			this.matri = new Matricula();
			matri.setAlumno(this.alumno);
			matri.setCarrera(carrera);
			matri.setMonto(monto);
			matri.setFechamatricula(fechamatricula);
			alumnoRepository.save(this.alumno); 
			matriculaRepository.save(this.matri);
			
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(nrosel+" Registros Generados Correctamente!!"));
		
		
	}
	
	public void eliminar(){
		MatriculaRepository repository = new MatriculaRepository(this.entityManager);
		this.matris = repository.getMatriculas();
		
		if(matris.isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existe nada que eliminar!!"));
		}else{	

			for(int i=0; i< matris.size(); i++) {
				
				if(i==0){
					operador = String.valueOf(matris.get(i));
					menor = Long.valueOf(operador);
					mayor = Long.valueOf(operador);
					
				}else{
					String nro = String.valueOf(matris.get(i));
					long nro2 = Long.valueOf(nro);
					if(nro2<menor){
						menor = nro2;
						
					}
					if(nro2>mayor){
						mayor = nro2;
						
					}
					
				}
				
				
			}
					
			for(long x=menor; x<=mayor;x++){
				
				MatriculaRepository reposito = new MatriculaRepository(this.entityManager);
				Matricula matri;
				long y = x;
				AlumnoRepository alumnoRepository = new AlumnoRepository(this.entityManager);
				Alumno alu;
				matri = reposito.search(y);
				alu = alumnoRepository.search(y);
				reposito.eliminar(matri);
				alumnoRepository.eliminar(alu);
				
				this.matris = null;	
				
			}
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registros Eliminados Correctamente!! "));
			

			}
		CarreraRepository carrerarepository = new CarreraRepository(this.entityManager);
		this.carreras = carrerarepository.getCarreras();
		for(int i=0;i < carreras.size();i++){
			if(i==0){
				operador = String.valueOf(carreras.get(i));
				menorcarrera = Long.valueOf(operador);
				mayorcarrera = Long.valueOf(operador);
			}else{
				String nro = String.valueOf(carreras.get(i));
				long nro2 = Long.valueOf(nro);
				if(nro2<menorcarrera){
					menorcarrera = nro2;
					
				}
				if(nro2>mayorcarrera){
					mayorcarrera = nro2;
				}
				
			}
			
			
		}
		for(long x=menorcarrera; x<=mayorcarrera;x++){
			
			CarreraRepository carreraRepository = new CarreraRepository(this.entityManager);
			Carrera car;
			long y = x;
				car = carreraRepository.search(y); 
				carreraRepository.eliminar(car); 
				this.carreras = null;
			
		}
	}
	
	public void actualizar(){
		AlumnoRepository repository = new AlumnoRepository(this.entityManager);
		this.alumnos = repository.getAlumnos();
		System.out.println(alumnos.get(1));
		
		if(alumnos.isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existe nada que actualizar!!"));
		}else{	
			for(int i=0; i< alumnos.size(); i++) {
				
				if(i==0){
					String atrapado = String.valueOf(alumnos.get(i));
					System.out.println(atrapado);
					menoralumno = Long.valueOf(atrapado);
					mayoralumno = Long.valueOf(atrapado);
					
				}else{
					String nro = String.valueOf(alumnos.get(i));
					long nro2 = Long.valueOf(nro);
					if(nro2<menoralumno){
						menoralumno = nro2;
						
					}
					if(nro2>mayoralumno){
						mayoralumno = nro2;
						
					}
					
				}
			}
				
			for(long x=menoralumno; x<=mayoralumno;x++){
				
				long y = x;
				AlumnoRepository alumnoRepository = new AlumnoRepository(this.entityManager);
				Alumno alu;
				alu = alumnoRepository.search(y);
				String nombre = alu.getNombres();
				String apellido = alu.getApellidos();
				String genero = alu.getGenero();
				String telefono = alu.getTelefono();
				this.alumno = new Alumno();
				alu.setNombres(nombre+" Actualizado");
				alu.setApellidos(apellido+" Actualizado");
				alu.setGenero(genero+" Actualizado");
				alu.setTelefono(telefono+" Actualizado");
				alumnoRepository.actualizar(alu);	
			}
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registros Actualizados Correctamente!! "));
		}
		
	}
	
	public List<Alumno> getAlumnos() {
		if(this.alumnos == null){
			AlumnoRepository repository = new AlumnoRepository(this.entityManager);
			this.alumnos = repository.getAlumnos();
		}
		return this.alumnos;
	}
	public List<Carrera> getCarreras() {
		if (this.carreras == null) {
			CarreraRepository repository = new CarreraRepository(this.entityManager);
			this.carreras = repository.getCarreras();
			
		}
		return this.carreras;
	}

	public List<Matricula> getMatris() {
		if (this.matris == null) {
			MatriculaRepository repository = new MatriculaRepository(this.entityManager);
			this.matris = repository.getMatriculas();
			
		}
		return this.matris;
	}

	public int getNrosel() {
		return nrosel;
	}
	
	public void setNrosel(int nrosel) {
		this.nrosel = nrosel;
	}

	public Long getCount() {
		MatriculaRepository repository = new MatriculaRepository(this.entityManager);
		return repository.getCountMatriculas();
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unused")
	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute(" EntityManager ");
	}
}
