package clase.datos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "required_lecturas")
public class LecturasAmigoList {
	
	private ArrayList <LecturaAmigo> LecturasList;
	 
	 public LecturasAmigoList() {
		 this.LecturasList = new ArrayList<LecturaAmigo>();
	 }
	 
	 public void setLecturasList(ArrayList<LecturaAmigo> LecturasList) {
		 this.LecturasList = LecturasList;
	 }
	 
	 @XmlElementWrapper(name = "lecturas")
	 @XmlElement(name = "user")
	 public ArrayList<LecturaAmigo> getLecturasList() {
		 return LecturasList;
	 }

}
