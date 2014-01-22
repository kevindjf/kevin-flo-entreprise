package fr.RivaMedia.AnnoncesBateauGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Categorie;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.core.XmlParser;

public class CategorieXmlParser extends XmlParser {

	public CategorieXmlParser(String xml) {
		super(xml);	
	}

	public CategorieXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Categorie> getCategoriesBateau() {
		List<Categorie> categories = new ArrayList<Categorie>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("categorie")){
					Categorie categorie = new Categorie();
					categorie.setIdCategory(getXpp().getAttributeValue(null, "id"));
					categorie.setLibelle(getString());
					categories.add(categorie);
				}
			}
			eventType = XMLgetSuivant();
		}
		
		Log.e("XML",categories.size()+" categories chargees");

		return categories;
	}

}
