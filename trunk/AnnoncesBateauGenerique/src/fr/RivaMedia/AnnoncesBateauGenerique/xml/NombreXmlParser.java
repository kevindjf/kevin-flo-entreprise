package fr.RivaMedia.AnnoncesBateauGenerique.xml;

import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import fr.RivaMedia.AnnoncesBateauGenerique.xml.core.XmlParser;

public class NombreXmlParser extends XmlParser {

	public NombreXmlParser(String xml) {
		super(xml);	
	}

	public NombreXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public Map<String,Integer> getNombres() {
		Map<String,Integer> typeNombres = new HashMap<String,Integer>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(!tag.equals("nbann")){
					String nombre = getString();
					typeNombres.put(tag, Integer.parseInt(nombre));
				}
			}
			eventType = XMLgetSuivant();
		}

		return typeNombres;
	}
	
	public String getNombre() {
		String nombre = "";
		int eventType = XMLgetEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				nombre = getString();
			}
			eventType = XMLgetSuivant();
		}
		return nombre;
	}

}
