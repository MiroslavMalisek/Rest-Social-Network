package clase.datos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "required_lecturas")
public class LecturasUsuarioList {
		 
	 private ArrayList <LecturaUsuario> LecturasList;
	 
	 public LecturasUsuarioList() {
		 this.LecturasList = new ArrayList<LecturaUsuario>();
	 }
	 
	 public void setLecturasList(ArrayList<LecturaUsuario> LecturasList) {
		 this.LecturasList = LecturasList;
	 }
	 
	 @XmlElementWrapper(name = "lecturas")
	 @XmlElement(name = "user")
	 public ArrayList<LecturaUsuario> getLecturasList() {
		 return LecturasList;
	 }
}
