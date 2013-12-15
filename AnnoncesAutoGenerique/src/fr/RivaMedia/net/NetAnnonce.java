package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.AnnonceXmlParser;

public class NetAnnonce extends Net {	

	public static List<Annonce> getAnnonces(List<NameValuePair> donnees){

		String xml = Net.requeteGet(Constantes.URL_ANNONCES,donnees);
		return new AnnonceXmlParser(xml).getAnnonces();
	}
	
	public static List<Annonce> getDernieresAnnonces(){

		String xml = Net.requeteGet(Constantes.URL_DERNIERES_ANNONCES,null);
		return new AnnonceXmlParser(xml).getAnnonces();
	}

	
	public static Annonce getAnnonce(String id) {
		String xml = Net.requeteGet(Constantes.URL_ANNONCE_DETAIL,
				Net.construireDonnes(
				Constantes.ANNONCE_DETAIL_ID,id)
				);
		
		return new AnnonceXmlParser(xml).getAnnonce();
	}
	
}
