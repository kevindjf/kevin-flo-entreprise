package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.model.Lien;
import fr.RivaMedia.model.Moteur;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.xml.core.XmlParser;

public class AnnonceXmlParser extends XmlParser {

	public AnnonceXmlParser(String xml) {
		super(xml);	
	}

	public AnnonceXmlParser(XmlPullParser xpp) {
		super(xpp);
	}
	
	public Map<String,Integer> getNbAnnonces() {
		Map<String,Integer> nb = new HashMap<String,Integer>();
		int eventType = XMLgetEventType();
		XMLgetSuivant();
		do{
			if (eventType == XmlPullParser.START_TAG) {
				try{
				String tag = getXpp().getName();
				String text = getString();
				//Log.e("XML", tag+" : "+text);
				nb.put(tag, Integer.parseInt(text));
				}catch(Exception e){}
			}
			eventType = XMLgetSuivant();
		}while (eventType != XmlPullParser.END_DOCUMENT);

		Log.e("XML",nb.size()+" nombres d'annonces chargees");

		return nb;
	}

	public List<Annonce> getListe() {
		List<Annonce> annonces = new ArrayList<Annonce>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("item")){
					Annonce annonce = getAnnonce(true);
					annonces.add(annonce);
				}
				if(tag.startsWith("detail_")){
					String type = tag.replace("detail_", "");
					Annonce annonce = getAnnonce(false);
					annonce.setTypeAnnonce(type);
					annonces.add(annonce);
				}
			}
			eventType = XMLgetSuivant();
		}

		Log.e("XML",annonces.size()+" elements chargees");

		return annonces;
	}

	public Annonce getAnnonce(boolean item){
		Annonce annonce = new Annonce();
		
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML",tag);
				if(tag.equals("numero")){
					annonce.setNumero(getString());
				}
				else if(tag.equals("title")){
					annonce.setTitle(getString());
				}
				else if(tag.equals("moteur")){
					if(item)
						annonce.setNomMoteur(getString());
					else
						annonce.setMoteur(getMoteur());
				}
				else if(tag.equals("longueur")){
					annonce.setLongueur(getString());
				}
				else if(tag.equals("annee")){
					annonce.setAnnee(getString());
				}
				else if(tag.equals("categorie")){
					annonce.setCategorie(getString());
				}
				else if(tag.equals("gpslatitude")){
					annonce.setGpsLatitude(getString());
				}
				else if(tag.equals("gpslongitude")){
					annonce.setGpsLongtitude(getString());
				}
				else if(tag.equals("type")){
					annonce.setType(getString());
				}
				else if(tag.equals("typeclient")){
					annonce.setTypeClient(getString());
				}
				else if(tag.equals("enclosure")){
					Lien lien = new Lien();
					lien.setType(getXpp().getAttributeValue(null, "type"));
					lien.setUrl(getXpp().getAttributeValue(null, "url"));
					annonce.setLien(lien);
					getString();
				}
				else if(tag.equals("prix")){
					annonce.setPrix(getString());
				}
				else if(tag.equals("taxeprix")){
					annonce.setTaxePrix(getString());
				}
				else if(tag.equals("pubDate")){
					annonce.setPubDate(getString());
				}
				else if(tag.equals("lien_annonce")){
					annonce.setLienAnnonce(getString());
				}
				else if(tag.equals("numero_vendeur"))
					annonce.setNumeroVendeur(getString());
				else if(tag.equals("etat"))
					annonce.setEtat(getString());
				else if(tag.equals("largeur"))
					annonce.setLargeur(getString());
				else if(tag.equals("nb_cabine"))
					annonce.setNbCabines(getString());
				else if(tag.equals("nb_couch"))
					annonce.setNbCouchettes(getString());
				else if(tag.equals("nb_sdb"))
					annonce.setNbSallesDeBain(getString());
				else if(tag.equals("garantie"))
					annonce.setGarantie(getString());
				else if(tag.equals("commentaire"))
					annonce.setCommentaire(getString());
				else if(tag.equals("place_de_port"))
					annonce.setPlaceDePort(getString());
				else if(tag.equals("taxe"))
					annonce.setTaxePrix(getString());
				else if(tag.equals("equipement"))
					annonce.getEquipement().add(getString());
				else if(tag.equals("electronique"))
					annonce.getEquipement().add(getString());
				else if(tag.equals("nb_photos"))
					annonce.setNbPhotos(getString());
				else if(tag.equals("photos"))
					annonce.setPhotos(getPhotos());
				else if(tag.equals("vendeur")){
					Log.e("RechercheXmlParser","Vendeur");
					annonce.setVendeur(getVendeur());
				}
			}
			eventType = XMLgetSuivant();
		}

		return annonce;
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
					getString();
				}
			}

			eventType = XMLgetSuivant();
		}
		return liens;
	}

	public Vendeur getVendeur(){
		Vendeur vendeur = new Vendeur();

		int eventType = XMLgetEventType(); 
		do { 
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
				else if(tag.equals("siteweb"))
					vendeur.setSiteWeb(getString());
			}

			eventType = XMLgetSuivant();
			
		}while (eventType != XmlPullParser.END_TAG);
		return vendeur;
	}
}
