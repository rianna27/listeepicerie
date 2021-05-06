package application;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="epiceries")
public class EpicerieListeWrapper {
	
	
		private List<Liste1> epiceries;
		@XmlElement(name="epiceries")
		public List<Liste1> getliste()
		{
			return epiceries;
		}
		public void setliste(List<Liste1> epiceries)
		{
			this.epiceries=epiceries;
		}
		
		

	}


