package fr.RivaMedia.AnnoncesBateauGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.model.TypeAnnonce;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.core.XmlParser;

public class TypeAnnonceXmlParser extends XmlParser {

	public TypeAnnonceXmlParser(String xml) {
		super(xml);	
	}

	public TypeAnnonceXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<TypeAnnonce> getTypesAnnonces() {
		List<TypeAnnonce> typesAnnonces = new ArrayList<TypeAnnonce>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("type")){
					TypeAnnonce typeAnnonce = new TypeAnnonce();
					typeAnnonce.setId(getXpp().getAttributeValue(null, "id"));
					typeAnnonce.setNom(getString());
					typesAnnonces.add(typeAnnonce);
				}
			}
			eventType = XMLgetSuivant();
		}
		
		Log.e("XML",typesAnnonces.size()+" types d'annonces chargees");

		return typesAnnonces;
	}

}
