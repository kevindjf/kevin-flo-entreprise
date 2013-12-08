package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.Alerte;
import fr.RivaMedia.xml.core.XmlParser;

public class AlerteXmlParser extends XmlParser {

	public AlerteXmlParser(String xml) {
		super(xml);	
	}

	public AlerteXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Alerte> getListe() {
		List<Alerte> alertes = new ArrayList<Alerte>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("alerte")){
					alertes.add(getAlerte());
				}
			}
			eventType = XMLgetSuivant();
		}

		Log.e("XML",alertes.size()+" alertes chargees");

		return alertes;
	}

	/*
<alertes>
    <alerte>
        <id>1</id>
        <jeton>FKJSIFHZIFSEFIHFUIEHFF456451654FZHSFHII564FIIUSFHIU</jeton>
        <idcat_bateau>18</idcat_bateau>
        <taille_min>4</taille_min>
        <taille_max>10</taille_max>
        <budget_min>30000</budget_min>
        <budget_max>60000</budget_max>
        <date>2013-11-29 11:10:00</date>
    </alerte>
    <alerte>...</alerte>
</alertes>
	 */

	public Alerte getAlerte(){
		Alerte alerte = new Alerte();

		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML",tag);
				if(tag.equals("id")){
					alerte.setId(getString());
				}
				else if(tag.equals("idcat_bateau")){
					alerte.setCategorie(getString());
				}
				else if(tag.equals("jeton")){
					alerte.setJeton(getString());
				}
				else if(tag.equals("date")){
					alerte.setDate(getString());
				}
				else if(tag.equals("etat")){
					alerte.setEtat(getString());
				}
				else if(tag.equals("taille_min")){
					alerte.setLongueurMin(getString());
				}
				else if(tag.equals("taille_max")){
					alerte.setLongueurMax(getString());
				}
				else if(tag.equals("budget_min")){
					alerte.setPrixMin(getString());
				}
				else if(tag.equals("budget_max")){
					alerte.setPrixMax(getString());
				}

			}
			eventType = XMLgetSuivant();
		}

		return alerte;
	}

	public String getJeton(){

		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML",tag);
				if(tag.equals("idiphone")){
					return getString();
				}
			}
			eventType = XMLgetSuivant();
		}

		return "";
	}

}
