package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.Etat;
import fr.RivaMedia.model.Region;
import fr.RivaMedia.xml.core.XmlParser;

public class EtatXmlParser extends XmlParser {

	public EtatXmlParser(String xml) {
		super(xml);	
	}

	public EtatXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Etat> getEtats() {
		List<Etat> etats = new ArrayList<Etat>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("etat")){
					Etat etat = new Etat();
					etat.setId(getXpp().getAttributeValue(null, "id"));
					etat.setNom(getString());
					etats.add(etat);
				}
			}
			eventType = XMLgetSuivant();
		}
		
		Log.e("XML",etats.size()+" etats chargees");

		return etats;
	}

}
