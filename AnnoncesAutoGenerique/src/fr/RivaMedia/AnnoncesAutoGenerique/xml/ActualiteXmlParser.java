package fr.RivaMedia.AnnoncesAutoGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Actualite;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.core.XmlParser;

public class ActualiteXmlParser extends XmlParser {

	public ActualiteXmlParser(String xml) {
		super(xml);	
	}

	public ActualiteXmlParser(XmlPullParser xpp) {
		super(xpp);
	}
	
	public List<Actualite> getListeActualites() {
		List<Actualite> listeActualites = new ArrayList<Actualite>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("actualite")){
					listeActualites.add(getActualite());
				}
			}
			eventType = XMLgetSuivant();
		}
		Log.e("XML",listeActualites.size()+" actualites chargees");

		return listeActualites;
	}
	
	public Actualite getActualite(){
		Actualite actualite = new Actualite();
		
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML",tag);
				if(tag.equals("id"))
					actualite.setId(getString());
				else if(tag.equals("titre"))
					actualite.setTitre(getString());
				else if(tag.equals("texte"))
					actualite.setTexte(getString());
				else if(tag.equals("tags"))
					actualite.setTitre(getString());
				else if(tag.equals("date"))
					actualite.setDate(getString());
				else if(tag.equals("photo"))
					actualite.setPhoto(getString());
				else if(tag.equals("video"))
					actualite.setVideo(getString());
				else if(tag.equals("categorie"))
					actualite.setCategorie(getString());
			}
			eventType = XMLgetSuivant();
		}
		
		return actualite;
	}
	

}
