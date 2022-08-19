package clase.datos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "required_profiles")
public class ProfilesList {
	 
	 private ArrayList <Profile> profilesList;
	 
	 public ProfilesList() {
		 this.profilesList = new ArrayList<Profile>();
	 }
	 
	 public void setProfilesList(ArrayList<Profile> profilesList) {
		 this.profilesList = profilesList;
	 }
	 
	 @XmlElementWrapper(name = "profiles")
	 @XmlElement(name = "user")
	 public ArrayList<Profile> getProfilesList() {
		 return profilesList;
	 }
}
