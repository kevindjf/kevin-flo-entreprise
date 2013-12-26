package fr.RivaMedia.AnnoncesAutoGenerique.net;

import fr.RivaMedia.AnnoncesAutoGenerique.Constantes;
import fr.RivaMedia.AnnoncesAutoGenerique.net.core.Net;

public class NetInscriptionActualite {
	
	public static String inscrireActualites(String jeton){
		return Net.requete(Constantes.URL_ABONNEMENT_ACTUALITES, 
				Net.construireDonnes(
						Constantes.ABONNEMENT_ACTUALITES_JETON,jeton,
						Constantes.ABONNEMENT_ACTUALITES_TYPE,Constantes.ABONNEMENT_ACTUALITES_TYPE_VALUE
						)
				);
	}
	
}
