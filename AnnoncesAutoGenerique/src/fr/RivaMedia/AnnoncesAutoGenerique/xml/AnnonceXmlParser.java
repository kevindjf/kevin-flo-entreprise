package fr.RivaMedia.AnnoncesAutoGenerique.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.core.XmlParser;

public class AnnonceXmlParser extends XmlParser {

	public AnnonceXmlParser(String xml) {
		super(xml);	
	}

	public AnnonceXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Annonce> getAnnonces() {
		List<Annonce> annonces = new ArrayList<Annonce>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("annonce")){
					Annonce annonce = getAnnonce();
					annonces.add(annonce);
				}
			}
			eventType = XMLgetSuivant();
		}

		Log.e("XML",annonces.size()+" annonces chargees");

		return annonces;
	}
	
	public String getNombreAnnonces() {
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				return getString().trim();
			}
			eventType = XMLgetSuivant();
		}

		return "";
	}

	public Annonce getAnnonce(){
		Annonce annonce = new Annonce();

		int eventType = XMLgetEventType();
		String tag = "";
		while (eventType != XmlPullParser.END_TAG) { 

			if (eventType == XmlPullParser.START_TAG) {
				tag = getXpp().getName();
				//Log.e("XML ANNONCE",tag);

				if(tag.equals("id"))
					annonce.setId(getString());
				else if(tag.equals("id_client"))
					annonce.setIdClient(getString());
				else if(tag.equals("categorie"))
					annonce.setCategorie(getString());
				else if(tag.equals("marque"))
					annonce.setMarque(getString());
				else if(tag.equals("serie"))
					annonce.setSerie(getString());
				else if(tag.equals("finition"))
					annonce.setFinition(getString());
				else if(tag.equals("energie"))
					annonce.setEnergie(getString());
				else if(tag.equals("annee"))
					annonce.setAnnee(getString());
				else if(tag.equals("km"))
					annonce.setKm(getString());
				else if(tag.equals("prix"))
					annonce.setPrix(getString());
				else if(tag.equals("departement"))
					annonce.setDepartement(getString());
				else if(tag.equals("departement_num"))
					annonce.setDepartementNum(getString());
				else if(tag.equals("photo"))
					annonce.setPhoto(getString());

				else if(tag.equals("transmission"))
					annonce.setTransmission(getString());
				else if(tag.equals("puissance_din"))
					XMLgetSuivant();
					//annonce.setPuissanceDin(getString());
				else if(tag.equals("puissance_fisc"))
					annonce.setPuissanceFisc(getString());
				else if(tag.equals("co2"))
					XMLgetSuivant();
					//annonce.setCo2(getString());
				else if(tag.equals("nb_portes"))
					annonce.setNbPortes(getString());
				else if(tag.equals("couleur_ext"))
					annonce.setCouleurExt(getString());
				else if(tag.equals("couleur_int"))
					annonce.setCouleurInt(getString());
				else if(tag.equals("garantie"))
					annonce.setGarantie(getString());
				else if(tag.equals("reference"))
					annonce.setReference(getString());
				else if(tag.equals("descriptif"))
					annonce.setDescriptif(getString());


				else if(tag.equals("client"))
					annonce.setClient(new ClientXmlParser(getXpp()).getClient());
				else if(tag.equals("photos"))
					annonce.setPhotos(getPhotos());


				else
					Log.e("XML INCONNU",tag);




			}
			eventType = XMLgetSuivant();
		}

		return annonce;
	}

	public List<String> getPhotos() {

		System.err.println("photos");

		List<String> photos = new ArrayList<String>();
		int eventType = XMLgetSuivant(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("photo"))
					photos.add(getString());
			}
			System.err.println("photos :"+photos);
			eventType = XMLgetSuivant();
		}

		return photos;
	}
}
