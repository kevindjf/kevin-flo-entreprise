package fr.RivaMedia.AnnoncesAutoGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Departement;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.core.XmlParser;

public class DepartementXmlParser extends XmlParser {

	public DepartementXmlParser(String xml) {
		super(xml);	
	}

	public DepartementXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Departement> getDepartements() {
		List<Departement> departments = new ArrayList<Departement>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("departement")){
					departments.add(getDepartement());
				}
			}
			eventType = XMLgetSuivant();
		}
		Log.e("XML",departments.size()+" departements chargees");

		return departments;
	}

	public Departement getDepartement() {
		Departement departement = new Departement();
		int eventType = XMLgetSuivant(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("id"))
					departement.setId(getString());
				else if(tag.equals("nom"))
					departement.setNom(getString());
				else if(tag.equals("num"))
					departement.setNum(getString());
			}
			eventType = XMLgetSuivant();
		}

		return departement;
	}
	
}