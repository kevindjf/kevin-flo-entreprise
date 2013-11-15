package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;

import fr.RivaMedia.model.*;
import fr.RivaMedia.xml.core.XmlParser;

public class RechercheXmlParser extends XmlParser {

	public RechercheXmlParser(String xml) {
		super(xml);	
	}

	public RechercheXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Object> getListe() {
		List<Object> bateaux = new ArrayList<Object>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("item")){
					Bateau bateau = getBateau(true);
					bateaux.add(bateau);
				}
				if(tag.equals("detail_bateau")){
					Bateau bateau = getBateau(false);
					bateaux.add(bateau);
				}
			}
			eventType = XMLgetSuivant();
		}

		Log.e("XML",bateaux.size()+" elements chargees");

		return bateaux;
	}

	public Bateau getBateau(boolean item){
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
					if(item)
						bateau.setNomMoteur(getString());
					else
						bateau.setMoteur(getMoteur());
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



				else if(tag.equals("lien_annonce")){
					bateau.setLienAnnonce(getString());
				}
				else if(tag.equals("numero_vendeur"))
					bateau.setNumeroVendeur(getString());
				else if(tag.equals("etat"))
					bateau.setEtat(getString());
				else if(tag.equals("largeur"))
					bateau.setLargeur(getString());
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
				else if(tag.equals("nb_photos"))
					bateau.setNbPhotos(getString());
				else if(tag.equals("photos"))
					bateau.setPhotos(getPhotos());
				else if(tag.equals("vendeur"))
					bateau.setVendeur(getVendeur());
			}
			eventType = XMLgetSuivant();
		}

		return bateau;
	}

	public Moteur getMoteur(){
		Moteur moteur = new Moteur();

		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML",tag);

				if(tag.equals("info_moteur"))
					moteur.setInfoMoteur(getString());
				else if(tag.equals("propulsion"))
					moteur.setPropulsion(getString());
				else if(tag.equals("heure_moteur"))
					moteur.setHeureMoteur(getString());
				else if(tag.equals("marque_moteur"))
					moteur.setMarqueMoteur(getString());
				else if(tag.equals("puissance_moteur"))
					moteur.setPuissanceMoteur(getString());
			}

			eventType = XMLgetSuivant();
		}
		return moteur;
	}

	public List<Lien> getPhotos(){
		List<Lien> liens = new ArrayList<Lien>();

		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML",tag);

				if(tag.equals("enclosure")){
					Lien lien = new Lien();
					lien.setType(getXpp().getAttributeValue(null, "type"));
					lien.setUrl(getXpp().getAttributeValue(null, "url"));
					liens.add(lien);
				}
			}

			eventType = XMLgetSuivant();
		}
		return liens;
	}

	public Vendeur getVendeur(){
		Vendeur vendeur = new Vendeur();

		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML",tag);

				if(tag.equals("nom"))
					vendeur.setNom(getString());
				else if(tag.equals("email"))
					vendeur.setEmail(getString());
				else if(tag.equals("adresse"))
					vendeur.setAdresse(getString());
				else if(tag.equals("cp"))
					vendeur.setCodePostal(getString());
				else if(tag.equals("ville"))
					vendeur.setVille(getString());
				else if(tag.equals("tel1"))
					vendeur.setTel1(getString());
				else if(tag.equals("tel2"))
					vendeur.setTel2(getString());
				else if(tag.equals("type"))
					vendeur.setType(getString());
			}

			eventType = XMLgetSuivant();
		}
		return vendeur;
	}
}
