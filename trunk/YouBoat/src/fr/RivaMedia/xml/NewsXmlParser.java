package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.News;
import fr.RivaMedia.xml.core.XmlParser;

public class NewsXmlParser extends XmlParser {

	public NewsXmlParser(String xml) {
		super(xml);	
	}

	public NewsXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	/*
	 * exemple: 
	  		<item>
				<title>BAVARIA CRUISER 37 À DÉCOUVRIR SUR ST-MANDRIER DANS LE VAR</title>
				<categorie>News Bateau</categorie>
				<link>188</link>
				<image>http://www.youboat.fr/images/actu/188.jpg</image>
				<pubDate>Thu, 17 Oct 2013 11:22:27 +0100</pubDate>
				<description>EVASION YACHTING, concessionnaire Bavaria dans le Var, est fier de présenter le nouveau BAVARIA CRUISER 37 du célèbre chantier Allemand. BAVARIA frappe fort pour cette nouvelle saison avec 5 nouveautés dans la gamme des voiliers dont le BAVARIA CRUISER 37 qui était exposé en 1ère mondiale au Salon [...]</description>
			</item>
	 */
	
	public List<News> getListeNews() {
		List<News> listeNews = new ArrayList<News>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("item")){
					listeNews.add(getNews());
				}
			}
			eventType = XMLgetSuivant();
		}
		Log.e("XML",listeNews.size()+" news chargees");

		return listeNews;
	}
	
	public News getNews(){
		News news = new News();
		
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML",tag);
				if(tag.equals("title")){
					news.setTitle(getString());
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
				else if(tag.equals("pubDate")){
					news.setPubDate(getString());
					//TODO affecter dateFormatee
				}
				else if(tag.equals("description")){
					news.setDescription(getString());
				}
			}
			eventType = XMLgetSuivant();
		}
		
		return news;
	}
	

}
