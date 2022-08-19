package clase.datos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="lectura")

public class LecturaUsuario {
	private String nameLibro;
	private String autorLibro;
	private String categoriaLibro;
	private float calificacion;
	
	public LecturaUsuario() {
	}

	public String getNameLibro() {
		return nameLibro;
	}
	public void setNameLibro(String nameLibro) {
		this.nameLibro = nameLibro;
	}

	public String getAutorLibro() {
		return autorLibro;
	}
	public void setAutorLibro(String autor) {
		this.autorLibro = autor;
	}
	
	public String getCategoriaLibro(){
		return categoriaLibro;
	}
	public void setCategoriaLibro(String categoria){
		this.categoriaLibro = categoria;
	}

	public float getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}
}
