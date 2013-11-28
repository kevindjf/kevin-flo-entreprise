package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.AnnonceXmlParser;

public class NetAnnonce extends Net {	

	public static List<Annonce> rechercher(String type, Integer page, List<NameValuePair> donnees, String idClient){

		if(page != null)
			Net.add(donnees, Constantes.PAGE, page);
		if(idClient != null)
			Net.add(donnees, Constantes.ANNONCES_ID_CLIENT, idClient);
		
		String xml = Net.requeteGet(recupererUrlAnnonce(type),donnees);
		Log.e("NetRecherche",xml);
		return new AnnonceXmlParser(xml).getListe();
	}

	protected static String recupererUrlAnnonce(String _type){
		String url = null;

		if(_type != null){

			if(_type.equals(Constantes.BATEAU_A_MOTEUR) || _type.equals(Constantes.VOILIER) || _type.equals(Constantes.PNEU))
				url = Constantes.URL_ANNONCE_DETAIL_BATEAU;
			else if(_type.equals(Constantes.MOTEURS))
				url = Constantes.URL_ANNONCE_DETAIL_MOTEUR;
			else if(_type.equals(Constantes.ACCESSOIRES))
				url = Constantes.URL_ANNONCE_DETAIL_DIVER;
		}

		return url;
	}
	
	protected static String recupererUrlAnnonces(String _type){
		String url = null;

		if(_type != null){

			if(_type.equals(Constantes.BATEAU_A_MOTEUR) || _type.equals(Constantes.VOILIER) || _type.equals(Constantes.PNEU))
				url = Constantes.URL_ANNONCES_BATEAUX;
			else if(_type.equals(Constantes.MOTEURS))
				url = Constantes.URL_ANNONCES_MOTEURS;
			else if(_type.equals(Constantes.ACCESSOIRES))
				url = Constantes.URL_ANNONCES_DIVERS;
		}

		return url;
	}

	public static Annonce rechercherAnnonce(String type, List<NameValuePair> donnees) {
		String url = recupererUrlAnnonce(type);
		if(url != null){
			String xml = Net.requeteGet(recupererUrlAnnonce(type),donnees);
			List<Annonce> annonces = new AnnonceXmlParser(xml).getListe();
			if(annonces!=null && annonces.size()>0)
				return annonces.get(0);
		}
		return null;
	}

	public static List<Annonce> getAnnoncesDe(String numero, String type) {
		String url = "";
		if(type.equals(Constantes.BATEAUX))
			url = Constantes.URL_ANNONCES_BATEAUX_DE;
		else if(type.equals(Constantes.MOTEURS))
			url = Constantes.URL_ANNONCES_MOTEURS_DE;
		else if(type.equals(Constantes.ACCESSOIRES))
			url = Constantes.URL_ANNONCES_ACCESSOIRES_DE;

		String xml = Net.requeteGet(url,Net.construireDonnes(
				Constantes.ANNONCES_ID_CLIENT,numero
				));
		List<Annonce> annonces = new AnnonceXmlParser(xml).getListe();
		return annonces;
	}

}
