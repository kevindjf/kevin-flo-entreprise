package fr.RivaMedia.AnnoncesBateauGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Actualite;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.core.XmlParser;

public class ActualiteXmlParser extends XmlParser {

	public ActualiteXmlParser(String xml) {
		super(xml);	
	}

	public ActualiteXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	/*
	 * exemple: 
	  		<item>
				<title>BAVARIA CRUISER 37 ?? D??COUVRIR SUR ST-MANDRIER DANS LE VAR</title>
				<categorie>News Bateau</categorie>
				<link>188</link>
				<image>http://www.youboat.fr/images/actu/188.jpg</image>
				<pubDate>Thu, 17 Oct 2013 11:22:27 +0100</pubDate>
				<description>EVASION YACHTING, concessionnaire Bavaria dans le Var, est fier de pr??senter le nouveau BAVARIA CRUISER 37 du c??l??bre chantier Allemand. BAVARIA frappe fort pour cette nouvelle saison avec 5 nouveaut??s dans la gamme des voiliers dont le BAVARIA CRUISER 37 qui ??tait expos?? en 1??re mondiale au Salon [...]</description>
			</item>
	 */
	
	public List<Actualite> getListeActualites() {
		List<Actualite> listeActualites = new ArrayList<Actualite>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("actu")){
					listeActualites.add(getNews());
				}
			}
			eventType = XMLgetSuivant();
		}
		Log.e("XML",listeActualites.size()+" news chargees");

		return listeActualites;
	}
	
	public Actualite getNews(){
		Actualite news = new Actualite();
		
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				Log.e("XML",tag);
				if(tag.equals("title")){
					news.setTitle(getString());
				}
				else if(tag.equals("titre")){
					news.setTitle(getString());
				}
				else if(tag.equals("id")){
					news.setId(getString());
				}
				else if(tag.equals("categorie")){
					getString();
				}
				else if(tag.equals("link")){
					news.setLink(getString());
				}
				else if(tag.equals("image")){
					news.setImageAdress(getString());
					Log.e("IMAGE",news.getImageAdress());
				}
				else if(tag.equals("img")){
					news.setImageAdress(getString());
					//Log.e("IMAGE",news.getImageAdress());
				}
				else if(tag.equals("date")){
					news.setPubDate(getString());
					//TODO affecter dateFormatee
				}
				else if(tag.equals("pubDate")){
					news.setPubDate(getString());
					//TODO affecter dateFormatee
				}
				else if(tag.equals("description")){
					news.setDescription(getString());
				}
				else if(tag.equals("texte")){
					news.setDescription(getString());
				}
			}
			eventType = XMLgetSuivant();
		}
		
		return news;
	}
	

}
