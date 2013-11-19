package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.fragments.Annonces;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.AnnonceXmlParser;

public class NetAnnonce extends Net {

	public static List<Annonce> chargerListeBateaux(){

		String xml = Net.requeteGet(Constantes.RECHERCHE_BATEAU_ADRESSE, null);

		return new AnnonceXmlParser(xml).getListe();
	}
	
	
	public static List<Annonce> rechercher(String url, List<NameValuePair> donnees){

		String xml = Net.requeteGet(url,donnees);
		Log.e("NetRecherche",xml);
		return new AnnonceXmlParser(xml).getListe();
	}

	public static Annonce rechercherAnnonce(String url, List<NameValuePair> donnees) {
		List<Annonce> annonces = rechercher(url, donnees);
		if(annonces.size()>0)
			return annonces.get(0);
		else
			return null;
	}


	public static String annonceBateauDe = "xml-client-bateau.php?";
	public static String annonceMoteurDe = "xml-client-moteur.php?";
	public static String annonceAccessoiresDe = "xml-client-accessoire.php?";
	
	public static String idClient = "idcli";
	
	public static List<Annonce> getAnnoncesDe(String numero, String type) {
		String url = "";
		if(type.equals(""+Annonces.BATEAUX))
			url = annonceBateauDe;
		else if(type.equals(""+Annonces.MOTEURS))
			url = annonceMoteurDe;
		else if(type.equals(""+Annonces.DIVERS))
			url = annonceAccessoiresDe;
		
		return rechercher(url, Net.construireDonnes(
				idClient,numero
				));
	}
	
}
