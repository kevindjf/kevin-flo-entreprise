package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.Lieu;
import fr.RivaMedia.xml.core.XmlParser;

public class LieuxXmlParser extends XmlParser {

	public LieuxXmlParser(String xml) {
		super(xml);	
	}

	public LieuxXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Lieu> getListe() {
		List<Lieu> lieux = new ArrayList<Lieu>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("lieu")){
					Lieu lieu = new Lieu();
					lieu.setId(getXpp().getAttributeValue(null, "id"));
					lieu.setNom(getString());
					lieux.add(lieu);
				}
			}
			eventType = XMLgetSuivant();
		}
		
		Log.e("XML",lieux.size()+" lieux chargees");

		return lieux;
	}

}
