package fr.RivaMedia.AnnoncesAutoGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Energie;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.core.XmlParser;

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
					energies.add(getEnergie());
				}
			}
			eventType = XMLgetSuivant();
		}
		
		Log.e("XML",energies.size()+" energies chargees");

		return energies;
	}
	
	public Energie getEnergie() {
		Energie energie = new Energie();
		int eventType = XMLgetSuivant(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("id"))
					energie.setId(getString());
				else if(tag.equals("nom"))
					energie.setNom(getString());
			}
			eventType = XMLgetSuivant();
		}

		return energie;
	}

}
