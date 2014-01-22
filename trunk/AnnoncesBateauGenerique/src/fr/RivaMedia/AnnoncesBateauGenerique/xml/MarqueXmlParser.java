package fr.RivaMedia.AnnoncesBateauGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Marque;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.core.XmlParser;

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
					Marque marque = new Marque();
					marque.setId(getXpp().getAttributeValue(null, "id"));
					marque.setLibelle(getString());
					marques.add(marque);
				}
			}
			eventType = XMLgetSuivant();
		}
		Log.e("XML",marques.size()+" marques chargees");

		return marques;
	}

}
