package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.Modele;
import fr.RivaMedia.xml.core.XmlParser;

public class ModeleXmlParser extends XmlParser {

	public ModeleXmlParser(String xml) {
		super(xml);	
	}

	public ModeleXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Modele> getModeles() {
		List<Modele> modeles = new ArrayList<Modele>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("modele")){
					modeles.add(getModele());
				}
			}
			eventType = XMLgetSuivant();
		}
		Log.e("XML",modeles.size()+" modeles chargees");

		return modeles;
	}
	
	public Modele getModele() {
		Modele modele = new Modele();
		int eventType = XMLgetSuivant(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("id"))
					modele.setId(getString());
				else if(tag.equals("nom"))
					modele.setNom(getString());
			}
			eventType = XMLgetSuivant();
		}

		return modele;
	}

}
