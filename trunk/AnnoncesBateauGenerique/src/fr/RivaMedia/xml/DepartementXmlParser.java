package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.Departement;
import fr.RivaMedia.xml.core.XmlParser;

public class DepartementXmlParser extends XmlParser {

	public DepartementXmlParser(String xml) {
		super(xml);	
	}

	public DepartementXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Departement> getDepartements() {
		List<Departement> departements = new ArrayList<Departement>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("departement")){
					Departement departement = new Departement();
					departement.setId(getXpp().getAttributeValue(null, "id"));
					departement.setNom(getString());
					departements.add(departement);
				}
			}
			eventType = XMLgetSuivant();
		}
		
		Log.e("XML",departements.size()+" departements chargees");

		return departements;
	}

}
