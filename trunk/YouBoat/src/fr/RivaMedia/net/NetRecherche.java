package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Bateau;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.MarqueXmlParser;
import fr.RivaMedia.xml.RechercheXmlParser;

public class NetRecherche extends Net {

	public static List<Object> chargerListeBateaux(){

		String xml = Net.requeteGet(Constantes.RECHERCHE_BATEAU_ADRESSE, null);

		return new RechercheXmlParser(xml).getListeBateau();
	}
	
}
