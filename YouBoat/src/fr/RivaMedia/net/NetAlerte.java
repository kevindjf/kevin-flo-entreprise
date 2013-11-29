package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;


import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Alerte;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.AlerteXmlParser;

public class NetAlerte extends Net{

	public static Boolean creerAlerte(String categorieBateau){
		return creerAlerte(categorieBateau, null, null, null, null);
	}
	
	public static Boolean creerAlerte(String categorieBateau, String minLong, String maxLong, String minPrix, String maxPrix){
		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.ALERTE_ID_SMARTPHONE, Donnees.jeton,
				Constantes.ALERTE_ID_CATEGORIE, categorieBateau
				);
		if(minLong != null)
			Net.add(donnees, Constantes.ALERTE_MIN_LONG, minLong);
		if(maxLong != null)
			Net.add(donnees, Constantes.ALERTE_MAX_LONG, maxLong);
		if(minPrix != null)
			Net.add(donnees, Constantes.ALERTE_MIN_PRIX, minPrix);
		if(maxPrix != null)
			Net.add(donnees, Constantes.ALERTE_MAX_PRIX, maxPrix);

		return creerAlerte(donnees);
	}
	
	public static Boolean creerAlerte(List<NameValuePair> donnees){
		return Boolean.parseBoolean(Net.requete(Constantes.URL_CREER_ALERTE, donnees));
	}

	public static Boolean supprimerAlerte(String alerteId){
		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.ALERTE_ID_SMARTPHONE, Donnees.jeton,
				Constantes.SUPPRIMER_ALERTE_ID, alerteId,
				Constantes.SUPPRIMER_ALERTE_DELETE, "1"
				);

		return Boolean.parseBoolean(Net.requete(Constantes.URL_SUPPRIMER_ALERTE, donnees));
	}
	
	public static List<Alerte> getAlertes(){		
		String xml = Net.requeteGet(Constantes.URL_ALERTES,
				Net.construireDonnes(Constantes.ALERTE_JETON,Donnees.jeton)
				);
		return new AlerteXmlParser(xml).getListe();
	}

}
