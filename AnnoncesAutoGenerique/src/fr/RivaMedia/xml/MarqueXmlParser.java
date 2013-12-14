package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.xml.core.XmlParser;

public class MarqueXmlParser extends XmlParser {

	public MarqueXmlParser(String xml) {
		super(xml);	
	}

	public MarqueXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Marque> getMarques() {
		List<Marque> marques = new ArrayList<Marque>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("marque")){
					marques.add(getMarque());
				}
			}
			eventType = XMLgetSuivant();
		}
		Log.e("XML",marques.size()+" marques chargees");

		return marques;
	}

	public Marque getMarque() {
		Marque marque = new Marque();
		int eventType = XMLgetSuivant(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("id"))
					marque.setId(getString());
				else if(tag.equals("nom"))
					marque.setNom(getString());
			}
			eventType = XMLgetSuivant();
		}

		return marque;
	}
	
}
