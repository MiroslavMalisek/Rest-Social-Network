package clase.datos;

public class LecturaAmigo {
	private String nameAmigo;
	private String nameLibro;
	private String autorLibro;
	private String categoriaLibro;
	private float calificacion;
	
	public LecturaAmigo() {
		
	}

	public String getNameAmigo() {
		return nameAmigo;
	}
	public void setNameAmigo(String name) {
		this.nameAmigo = name;
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
