package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.xml.core.XmlParser;

public class CategorieXmlParser extends XmlParser {

	public CategorieXmlParser(String xml) {
		super(xml);	
	}

	public CategorieXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Categorie> getCategories() {
		List<Categorie> categories = new ArrayList<Categorie>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("categorie")){
					categories.add(getCategorie());
				}
			}
			eventType = XMLgetSuivant();
		}
		
		Log.e("XML",categories.size()+" categories chargees");

		return categories;
	}
	
	public Categorie getCategorie() {
		Categorie categorie = new Categorie();
		int eventType = XMLgetSuivant(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("id"))
					categorie.setId(getString());
				else if(tag.equals("nom"))
					categorie.setNom(getString());
			}
			eventType = XMLgetSuivant();
		}

		return categorie;
	}

}
