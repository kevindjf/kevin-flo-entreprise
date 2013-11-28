package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.Energie;
import fr.RivaMedia.xml.core.XmlParser;

public class EnergieXmlParser extends XmlParser {

	public EnergieXmlParser(String xml) {
		super(xml);	
	}

	public EnergieXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Energie> getEnergies() {
		List<Energie> energies = new ArrayList<Energie>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("energie")){
					Energie energie = new Energie();
					energie.setId(getXpp().getAttributeValue(null, "id"));
					energie.setNom(getString());
					energies.add(energie);
				}
			}
			eventType = XMLgetSuivant();
		}
		
		Log.e("XML",energies.size()+" energies chargees");

		return energies;
	}

}
