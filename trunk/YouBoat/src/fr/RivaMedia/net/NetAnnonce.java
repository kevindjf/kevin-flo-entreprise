package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.AnnonceXmlParser;

public class NetAnnonce extends Net {	

	public static List<Annonce> rechercher(String url, List<NameValuePair> donnees){

		String xml = Net.requeteGet(url,donnees);
		Log.e("NetRecherche",xml);
		return new AnnonceXmlParser(xml).getListe();
	}

	protected static String recupererUrl(String _type){
		String url = null;

		if(_type != null){

			if(_type.equals(Constantes.BATEAU_A_MOTEUR))
				url = Constantes.URL_ANNONCE_DETAIL_BATEAUX_A_MOTEURS;
			else if(_type.equals(Constantes.VOILIER))
				url = Constantes.URL_ANNONCE_DETAIL_BATEAUX_VOILIERS;
			else if(_type.equals(Constantes.PNEU))
				url = Constantes.URL_ANNONCE_DETAIL_BATEAUX_PNEUMATIQUES;
			else if(_type.equals(Constantes.MOTEURS))
				url = Constantes.URL_ANNONCE_DETAIL_MOTEURS;
			else if(_type.equals(Constantes.ACCESSOIRES))
				url = Constantes.URL_ANNONCE_DETAIL_ACCESSOIRES;
		}

		return url;
	}

	public static Annonce rechercherAnnonce(String type, List<NameValuePair> donnees) {
		String url = recupererUrl(type);
		if(url != null){
			List<Annonce> annonces = rechercher(url, donnees);
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

		return rechercher(url, Net.construireDonnes(
				Constantes.ANNONCES_ID_CLIENT,numero
				));
	}

}
