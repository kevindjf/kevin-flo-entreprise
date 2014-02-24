package fr.RivaMedia.AnnoncesBateauGenerique.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesBateauGenerique.model.TypeAnnonce;
import fr.RivaMedia.AnnoncesBateauGenerique.net.core.Net;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.AnnonceXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.NombreXmlParser;

public class NetAnnonce extends Net {	

	protected static String recupererUrlAnnonces(String _type ){
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

	public static List<Annonce> rechercher(String type, TypeAnnonce typeAnnonce, Integer page, List<NameValuePair> donnees, String idClient){

		if(donnees == null )
			donnees = new ArrayList<NameValuePair>();
		
		List<NameValuePair> d = new ArrayList<NameValuePair>(donnees);

		if(page != null)
			Net.add(d, Constantes.PAGE, page);
		if(idClient != null)
			Net.add(d, Constantes.ANNONCES_ID_CLIENT, idClient);

		String xml = "";
		if(typeAnnonce != null && typeAnnonce.getUrl() != null)
			xml = Net.requeteGet(typeAnnonce.getUrl(),true,d);
		else
			xml = Net.requeteGet(recupererUrlAnnonces(type),d);
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



	public static Annonce rechercherAnnonce(String type, List<NameValuePair> donnees) {
		String url = recupererUrlAnnonce(type);
		if(url != null){
			String xml = Net.requeteGet(recupererUrlAnnonce(type),donnees);
			Annonce annonce = new AnnonceXmlParser(xml).getAnnonce();
			annonce.setType(type);
			return annonce;
		}
		return null;
	}

	public static List<Annonce> getAnnoncesDe(String numero, String type) {
		String url = recupererUrlAnnonces(type);

		String xml = Net.requeteGet(url,Net.construireDonnes(
				Constantes.ANNONCES_ID_CLIENT,numero
				));
		
		List<Annonce> annonces = new AnnonceXmlParser(xml).getListe();
		return annonces;
	}

	protected static String recupererUrlNombreAnnonces(String _type){
		String url = null;

		if(_type != null){

			if(_type.equals(Constantes.BATEAU_A_MOTEUR) || _type.equals(Constantes.VOILIER) || _type.equals(Constantes.PNEU))
				url = Constantes.URL_NB_ANNONCES_BATEAUX;
			else if(_type.equals(Constantes.MOTEURS))
				url = Constantes.URL_NB_ANNONCES_MOTEURS;
			else if(_type.equals(Constantes.ACCESSOIRES))
				url = Constantes.URL_NB_ANNONCES_DIVERS;
		}

		return url;
	}
	
	public static String nombreAnnonces(String type, List<NameValuePair> donnees) {
		String url = recupererUrlNombreAnnonces(type);
		if(url != null){
			String xml = Net.requeteGet(url,donnees);
			return new NombreXmlParser(xml).getNombre();
		}
		return null;
	}
	
}
