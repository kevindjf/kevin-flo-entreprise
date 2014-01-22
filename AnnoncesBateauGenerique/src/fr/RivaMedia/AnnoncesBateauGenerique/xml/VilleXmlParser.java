package fr.RivaMedia.AnnoncesBateauGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Ville;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.core.XmlParser;

public class VilleXmlParser extends XmlParser {

	public VilleXmlParser(String xml) {
		super(xml);	
	}

	public VilleXmlParser(XmlPullParser xpp) {
		super(xpp);
	}
	


	public List<Ville> getVilles() {
		List<Ville> villes = new ArrayList<Ville>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				villes.add(getVille());
			}
			eventType = XMLgetSuivant();
		}
		
		Log.e("XML",villes.size()+" villes chargees");

		return villes;
	}
	
	public Ville getVille() {
		
		Ville ville = new Ville();
		
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("id"))
					ville.setId(getString());
				if(tag.equals("nom"))
					ville.setNom(getString());
				if(tag.equals("cp"))
					ville.setCodePostal(getString());
			}
			eventType = XMLgetSuivant();
		}
		
		return ville;
	}

}
