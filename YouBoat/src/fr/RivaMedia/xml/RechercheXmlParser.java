package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;

import fr.RivaMedia.model.Bateau;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Lien;
import fr.RivaMedia.model.News;
import fr.RivaMedia.xml.core.XmlParser;

public class RechercheXmlParser extends XmlParser {

	public RechercheXmlParser(String xml) {
		super(xml);	
	}

	public RechercheXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Object> getListeBateau() {
		List<Object> bateaux = new ArrayList<Object>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("item")){
					Bateau bateau = getBateau();
					bateaux.add(bateau);
				}
			}
			eventType = XMLgetSuivant();
		}
		
		Log.e("XML",bateaux.size()+" bateaux chargees");

		return bateaux;
	}
	
	public Bateau getBateau(){
		Bateau bateau = new Bateau();
		
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML",tag);
				if(tag.equals("numero")){
					bateau.setNumero(getString());
				}
				else if(tag.equals("title")){
					bateau.setTitle(getString());
				}
				else if(tag.equals("moteur")){
					bateau.setMoteur(getString());
				}
				else if(tag.equals("longueur")){
					bateau.setLongueur(getString());
				}
				else if(tag.equals("annee")){
					bateau.setAnnee(getString());
				}
				else if(tag.equals("categorie")){
					bateau.setCategorie(getString());
				}
				else if(tag.equals("gpslatitude")){
					bateau.setGpsLatitude(getString());
				}
				else if(tag.equals("gpslongitude")){
					bateau.setGpsLongtitude(getString());
				}
				else if(tag.equals("typeclient")){
					bateau.setTypeClient(getString());
				}
				else if(tag.equals("photos")){
					bateau.setPhotos(getString());
				}
				else if(tag.equals("enclosure")){
					Lien lien = new Lien();
					lien.setType(getXpp().getAttributeValue(null, "type"));
					lien.setUrl(getXpp().getAttributeValue(null, "url"));
					bateau.setLien(lien);
					getString();
				}
				else if(tag.equals("prix")){
					bateau.setPrix(getString());
				}
				else if(tag.equals("taxeprix")){
					bateau.setTaxePrix(getString());
				}
				else if(tag.equals("pubDate")){
					bateau.setPubDate(getString());
				}
				
				/*
				
				else if(tag.equals("lien_annonce")){
					bateau.setLienAnnonce(getString());
				}
				else if(tag.equals("numero_vendeur"))
					bateau.setNumeroVendeur(getString());
				else if(tag.equals("moteur"))
					bateau.setMoteur(getMoteur());
				else if(tag.equals("etat"))
					bateau.setEtat(getString());
				else if(tag.equals("longueur"))
					bateau.setLongueur(getString());
				else if(tag.equals("nb_cabine"))
					bateau.setNbCabines(getString());
				else if(tag.equals("nb_couch"))
					bateau.setNbCouchettes(getString());
				else if(tag.equals("nb_sdb"))
					bateau.setNbSallesDeBain(getString());
				else if(tag.equals("garantie"))
					bateau.setGarantie(getString());
				else if(tag.equals("commentaire"))
					bateau.setCommentaire(getString());
				else if(tag.equals("place_de_port"))
					bateau.setPlaceDePort(getString());
				else if(tag.equals("taxe"))
					bateau.setTaxePrix(getString());
				*/
			}
			eventType = XMLgetSuivant();
		}
		
		return bateau;
	}

}
