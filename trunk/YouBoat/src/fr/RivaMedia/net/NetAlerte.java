package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.net.core.Net;

public class NetAlerte extends Net{

	public Boolean creerAlerte(String idTelephone, String categorieBateau){
		return creerAlerte(idTelephone, categorieBateau, null, null, null, null);
	}
	
	public Boolean creerAlerte(String idTelephone, String categorieBateau, String minLong, String maxLong, String minPrix, String maxPrix){
		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.ALERTE_ID_SMARTPHONE, idTelephone,
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

		return Boolean.parseBoolean(Net.requete(Constantes.URL_CREER_ALERTE, donnees));
	}

	public Boolean supprimerAlerte(String idTelephone, String categorieBateau, String alerteId){
		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.ALERTE_ID_SMARTPHONE, idTelephone,
				Constantes.ALERTE_ID_CATEGORIE, categorieBateau,
				Constantes.SUPPRIMER_ALERTE_ID, alerteId,
				Constantes.SUPPRIMER_ALERTE_DELETE, "1"
				);

		return Boolean.parseBoolean(Net.requete(Constantes.URL_SUPPRIMER_ALERTE, donnees));
	}

}