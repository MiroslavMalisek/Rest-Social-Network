package clase.datos;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="profile")
public class Profile {

//clase que es una replica de tabla "profiles" de la base de datos
	
	private String username;
	private String email;
	private int age;
	
	public Profile() {
	
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String nombre) {
		this.username = nombre;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
