package fr.RivaMedia.AnnoncesBateauGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Modele;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.core.XmlParser;

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
					Modele modele = new Modele();
					modele.setId(getXpp().getAttributeValue(null, "id"));
					modele.setLibelle(getString());
					modeles.add(modele);
				}
			}
			eventType = XMLgetSuivant();
		}
		Log.e("XML",modeles.size()+" modeles chargees");

		return modeles;
	}

}
